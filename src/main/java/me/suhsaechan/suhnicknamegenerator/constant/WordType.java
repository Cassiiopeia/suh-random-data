package me.suhsaechan.suhnicknamegenerator.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WordType {
  ADJECTIVES("adjectives"),
  NOUNS("nouns");

  private final String filename;
}
