package me.suhsaechan.suhnicknamegenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 이 클래스는 라이브러리를 독립 애플리케이션으로 실행할 때만 사용됩니다.
 * 다른 프로젝트에서 라이브러리로 사용할 때는 이 클래스가 필요하지 않습니다.
 */
@SpringBootApplication
public class SuhNicknameGeneratorApplication {

  public static void main(String[] args) {
    SpringApplication.run(SuhNicknameGeneratorApplication.class, args);
  }

}
