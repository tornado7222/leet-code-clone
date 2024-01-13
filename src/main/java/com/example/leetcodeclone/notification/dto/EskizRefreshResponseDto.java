package com.example.leetcodeclone.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EskizRefreshResponseDto {
    private String message;
    private HashMap<String, String> data;
}
