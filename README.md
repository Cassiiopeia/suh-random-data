# Suh Random Engine

랜덤 객체를 생성하는 Java 라이브러리입니다.

## 기능

- 랜덤 닉네임 생성: 형용사와 명사를 조합해 자연스러운 닉네임 생성 (약 960,000 조합 가능)
- 성인용 랜덤 닉네임 생성 (v1.0.2): 성인용 형용사와 명사를 조합한 닉네임 생성 (만 19세 이상 사용 가능)
- 정치인 랜덤 닉네임 생성 (v1.0.3): 형용사와 정치인 이름을 조합한 특별한 닉네임 생성

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
    implementation 'me.suhsaechan:suh-random-engine:1.0.3'
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
        <version>1.0.3</version>
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

### 성인용 콘텐츠 사용 (v1.0.2 신규 기능)

> ⚠️ **주의사항**: 성인용 콘텐츠는 만 19세 이상만 사용할 수 있습니다.

```java
// 성인용 콘텐츠 활성화 (만 19세 이상만 사용 가능)
SuhRandomKit adultGenerator = SuhRandomKit.builder()
    .locale("ko")
    .enableAdultContent(true) // 성인용 콘텐츠 활성화 (중요: 사용자가 만 19세 이상인지 확인 필요)
    .build();

// 성인용 기본 닉네임
String adultNickname = adultGenerator.matureNickname();

// 성인용 닉네임 + 숫자
String adultNumberNickname = adultGenerator.matureNicknameWithNumber();

// 성인용 닉네임 + UUID
String adultUuidNickname = adultGenerator.matureNicknameWithUuid();
```

#### ✅ 성인용 콘텐츠 사용 시 필수 동의 사항

성인용 콘텐츠를 사용하기 위해 애플리케이션에서는 다음 사항에 대해 사용자의 명시적인 동의를 받아야 합니다:

1. 사용자가 만 19세 이상임을 확인
2. 성인용 콘텐츠의 사용에 대한 책임은 사용자에게 있음을 동의
3. 생성된 콘텐츠를 불법적인 용도로 사용하지 않을 것을 동의

**구현 예시:**
```java
// 예시: 성인 인증을 위한 체크박스나 버튼을 통해 사용자의 동의를 받은 후
boolean userConfirmedAdult = getUserConfirmation(); // 사용자 동의 여부 확인 메소드
boolean userAcceptedTerms = getUserTermsAcceptance(); // 이용약관 동의 여부 확인 메소드

if (userConfirmedAdult && userAcceptedTerms) {
    SuhRandomKit adultGenerator = SuhRandomKit.builder()
        .enableAdultContent(true) // 동의를 받은 후에만 활성화
        .build();
    
    String nickname = adultGenerator.matureNickname();
    // ...
} else {
    // 동의를 받지 못한 경우 일반 콘텐츠만 제공
    SuhRandomKit normalGenerator = SuhRandomKit.builder().build();
    String nickname = normalGenerator.simpleNickname();
    // ...
}
```

### 정치인 닉네임 생성 (v1.0.3 신규 기능)

```java
// 정치인 닉네임 생성
SuhRandomKit generator = SuhRandomKit.builder()
    .locale("ko") // 한국어 또는 영어("en") 지원
    .build();

// 기본 정치인 닉네임 (형용사 + 정치인 이름)
String politicianNickname = generator.politicianNickname();
System.out.println("정치인 닉네임: " + politicianNickname); // 예: 멋진윤석열

// 정치인 닉네임 + 숫자
String numberedPoliticianNickname = generator.politicianNicknameWithNumber();
System.out.println("숫자 정치인 닉네임: " + numberedPoliticianNickname); // 예: 빛나는이재명-1234

// 정치인 닉네임 + UUID
String uuidPoliticianNickname = generator.politicianNicknameWithUuid();
System.out.println("UUID 정치인 닉네임: " + uuidPoliticianNickname); // 예: 멋진이준석-abcd

// 사용자 정의 접미사 길이 지정
String customNumberedPolitician = generator.politicianNicknameWithNumber(6); // 6자리 숫자
String customUuidPolitician = generator.politicianNicknameWithUuid(8); // 8자리 UUID
```

> **참고**: 정치인 닉네임 기능은 현직 및 전직 정치인 이름을 사용하여 재미있는 닉네임을 생성합니다. 실제 정치적 견해나 의견을 대변하지 않으며, 순수하게 재미를 위한 기능입니다.

## 문서

- [API 문서](docs/api.md)
- [사용 예제](docs/usage.md)
- [변경 이력](docs/CHANGELOG.md)

## 변경 이력

### 1.0.3 (최신)
- 정치인 닉네임 생성 기능 추가
- 한국어/영어 정치인 이름 데이터셋 지원
- 정치인 닉네임에 대한 숫자 및 UUID 접미사 옵션 추가

### 1.0.2
- 성인용 랜덤 닉네임 생성 기능 추가 (만 19세 이상만 사용 가능)
- 성인용 콘텐츠 사용을 위한 동의 프로세스 추가
- 암호화된 성인용 단어 파일 형식 지원

### 1.0.1
- 기본 닉네임 생성 기능
- 여러 언어 지원 (한국어, 영어)
- 숫자 및 UUID 접미사 옵션

## 라이선스

MIT License
