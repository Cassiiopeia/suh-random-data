package me.suhsaechan.suhnicknamegenerator.core;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import me.suhsaechan.suhnicknamegenerator.constant.WordType;
import me.suhsaechan.suhnicknamegenerator.util.DataLoader;
import me.suhsaechan.suhnicknamegenerator.util.LocaleResolver;

public final class SuhRandomData {
    private final Locale locale;
    private final Random random;
    private final int defaultNumberLength;
    private final int defaultUuidLength;

    private SuhRandomData(
        Locale locale,
        int defaultNumberLength,
        int defaultUuidLength
    ) {
        this.locale = locale;
        this.random = new Random();
        this.defaultNumberLength = defaultNumberLength;
        this.defaultUuidLength   = defaultUuidLength;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Locale locale = LocaleResolver.resolve("ko");
        private int numberLength = 4;  // 기본 4자리
        private int uuidLength   = 4;  // 기본 4자리

        /** ko, KO, Korean, 한글, 한국어 등 모두 지원 */
        public Builder local(String localeStr) {
            this.locale = LocaleResolver.resolve(localeStr);
            return this;
        }

        /** 번호 뒤에 붙일 숫자 자리 수 지정 (기본 4) */
        public Builder numberLength(int length) {
            if (length < 1) throw new IllegalArgumentException("length must be >= 1");
            this.numberLength = length;
            return this;
        }

        /** UUID 접미사로 사용할 문자 수 지정 (기본 4) */
        public Builder uuidLength(int length) {
            if (length < 1) throw new IllegalArgumentException("length must be >= 1");
            this.uuidLength = length;
            return this;
        }

        public SuhRandomData build() {
            return new SuhRandomData(locale, numberLength, uuidLength);
        }
    }

    /** 형용사 + 명사 */
    public String simpleNickname() {
        List<String> adj  = DataLoader.loadWords(WordType.ADJECTIVES, locale);
        List<String> noun = DataLoader.loadWords(WordType.NOUNS,      locale);
        return adj.get(random.nextInt(adj.size()))
            + noun.get(random.nextInt(noun.size()));
    }

    public String nicknameWithNumber() {
        return nicknameWithNumber(defaultNumberLength);
    }

    public String nicknameWithNumber(int length) {
        int max = (int) Math.pow(10, length);
        String format = "%0" + length + "d";
        return simpleNickname() + "-" + String.format(format, random.nextInt(max));
    }

    public String nicknameWithUuid() {
        return nicknameWithUuid(defaultUuidLength);
    }

    public String nicknameWithUuid(int length) {
        String uuid = UUID.randomUUID()
            .toString()
            .replace("-", "");
        if (length > uuid.length()) {
            length = uuid.length();
        }
        return simpleNickname() + "-" + uuid.substring(0, length);
    }
}
