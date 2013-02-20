package com.petrpopov.yourtracker.security;

import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;

/**
 * User: petrpopov
 * Date: 15.02.13
 * Time: 12:52
 */
@Component
public class MyTextEncryptor implements TextEncryptor {
    @Override
    public String encrypt(String text) {
        return text;
    }

    @Override
    public String decrypt(String encryptedText) {
        return encryptedText;
    }
}
