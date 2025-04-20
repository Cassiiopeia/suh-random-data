package me.suhsaechan.suhnicknamegenerator.core;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class SuhRandomKitTest {

  @Test
  void testGenerate() {
    SuhRandomKit kit = SuhRandomKit.builder()
        .locale("ko")        // 한/영/코드 모두 OK
        .numberLength(4)                // 4자리 숫자
        .uuidLength(4)                  // 4자리 UUID
        .build();

    log.info("========================");
    log.info("simpleNickname:       {}", kit.simpleNickname());
    log.info("nicknameWithNumber:   {}", kit.nicknameWithNumber());
    log.info("nicknameWithUuid:     {}", kit.nicknameWithUuid());
    log.info("========================");
  }
}
