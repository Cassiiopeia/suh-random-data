package me.suhsaechan.suhnicknamegenerator.core;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import me.suhsaechan.suhnicknamegenerator.constant.WordType;
import me.suhsaechan.suhnicknamegenerator.util.DataLoader;
import me.suhsaechan.suhnicknamegenerator.util.LocaleResolver;

public final class SuhRandomKit {
    private final Locale locale;
    private final Random random;
    private final int numberLength;
    private final int uuidLength;

    private SuhRandomKit(Builder b) {
        this.locale       = b.locale;
        this.random       = b.random;
        this.numberLength = b.numberLength;
        this.uuidLength   = b.uuidLength;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Locale locale = LocaleResolver.resolve("ko");
        private Random random = new Random();
        private int numberLength = 4;
        private int uuidLength = 4;

        /** "ko","KO","Korean","한글","한국어" 등 모두 지원 */
        public Builder locale(String localeStr) {
            this.locale = LocaleResolver.resolve(localeStr);
            return this;
        }

        /** 숫자 접미사 자리 수 설정 */
        public Builder numberLength(int length) {
            if (length < 1) throw new IllegalArgumentException("length must be >=1");
            this.numberLength = length;
            return this;
        }

        /** UUID 접미사 길이 설정 */
        public Builder uuidLength(int length) {
            if (length < 1) throw new IllegalArgumentException("length must be >=1");
            this.uuidLength = length;
            return this;
        }

        public SuhRandomKit build() {
            return new SuhRandomKit(this);
        }
    }

    /** 형용사 + 명사 */
    public String simpleNickname() {
        List<String> adj  = DataLoader.loadWords(WordType.ADJECTIVES, locale);
        List<String> noun = DataLoader.loadWords(WordType.NOUNS,      locale);
        return adj.get(random.nextInt(adj.size()))
            + noun.get(random.nextInt(noun.size()));
    }

    /** 기본 설정 숫자 길이로 접미사 */
    public String nicknameWithNumber() {
        return nicknameWithNumber(numberLength);
    }

    /** 지정된 길이로 숫자 접미사 */
    public String nicknameWithNumber(int len) {
        int max = (int) Math.pow(10, len);
        String fmt = "%0" + len + "d";
        return simpleNickname() + "-" + String.format(fmt, random.nextInt(max));
    }

    /** 기본 UUID 길이로 접미사 */
    public String nicknameWithUuid() {
        return nicknameWithUuid(uuidLength);
    }

    /** 지정된 길이만큼 UUID 접미사 */
    public String nicknameWithUuid(int len) {
        String u = UUID.randomUUID().toString().replace("-", "");
        if (len > u.length()) len = u.length();
        return simpleNickname() + "-" + u.substring(0, len);
    }
}
