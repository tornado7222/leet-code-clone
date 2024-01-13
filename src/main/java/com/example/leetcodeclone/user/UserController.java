package com.example.leetcodeclone.user;

import com.example.leetcodeclone.user.dto.UserLoginRequestDto;
import com.example.leetcodeclone.user.dto.UserRegisterRequestDto;
import com.example.leetcodeclone.user.dto.UserRegisterResponseDto;
import com.example.leetcodeclone.user.otp.dto.OTPVerifyDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> register(@RequestBody @Valid UserRegisterRequestDto requestDto){
        UserRegisterResponseDto userRegisterResponseDto = userService.register(requestDto);
        return ResponseEntity.ok(userRegisterResponseDto);
    }

    @PostMapping("/register/verify/sms")
    public ResponseEntity<UserRegisterResponseDto> verify(@RequestBody @Valid OTPVerifyDto dto){
        UserRegisterResponseDto userRegisterResponseDto = userService.verifyOtp(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRegisterResponseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserRegisterResponseDto> register(@RequestBody @Valid UserLoginRequestDto requestDto){
        UserRegisterResponseDto userRegisterResponseDto = userService.login(requestDto);
        return ResponseEntity.ok(userRegisterResponseDto);
    }
}
