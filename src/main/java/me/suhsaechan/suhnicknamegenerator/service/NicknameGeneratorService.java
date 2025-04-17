package me.suhsaechan.suhnicknamegenerator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 닉네임 생성을 위한 서비스 클래스입니다.
 * 이 클래스는 다른 프로젝트에서 라이브러리로 사용될 수 있습니다.
 */
@Slf4j
@Service
public class NicknameGeneratorService {

    private final Random random = new Random();
    
    // 닉네임 형용사 목록 예시
    private final List<String> adjectives = Arrays.asList(
            "행복한", "즐거운", "멋진", "귀여운", "사랑스러운", 
            "현명한", "용감한", "씩씩한", "친절한", "재미있는"
    );
    
    // 닉네임 명사 목록 예시
    private final List<String> nouns = Arrays.asList(
            "호랑이", "토끼", "사자", "판다", "코끼리", 
            "여우", "늑대", "고양이", "강아지", "원숭이"
    );
    
    /**
     * 랜덤 닉네임을 생성합니다.
     * @return 생성된 닉네임
     */
    public String generateRandomNickname() {
        String adjective = adjectives.get(random.nextInt(adjectives.size()));
        String noun = nouns.get(random.nextInt(nouns.size()));
        
        String nickname = adjective + " " + noun;
        log.info("생성된 닉네임: {}", nickname);
        
        return nickname;
    }
    
    /**
     * 이름을 기반으로 닉네임을 생성합니다.
     * @param name 사용자 이름
     * @return 생성된 닉네임
     */
    public String generateNicknameFromName(String name) {
        if (name == null || name.isEmpty()) {
            return generateRandomNickname();
        }
        
        // 이름의 첫 글자를 사용해 형용사 선택
        int index = Math.abs(name.charAt(0)) % adjectives.size();
        String adjective = adjectives.get(index);
        
        // 이름의 마지막 글자를 사용해 명사 선택
        index = Math.abs(name.charAt(name.length() - 1)) % nouns.size();
        String noun = nouns.get(index);
        
        String nickname = adjective + " " + noun;
        log.info("{}님을 위한 닉네임: {}", name, nickname);
        
        return nickname;
    }
}
