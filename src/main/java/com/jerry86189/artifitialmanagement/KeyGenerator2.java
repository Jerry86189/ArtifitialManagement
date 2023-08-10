package com.jerry86189.artifitialmanagement;

import java.util.Random;
import java.util.stream.Collectors;

/**
 * ClassName: KeyGenerator2
 * Description: TODO
 * date: 2023/08/07 14:43
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
public class KeyGenerator2 {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static void main(String[] args) {
        String key = new Random().ints(64, 0, CHARACTERS.length())
                .mapToObj(i -> CHARACTERS.charAt(i))
                .map(Object::toString)
                .collect(Collectors.joining());
        System.out.println("Generated key: " + key);
    }
}
