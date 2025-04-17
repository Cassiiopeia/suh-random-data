# 문제 해결

## 의존성 해결 실패

저장소 연결 문제 또는 의존성 해결에 실패할 경우 확인해야 할 사항:

### HTTP 저장소 접근 문제 (Gradle 7.0+)

Gradle 7.0 이상 버전에서는 HTTPS가 아닌 HTTP 저장소에 접근할 때 명시적인 허용이 필요합니다.

```gradle
maven {
    url "http://suh-project.synology.me:9999/repository/maven-releases/"
    allowInsecureProtocol = true  // 이 설정이 필요
}
```

### Maven에서 HTTP 저장소 접근

Maven의 최신 버전에서도 기본적으로 HTTPS를 권장하지만, HTTP 저장소에 접근할 수 있습니다.

### 네트워크 연결 문제

1. 저장소 URL에 접근 가능한지 확인: `http://suh-project.synology.me:9999/repository/maven-releases/`
2. 저장소가 호스팅된 서버가 작동 중인지 확인
3. 방화벽이 9999 포트에 대한 아웃바운드 연결을 허용하는지 확인

## 기타 문제

문제가 지속되면 GitHub 이슈를 통해 문의해주세요.
