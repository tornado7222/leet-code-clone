package com.example.leetcodeclone.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserRegisterResponseDto {
    private UUID id;
    private String name;
    private String surname;
    private String phoneNumber;
    private LocalDate birthDate;
}
