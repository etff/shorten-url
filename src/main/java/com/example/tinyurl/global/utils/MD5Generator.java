package com.example.tinyurl.global.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Generator {

    private MD5Generator() {
    }

    public static String convertToHash(String word) {
        String MD5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(word.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            MD5 = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            MD5 = null;
        }
        return subString(MD5);
    }

    private static String subString(String word) {
        return word.substring(0, 8);
    }
}
