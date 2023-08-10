package com.jerry86189.artifitialmanagement;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * ClassName: JwtSecretGenerator
 * Description: TODO
 * date: 2023/08/03 14:11
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
public class JwtSecretGenerator {
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        byte[] secret = new byte[32]; // 256 bits
        random.nextBytes(secret);
        String encodedSecret = Base64.getEncoder().encodeToString(secret);
        System.out.println("Your JWT secret: " + encodedSecret);
    }
}
