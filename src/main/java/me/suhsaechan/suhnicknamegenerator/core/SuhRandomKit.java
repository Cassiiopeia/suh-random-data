package me.suhsaechan.suhnicknamegenerator.core;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import me.suhsaechan.suhnicknamegenerator.constant.NicknameCategory;
import me.suhsaechan.suhnicknamegenerator.constant.WordType;
import me.suhsaechan.suhnicknamegenerator.util.DataLoader;
import me.suhsaechan.suhnicknamegenerator.util.LocaleResolver;

public final class SuhRandomKit {

  private final Locale locale;
  private final Random random;
  private final int numberLength;
  private final int uuidLength;
  private final boolean adultContentEnabled;

  private SuhRandomKit(Builder b) {
    this.locale = b.locale;
    this.random = b.random;
    this.numberLength = b.numberLength;
    this.uuidLength = b.uuidLength;
    this.adultContentEnabled = b.adultContentEnabled;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private Locale locale = LocaleResolver.resolve("ko");
    private Random random = new Random();
    private int numberLength = 4;
    private int uuidLength = 4;
    private boolean adultContentEnabled = false;

    /**
     * "ko","KO","Korean","한글","한국어" 등 모두 지원
     */
    public Builder locale(String localeStr) {
      this.locale = LocaleResolver.resolve(localeStr);
      return this;
    }

    /**
     * 숫자 접미사 자리 수 설정
     */
    public Builder numberLength(int length) {
      if (length < 1) {
        throw new IllegalArgumentException("length must be >=1");
      }
      this.numberLength = length;
      return this;
    }

    /**
     * UUID 접미사 길이 설정
     */
    public Builder uuidLength(int length) {
      if (length < 1) {
        throw new IllegalArgumentException("length must be >=1");
      }
      this.uuidLength = length;
      return this;
    }

    /**
     * 성인용 콘텐츠 활성화 설정
     */
    public Builder enableAdultContent(boolean enabled) {
      this.adultContentEnabled = enabled;
      return this;
    }

    public SuhRandomKit build() {
      return new SuhRandomKit(this);
    }
  }

  // 카테고리별 닉네임 생성 핵심 메소드
  private String generateNickname(NicknameCategory category) {
    // 성인인증 필요한 카테고리인 경우 확인
    if (category.isRequiresAgeVerification() && !adultContentEnabled) {
      throw new IllegalStateException(
          "성인용 콘텐츠가 활성화되지 않았습니다. 빌더 옵션에서 build().enableAdultContent(true)를 설정하여 활성화하실 수 있습니다. 19세 이상만 사용해주세요.");
    }

    List<String> adj = DataLoader.loadWords(category.getAdjectiveType(), locale);
    List<String> noun = DataLoader.loadWords(category.getNounType(), locale);

    return adj.get(random.nextInt(adj.size())) + noun.get(random.nextInt(noun.size()));
  }


  public String simpleNickname() {
    return generateNickname(NicknameCategory.COMMON);
  }

  public String matureNickname() {
    return generateNickname(NicknameCategory.ADULT);
  }

  // 정치인 닉네임 메소드
  public String politicianNickname() {
    return generateNickname(NicknameCategory.POLITICIAN);
  }

  // 접미사 추가 유틸리티 메소드
  private String appendNumber(String nickname, int length) {
    int max = (int) Math.pow(10, length);
    String fmt = "%0" + length + "d";
    return nickname + "-" + String.format(fmt, random.nextInt(max));
  }

  private String appendUuid(String nickname, int length) {
    String uuid = UUID.randomUUID().toString().replace("-", "");
    if (length > uuid.length()) {
      length = uuid.length();
    }
    return nickname + "-" + uuid.substring(0, length);
  }

  // 접미사 관련 메소드들 - 일반 닉네임
  public String nicknameWithNumber() {
    return appendNumber(simpleNickname(), numberLength);
  }

  public String nicknameWithNumber(int len) {
    return appendNumber(simpleNickname(), len);
  }

  public String nicknameWithUuid() {
    return appendUuid(simpleNickname(), uuidLength);
  }

  public String nicknameWithUuid(int len) {
    return appendUuid(simpleNickname(), len);
  }

  // 접미사 관련 메소드들 - 성인용 닉네임
  public String matureNicknameWithNumber() {
    return appendNumber(matureNickname(), numberLength);
  }

  public String matureNicknameWithNumber(int len) {
    return appendNumber(matureNickname(), len);
  }

  public String matureNicknameWithUuid() {
    return appendUuid(matureNickname(), uuidLength);
  }

  public String matureNicknameWithUuid(int len) {
    return appendUuid(matureNickname(), len);
  }

  // 접미사 관련 메소드들 - 정치인 닉네임
  public String politicianNicknameWithNumber() {
    return appendNumber(politicianNickname(), numberLength);
  }

  public String politicianNicknameWithNumber(int len) {
    return appendNumber(politicianNickname(), len);
  }

  public String politicianNicknameWithUuid() {
    return appendUuid(politicianNickname(), uuidLength);
  }

  public String politicianNicknameWithUuid(int len) {
    return appendUuid(politicianNickname(), len);
  }

  // 유틸리티 메소드
  public boolean isAdultContentEnabled() {
    return adultContentEnabled;
  }
}