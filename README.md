# Suh Random Engine

랜덤 객체를 생성하는 Java 라이브러리입니다.

## 기능

- 랜덤 닉네임 생성: 형용사와 명사를 조합해 자연스러운 닉네임 생성

## 설치 방법

### Gradle

```gradle
repositories {
    mavenCentral()
    // Suh-Nexus 추가
    maven {
        url "http://suh-project.synology.me:9999/repository/maven-releases/"
        allowInsecureProtocol = true
    }
}

dependencies {
    implementation 'me.suhsaechan:suh-random-engine:1.0.1'
}
```

### Maven

```xml
<repositories>
    <repository>
        <id>suh-nexus</id>
        <url>http://suh-project.synology.me:9999/repository/maven-releases/</url>
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>me.suhsaechan</groupId>
        <artifactId>suh-random-engine</artifactId>
        <version>1.0.1</version>
    </dependency>
</dependencies>
```

## 기본 사용법

### Spring Boot 프로젝트에서 사용

```java
import me.suhsaechan.suhnicknamegenerator.core.SuhRandomKit;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private final NicknameGeneratorService nicknameGeneratorService;
    
    public UserService(NicknameGeneratorService nicknameGeneratorService) {
        this.nicknameGeneratorService = nicknameGeneratorService;
    }
    
    public String generateNickname(String name) {
        return nicknameGeneratorService.generateNicknameFromName(name);
    }
}
```

### 일반 Java 프로젝트에서 사용

```java
import me.suhsaechan.suhnicknamegenerator.core.SuhRandomKit;

public class Example {
  public static void main(String[] args) {
    // 기본 한국어 닉네임 생성
    SuhRandomKit generator = SuhRandomKit.builder()
        .locale("ko") // "ko", "한글", "KOREAN" 등 지원
        .numberLength(4) // 숫자 접미사 길이
        .uuidLength(4)   // UUID 접미사 길이
        .build();

    // 랜덤 닉네임 생성
    String simpleNickname = generator.simpleNickname();
    System.out.println("기본 닉네임: " + simpleNickname); // 예: 멋진고양이

    // 숫자 접미사 닉네임
    String numberedNickname = generator.nicknameWithNumber();
    System.out.println("숫자 닉네임: " + numberedNickname); // 예: 멋진고양이-1234

    // UUID 접미사 닉네임
    String uuidNickname = generator.nicknameWithUuid();
    System.out.println("UUID 닉네임: " + uuidNickname); // 예: 멋진고양이-abcd
  }
}
```
### 스프링부트에서 사용 
```java
import me.suhsaechan.suhnicknamegenerator.core.SuhRandomKit;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final SuhRandomKit nicknameGenerator;

    public UserService() {
        this.nicknameGenerator = SuhRandomKit.builder()
            .locale("ko")
            .numberLength(4)
            .uuidLength(4)
            .build();
    }

    public String generateSimpleNickname() {
        return nicknameGenerator.simpleNickname();
    }

    public String generateNumberedNickname() {
        return nicknameGenerator.nicknameWithNumber();
    }

    public String generateUuidNickname() {
        return nicknameGenerator.nicknameWithUuid();
    }
}
```

### Locale 설정
```java
SuhRandomKit generator = SuhRandomKit.builder()
    .locale("en") // 영어 닉네임 (예: CoolCat)
    .build();
String englishNickname = generator.simpleNickname();
System.out.println("영어 닉네임: " + englishNickname);
```

### 접미사 길이 설정
```java
SuhRandomKit generator = SuhRandomKit.builder()
    .locale("ko")
    .numberLength(6) // 6자리 숫자
    .uuidLength(8)   // 8자리 UUID
    .build();
String longNumberNickname = generator.nicknameWithNumber(); // 예: 멋진고양이-123456
String longUuidNickname = generator.nicknameWithUuid();     // 예: 멋진고양이-abcdef12
```
## 문서

- [API 문서](docs/api.md)
- [사용 예제](docs/usage.md)
- [변경 이력](docs/CHANGELOG.md)

## 라이선스

MIT License
