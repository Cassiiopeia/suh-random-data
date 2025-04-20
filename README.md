# Suh Nickname Generator

랜덤 닉네임을 생성하는 Java 라이브러리입니다.

## 기능

- 랜덤 닉네임 생성: 형용사와 명사를 조합해 자연스러운 닉네임 생성
- 이름 기반 닉네임 생성: 사용자 이름을 기반으로 일관된 닉네임 생성

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
    implementation 'me.suhsaechan:suh-nickname-generator:0.0.6'
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
        <artifactId>suh-nickname-generator</artifactId>
        <version>0.0.6</version>
    </dependency>
</dependencies>
```

## 기본 사용법

### Spring Boot 프로젝트에서 사용

```java
import me.suhsaechan.suhnicknamegenerator.service.SuhNicknameGenerator;
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
import me.suhsaechan.suhnicknamegenerator.service.SuhNicknameGenerator;

public class Example {
    public static void main(String[] args) {
        NicknameGeneratorService service = new NicknameGeneratorService();
        
        // 랜덤 닉네임 생성
        String randomNickname = service.generateRandomNickname();
        System.out.println("랜덤 닉네임: " + randomNickname);
        
        // 이름 기반 닉네임 생성
        String nameBasedNickname = service.generateNicknameFromName("홍길동");
        System.out.println("이름 기반 닉네임: " + nameBasedNickname);
    }
}
```

## 문서

- [API 문서](docs/api.md)
- [사용 예제](docs/usage.md)
- [변경 이력](docs/CHANGELOG.md)

## 라이선스

MIT License
