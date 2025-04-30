package me.suhsaechan.suhnicknamegenerator.core;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class SuhRandomKitTest {

  @Test
  public void mainTest(){
//    nickName_테스트();
//    matureNickName_테스트();
    politicianNickName_테스트();
  }

  private void nickName_테스트() {
    SuhRandomKit suhRandomKit = SuhRandomKit.builder()
        .locale("ko")        // 한/영/코드 모두 OK
        .numberLength(4)                // 4자리 숫자
        .uuidLength(4)                  // 4자리 UUID
        .build();

    log.info("========================");
    log.info("simpleNickname:       {}", suhRandomKit.simpleNickname());
    log.info("nicknameWithNumber:   {}", suhRandomKit.nicknameWithNumber());
    log.info("nicknameWithUuid:     {}", suhRandomKit.nicknameWithUuid());
    log.info("========================");
  }

  private void matureNickName_테스트(){
    SuhRandomKit suhRandomKit = SuhRandomKit.builder()
        .locale("ko")        // 한/영/코드 모두 OK
        .numberLength(4)                // 4자리 숫자
        .uuidLength(4)                  // 4자리 UUID
        .enableAdultContent(true)
        .build();

    log.info("========================");
    log.info("matureNickname:       {}", suhRandomKit.matureNickname());
    log.info("matureNicknameWithNumber:   {}", suhRandomKit.matureNicknameWithNumber());
    log.info("matureNicknameWithUuid:     {}", suhRandomKit.matureNicknameWithUuid());
    log.info("========================");

  }

  private void politicianNickName_테스트(){
    SuhRandomKit suhRandomKit = SuhRandomKit.builder()
        .locale("ko")        // 한/영/코드 모두 OK
        .numberLength(4)                // 4자리 숫자
        .uuidLength(4)                  // 4자리 UUID
        .enableAdultContent(true)
        .build();

    log.info("========================");
    log.info("politicianNickname:       {}", suhRandomKit.politicianNickname());
    log.info("politicianNicknameWithNumber:   {}", suhRandomKit.politicianNicknameWithNumber());
    log.info("politicianNicknameWithUuid:     {}", suhRandomKit.politicianNicknameWithUuid());
    log.info("========================");

  }
}
