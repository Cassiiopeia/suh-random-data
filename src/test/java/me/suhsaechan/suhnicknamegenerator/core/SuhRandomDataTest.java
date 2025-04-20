package me.suhsaechan.suhnicknamegenerator.core;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class SuhRandomDataTest {

  @Test
  public void test() {
    testGenerate();
  }

  void testGenerate() {
    SuhRandomData suhRandomData = SuhRandomData.builder()
        .local("ko")
        .build();
    String generate = suhRandomData.simpleNickname();
    String generateWithNumber = suhRandomData.nicknameWithNumber();
    String generateWithUuid = suhRandomData.nicknameWithUuid();

    log.info("========================");
    log.info("simpleNickname: {}", generate);
    log.info("nicknameWithNumber: {}", generateWithNumber);
    log.info("nicknameWithUuid: {}", generateWithUuid);
    log.info("========================");
  }


}