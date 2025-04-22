package me.suhsaechan.suhnicknamegenerator.util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


@Slf4j
class EncryptionUtilTest {

  @Test
  public void mainTest() throws IOException {
    // 각 언어별 성인용 파일 암호화 수행
    encryptAdultFiles("ko"); // 한국어
    encryptAdultFiles("en"); // 영어
  }

  private void encryptAdultFiles(String lang) throws IOException {
    String basePath = "src/main/resources/data/" + lang + "/";
    String adjectivesSource = basePath + "adult_adjectives.txt";
    String nounsSource = basePath + "adult_nouns.txt";

    String adjectivesTarget = basePath + "adult_adjectives.enc";
    String nounsTarget = basePath + "adult_nouns.enc";

    // 원본 파일이 존재하는지 확인
    if (Files.exists(Paths.get(adjectivesSource))) {
      DataLoader.encryptWordFile(adjectivesSource, adjectivesTarget);
      System.out.println("형용사 파일 암호화: " + adjectivesTarget);
    }

    if (Files.exists(Paths.get(nounsSource))) {
      DataLoader.encryptWordFile(nounsSource, nounsTarget);
      System.out.println("명사 파일 암호화: " + nounsTarget);
    }
  }

}