package me.suhsaechan.suhnicknamegenerator.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WordType {
  ADJECTIVES("adjectives"),
  NOUNS("nouns"),
  ADULT_ADJECTIVES("adult_adjectives"),
  ADULT_NOUNS("adult_nouns");
  ;

  private final String filename;
}
