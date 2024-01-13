package com.example.leetcodeclone.user.otp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPRepository extends CrudRepository<OTP, String> {
}
