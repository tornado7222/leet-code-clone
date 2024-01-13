package com.example.leetcodeclone.user.otp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OTPVerifyDto {
    @NotBlank
    private String phoneNumber;
    @NotNull
    private int code;
}
