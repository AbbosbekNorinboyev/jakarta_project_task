package com.example.lesson10tasks.utils;

import jakarta.validation.constraints.NotNull;

import java.util.regex.Pattern;

public class StringUtils {
    public static final Pattern validEmailPattern = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    public static boolean validEmail(@NotNull String email) {
        return validEmailPattern.matcher(email).matches();
    }
}
