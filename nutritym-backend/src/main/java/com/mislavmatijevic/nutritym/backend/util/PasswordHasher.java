package com.mislavmatijevic.nutritym.backend.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordHasher
{
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String hash(String readablePassword)
    {
        return encoder.encode(readablePassword);
    }

    public static boolean checkHash(String readablePassword, String encodedPassword)
    {
        return encoder.matches(readablePassword, encodedPassword);
    }
}
