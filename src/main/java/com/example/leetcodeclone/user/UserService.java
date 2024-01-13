package com.example.leetcodeclone.user;

import com.example.leetcodeclone.notification.NotificationService;
import com.example.leetcodeclone.user.dto.UserLoginRequestDto;
import com.example.leetcodeclone.user.dto.UserRegisterRequestDto;
import com.example.leetcodeclone.user.dto.UserRegisterResponseDto;
import com.example.leetcodeclone.user.entity.User;
import com.example.leetcodeclone.user.otp.OTP;
import com.example.leetcodeclone.user.otp.OTPRepository;
import com.example.leetcodeclone.user.otp.dto.OTPVerifyDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OTPRepository otpRepository;
    private final NotificationService notificationService;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return userRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(
                        () -> new EntityNotFoundException("User with phone number: %s does not exist"
                                .formatted(phoneNumber)
                        )
                );
    }

    @Transactional
    public UserRegisterResponseDto register(UserRegisterRequestDto requestDto) {
        String password = requestDto.getPassword();
        String encoderPassword = passwordEncoder.encode(password);
        requestDto.setPassword(encoderPassword);

        validateUserRegister(requestDto);

        OTP otp = mapper.map(requestDto, OTP.class);
        int code = new Random().nextInt(100000, 999999);
        otp.setCode(code);
        otp.setSendTime(LocalDateTime.now());
        otp.setSentCount(1);

        boolean isCodeSent = notificationService.sendSms(otp.getPhoneNumber(), "Your verification code: %s".formatted(code));

        if (isCodeSent) {
            OTP savedOtp = otpRepository.save(otp);
            return mapper.map(savedOtp, UserRegisterResponseDto.class);
        } else {
            // todo handle this exception
            throw new RuntimeException("We couldn't send sms. Please try later");
        }
    }

    private void validateUserRegister(UserRegisterRequestDto requestDto) {
        Optional<OTP> otp = otpRepository.findById(requestDto.getPhoneNumber());
        if (otp.isPresent()) {
            // todo handle this exception
            throw new RuntimeException("You have already registered. Please verify you phone number and finish registration");
        } else {
            Optional<User> byPhoneNumber = userRepository.findByPhoneNumber(requestDto.getPhoneNumber());
            if (byPhoneNumber.isPresent()) {
                // todo handle this exception
                throw new RuntimeException("This username already token");
            }
        }
    }

    @Transactional
    public UserRegisterResponseDto verifyOtp(@Valid OTPVerifyDto dto) {
        OTP otp = otpRepository
                .findById(dto.getPhoneNumber())
                // todo handle this exception
                .orElseThrow(() -> new RuntimeException("You need to register first"));

        if (otp.getCode() == dto.getCode()) {
            User user = mapper.map(otp, User.class);
            user.setId(UUID.randomUUID());
            user.setSubmissionBlocked(false);

            User saved = userRepository.save(user);
            otpRepository.deleteById(saved.getPhoneNumber());
            return mapper.map(saved, UserRegisterResponseDto.class);
        } else
            //todo handle this exception
            throw new RuntimeException("You have entered wrong code");
    }

    @Transactional
    public UserRegisterResponseDto login(UserLoginRequestDto requestDto) {
        User user = userRepository.findByPhoneNumber(requestDto.getPhoneNumber())
                // todo handle this exception
                .orElseThrow(() -> new BadCredentialsException("Username or password is not correct"));
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())){
            throw new BadCredentialsException("Phone number or password is not correct");
        }
        return new UserRegisterResponseDto(null,null,"nima",null,null);
    }
}
