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
            private static final String ENCRYPTED_TEMPLATE = "data/%s/%s.enc";
            private static final ConcurrentHashMap<String,List<String>> CACHE = new ConcurrentHashMap<>();

            public static List<String> loadWords(WordType type, Locale locale) {
              String lang = locale.getLanguage();              // "ko", "en", ...
              String key  = lang + "|" + type.name();
              return CACHE.computeIfAbsent(key, k -> {
                if (type == WordType.ADULT_ADJECTIVES || type == WordType.ADULT_NOUNS) {
                  return loadEncryptedWords(type, lang);
                } else {
                  return loadPlainWords(type, lang);
                }
              });
            }

            private static List<String> loadPlainWords(WordType type, String lang) {
              String path = String.format(TEMPLATE, lang, type.getFilename());
              try (InputStream is = DataLoader.class.getClassLoader().getResourceAsStream(path)) {
                if (is == null) {
                  throw new IllegalStateException("Resource not found: " + path);
                }
                try (BufferedReader r = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                  return r.lines()
                      .map(String::trim)
                      .filter(l -> !l.isEmpty())
                      .collect(Collectors.toList());
                }
              } catch (IOException e) {
                throw new RuntimeException("Failed to load " + path, e);
              }
            }

            private static List<String> loadEncryptedWords(WordType type, String lang) {
              String path = String.format(ENCRYPTED_TEMPLATE, lang, type.getFilename());
              try (InputStream is = DataLoader.class.getClassLoader().getResourceAsStream(path)) {
                if (is == null) {
                  throw new IllegalStateException("Encrypted resource not found: " + path);
                }
                try (BufferedReader r = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                  String encryptedContent = r.lines().collect(Collectors.joining("\n"));
                  String decryptedContent = EncryptionUtil.decrypt(encryptedContent);
                  return decryptedContent.lines()
                      .map(String::trim)
                      .filter(l -> !l.isEmpty())
                      .collect(Collectors.toList());
                }
              } catch (IOException e) {
                throw new RuntimeException("Failed to load encrypted file " + path, e);
              }
            }

            // 암호화 파일 생성을 위한 유틸리티 메소드
            public static void encryptWordFile(String sourcePath, String targetPath) throws IOException {
              try (BufferedReader reader = new BufferedReader(new FileReader(sourcePath));
                   BufferedWriter writer = new BufferedWriter(new FileWriter(targetPath))) {
                String content = reader.lines().collect(Collectors.joining("\n"));
                String encrypted = EncryptionUtil.encrypt(content);
                writer.write(encrypted);
              }
            }
          }