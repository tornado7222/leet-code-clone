package com.example.leetcodeclone.notification;

import com.example.leetcodeclone.notification.dto.EskizRefreshResponseDto;
import com.example.leetcodeclone.notification.dto.EskizSendSmsRequestDto;
import com.example.leetcodeclone.notification.dto.EskizSendSmsResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "notificationFeign", url = "notify.eskiz.uz/api")
public interface NotificationFeign {
    @PatchMapping("/auth/refresh")
    EskizRefreshResponseDto refresh(@RequestHeader(HttpHeaders.AUTHORIZATION) String token);
    @PostMapping("/message/sms/send")
    EskizSendSmsResponseDto send(@RequestBody EskizSendSmsRequestDto requestDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String token);
}
