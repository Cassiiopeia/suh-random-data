# API 문서

## NicknameGeneratorService

주요 서비스 클래스로 닉네임 생성 기능을 제공합니다.

### 생성자

```java
public NicknameGeneratorService()
```

기본 생성자입니다. 미리 정의된 형용사와 명사 목록으로 초기화합니다.

### 메소드

#### generateRandomNickname

```java
public String generateRandomNickname()
```

**설명**: 랜덤 형용사와 명사를 조합하여 닉네임을 생성합니다.

**반환값**: 생성된 닉네임 (String)

**예제**:
```java
String nickname = service.generateRandomNickname();
// 결과 예시: "행복한 호랑이", "귀여운 판다" 등
```

#### generateNicknameFromName

```java
public String generateNicknameFromName(String name)
```

**설명**: 사용자 이름을 기반으로 일관된 닉네임을 생성합니다. 같은 이름에 대해 항상 동일한 닉네임을 반환합니다.

**매개변수**:
- name: 사용자 이름 (String)

**반환값**: 생성된 닉네임 (String)

**예제**:
```java
String nickname = service.generateNicknameFromName("홍길동");
// 이름의 첫 글자와 마지막 글자를 기반으로 닉네임 생성
```

**주의사항**:
- name 매개변수가 null이거나 빈 문자열인 경우 `generateRandomNickname()`를 호출하여 랜덤 닉네임을 반환합니다.
