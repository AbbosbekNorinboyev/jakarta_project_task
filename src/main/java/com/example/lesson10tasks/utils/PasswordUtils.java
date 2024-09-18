package com.example.lesson10tasks.utils;

import jakarta.validation.constraints.NotNull;
import org.mindrot.jbcrypt.BCrypt;

import java.util.regex.Pattern;

public class PasswordUtils {
    public static final Pattern validEmailPattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    public static String encode(@NotNull String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

    public static boolean check(@NotNull String rawPassword, @NotNull String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}
