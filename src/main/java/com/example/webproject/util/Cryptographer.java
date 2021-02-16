package com.example.webproject.util;

import com.example.webproject.exception.EncryptionException;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cryptographer {
    private static final String ALGORITHM = "SHA-512";

    public String encrypt(String line) throws EncryptionException {
        try {
            MessageDigest digester = MessageDigest.getInstance(ALGORITHM);
            byte[] input = line.getBytes(StandardCharsets.UTF_8);
            byte[] digest = digester.digest(input);
            String encryptedPassword = DatatypeConverter.printHexBinary(digest);
            return encryptedPassword;
        } catch (NoSuchAlgorithmException e) {
            throw new EncryptionException(e);
        }
    }
}
