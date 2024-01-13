package com.example.leetcodeclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
@EnableFeignClients
@EnableJpaAuditing
public class LeetCodeCloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeetCodeCloneApplication.class, args);

        /*
            sign in
            sign up
            sms verification
            forget password
            authorization
            block user submission
            oath2
            authenticator
            password expiretion
        */
    }

}
