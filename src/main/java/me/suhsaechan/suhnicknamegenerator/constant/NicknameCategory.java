package me.suhsaechan.suhnicknamegenerator.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NicknameCategory {
  COMMON(WordType.ADJECTIVES, WordType.NOUNS, false),
  ADULT(WordType.ADULT_ADJECTIVES, WordType.ADULT_NOUNS, true),
  POLITICIAN(WordType.ADJECTIVES, WordType.POLITICIAN_NOUNS, false),
  ;

  private final WordType adjectiveType;
  private final WordType nounType;
  private final boolean requiresAgeVerification;
}