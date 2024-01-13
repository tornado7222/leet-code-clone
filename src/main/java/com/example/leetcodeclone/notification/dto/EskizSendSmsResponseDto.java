package com.example.leetcodeclone.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EskizSendSmsResponseDto {
    private UUID id;
    private String message;
    private String status;
}
