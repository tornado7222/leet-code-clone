package com.example.leetcodeclone.notification.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EskizSendSmsRequestDto {
    @JsonProperty("mobile_phone")
    private String phoneNumber;
    private String message;
    private String from = "4546";

    public EskizSendSmsRequestDto(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }
}
