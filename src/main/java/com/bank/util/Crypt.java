package com.bank.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypt {

    public static String encryptText(String text) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(text.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = digest.digest();
        StringBuilder builder = new StringBuilder();
        for (int x = 0; x < bytes.length; x++) {
            builder.append(Integer.toString((bytes[x] & 0xff) + 0x100, 16).substring(1));
        }
        return builder.toString();
    }
}
