package com.jerry86189.artifitialmanagement;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;

/**
 * ClassName: KeyGenerator
 * Description: TODO
 * date: 2023/06/10 11:00
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
public class KeyGenerator {
    public static void main(String[] args) {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println(encodedKey);
    }
}
