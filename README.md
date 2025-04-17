# Suh Nickname Generator

랜덤 닉네임을 생성하는 Java 라이브러리입니다.

## 기능

- 랜덤 닉네임 생성
- 이름 기반 닉네임 생성

## 사용 방법

### Gradle 의존성 추가

```gradle
repositories {
    maven {
        url "http://suh-project.synology.me:9999/repository/maven-releases/"
        // 인증이 필요한 경우
        credentials {
            username = "사용자명"
            password = "비밀번호"
        }
    }
}

dependencies {
    implementation 'me.suhsaechan:suh-nickname-generator:0.0.1-SNAPSHOT'
}
```

### 코드 예제

```java
import me.suhsaechan.suhnicknamegenerator.service.NicknameGeneratorService;

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

## 직접 빌드 및 배포

### 로컬 빌드

```bash
./gradlew build
```

### 로컬 Maven 저장소에 배포

```bash
./gradlew publishToMavenLocal
```

### Nexus 저장소에 자동 배포

이 프로젝트는 GitHub Actions를 통해 Git 태그 기반으로 자동 배포됩니다:

1. build.gradle 파일에서 버전을 업데이트합니다:
   ```groovy
   version = '1.0.0' // SNAPSHOT 제거
   ```

2. 변경사항을 커밋하고 푸시합니다:
   ```bash
   git add .
   git commit -m "버전 1.0.0 릴리스 준비"
   git push origin main
   ```

3. 커밋에 태그를 생성하고 푸시합니다:
   ```bash
   git tag v1.0.0
   git push origin v1.0.0
   ```

4. 태그가 푸시되면 GitHub Actions가 자동으로 Nexus 저장소에 라이브러리를 배포합니다.

5. 배포 후 다음 개발 버전으로 업데이트:
   ```groovy
   version = '1.0.1-SNAPSHOT' // 다음 개발 버전
   ```

### 수동 Nexus 배포 (개발용)

```bash
./gradlew publish -PnexusUrl=your-nexus-server -PnexusPort=9999 -PnexusUsername=your-username -PnexusPassword=your-password
```

## 라이선스

MIT License
