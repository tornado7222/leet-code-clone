package com.example.leetcodeclone.notification;

import com.example.leetcodeclone.notification.dto.EskizRefreshResponseDto;
import com.example.leetcodeclone.notification.dto.EskizSendSmsRequestDto;
import feign.FeignException;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final NotificationFeign notificationFeign;
    @Value("${leet-code-clone.sms.eskiz.token}")
    private String token;

    public boolean sendSms(String phoneNumber, String message) {
        try {
            notificationFeign.send(new EskizSendSmsRequestDto(phoneNumber, message), token);
            return true;
        } catch (FeignException.Forbidden | FeignException.Unauthorized e) {
            try {
                EskizRefreshResponseDto refreshToken = notificationFeign.refresh(token);
                token = refreshToken.getData().get("token");
                notificationFeign.send(new EskizSendSmsRequestDto(phoneNumber, message), token);
                return true;
            } catch (Exception ex) {
                log.error("Esxeption happened while refreshing eskiz jwt token", ex);
                return false;
            }
        } catch (Exception e) {
            log.error("Unable to send SMS to number. Exception happened", e);
            return false;
        }
    }
}
