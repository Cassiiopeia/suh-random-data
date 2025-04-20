package me.suhsaechan.suhnicknamegenerator.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import me.suhsaechan.suhnicknamegenerator.constant.WordType;

public class DataLoader {
  private static final String TEMPLATE = "data/%s/%s.txt";
  private static final ConcurrentHashMap<String,List<String>> CACHE = new ConcurrentHashMap<>();

  public static List<String> loadWords(WordType type, Locale locale) {
    String lang = locale.getLanguage();              // "ko", "en", ...
    String key  = lang + "|" + type.name();
    return CACHE.computeIfAbsent(key, k -> {
      String path = String.format(TEMPLATE, lang, type.getFilename());
      try (InputStream is = DataLoader.class
          .getClassLoader()
          .getResourceAsStream(path)) {
        if (is == null) {
          throw new IllegalStateException("Resource not found: " + path);
        }
        try (BufferedReader r = new BufferedReader(
            new InputStreamReader(is, StandardCharsets.UTF_8))) {
          return r.lines()
              .map(String::trim)
              .filter(l -> !l.isEmpty())
              .collect(Collectors.toList());
        }
      } catch (IOException e) {
        throw new RuntimeException("Failed to load " + path, e);
      }
    });
  }
}
