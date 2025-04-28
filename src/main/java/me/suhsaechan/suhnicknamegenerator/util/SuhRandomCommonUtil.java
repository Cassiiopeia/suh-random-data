package me.suhsaechan.suhnicknamegenerator.util;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

public class SuhRandomCommonUtil {
    private static final String DEFAULT_SEPARATOR = "-";
    private static final String SPECIAL_CHARS = "!@#$%^&*()_-+=<>?";
    private static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random random = new SecureRandom();

    // 닉네임에 랜덤 숫자 추가
    public static String appendNumber(String nickname, int length) {
        return appendNumber(nickname, length, DEFAULT_SEPARATOR);
    }

    public static String appendNumber(String nickname, int length, String separator) {
        int max = (int) Math.pow(10, length);
        String fmt = "%0" + length + "d";
        return nickname + separator + String.format(fmt, random.nextInt(max));
    }

    // 닉네임에 UUID 추가
    public static String appendUuid(String nickname, int length) {
        return appendUuid(nickname, length, DEFAULT_SEPARATOR);
    }

    public static String appendUuid(String nickname, int length, String separator) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        if (length > uuid.length()) {
            length = uuid.length();
        }
        return nickname + separator + uuid.substring(0, length);
    }

    // 닉네임에 특수문자 추가
    public static String appendSpecialChars(String nickname, int length) {
        return appendSpecialChars(nickname, length, DEFAULT_SEPARATOR);
    }

    public static String appendSpecialChars(String nickname, int length, String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));
        }
        return nickname + separator + sb.toString();
    }

    // 닉네임에 대문자 알파벳 추가
    public static String appendCapitalLetters(String nickname, int length) {
        return appendCapitalLetters(nickname, length, DEFAULT_SEPARATOR);
    }

    public static String appendCapitalLetters(String nickname, int length, String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(CAPITAL_LETTERS.charAt(random.nextInt(CAPITAL_LETTERS.length())));
        }
        return nickname + separator + sb.toString();
    }

    // 게임 태그 형식(# 사용)으로 숫자 추가
    public static String appendGameTag(String nickname, int length) {
        int max = (int) Math.pow(10, length);
        String fmt = "%0" + length + "d";
        return nickname + "#" + String.format(fmt, random.nextInt(max));
    }

    // 현재 시간 기반 타임스탬프 추가
    public static String appendTimestamp(String nickname) {
        return appendTimestamp(nickname, DEFAULT_SEPARATOR);
    }

    public static String appendTimestamp(String nickname, String separator) {
        long timestamp = System.currentTimeMillis() / 1000;
        return nickname + separator + timestamp;
    }
}