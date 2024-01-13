package com.example.leetcodeclone.user.otp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDate;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@RedisHash(timeToLive = 3600)
public class OTP {
    @Id
    private String phoneNumber;
    private String name;
    private String surname;
    private String password;
    private LocalDate birthDate;
    private int code;
    private LocalDateTime sendTime;
    private int sentCount;
}
