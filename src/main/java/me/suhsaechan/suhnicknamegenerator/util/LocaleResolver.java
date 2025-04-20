package me.suhsaechan.suhnicknamegenerator.util;

import java.util.Locale;
import java.util.Map;

/**
 * 빌더에서 받은 문자열을 java.util.Locale 로 변환
 * 예) "ko","KO","korean","한글","한국어" → Locale.KOREAN
 */
public class LocaleResolver {
  private static final Map<String,Locale> ALIASES = Map.of(
      "ko",       Locale.KOREAN,
      "kr",       Locale.KOREAN,
      "korean",   Locale.KOREAN,
      "한글",      Locale.KOREAN,
      "한국어",    Locale.KOREAN,
      "en",       Locale.ENGLISH,
      "us",       Locale.ENGLISH,
      "english",  Locale.ENGLISH,
      "영어",      Locale.ENGLISH
  );

  public static Locale resolve(String input) {
    if (input == null) {
      throw new IllegalArgumentException("Locale string must not be null");
    }
    Locale loc = ALIASES.get(input.trim().toLowerCase());
    if (loc == null) {
      throw new IllegalArgumentException("Unsupported locale: " + input);
    }
    return loc;
  }
}