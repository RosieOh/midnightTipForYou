package com.midnighttipforyou.domain.service;

import com.midnighttipforyou.domain.entity.DevelopmentTip;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
    
    /**
     * 개발 꿀팁을 메시지 형태로 포맷팅합니다.
     */
    public String formatTipMessage(DevelopmentTip tip) {
        LocalDateTime now = LocalDateTime.now();
        String today = now.format(DATE_FORMATTER);
        
        StringBuilder message = new StringBuilder();
        message.append("🌙 ").append(today).append(" 자기 전 말아주는 오늘의 개발 꿀팁\n\n");
        message.append("📚 ").append(tip.getTitle()).append("\n\n");
        message.append("💡 ").append(tip.getContent()).append("\n\n");
        
        if (tip.getCategory() != null && !tip.getCategory().trim().isEmpty()) {
            message.append("🏷️  카테고리: ").append(tip.getCategory()).append("\n");
        }
        
        if (tip.getSource() != null && !tip.getSource().trim().isEmpty()) {
            message.append("📖 출처: ").append(tip.getSource()).append("\n");
        }
        
        message.append("\n🌙 좋은 꿈 꾸세요! 내일도 화이팅! 💪");
        
        return message.toString();
    }
    
    /**
     * 개발 꿀팁을 전송합니다. (현재는 콘솔 출력)
     */
    public void sendTipMessage(DevelopmentTip tip) {
        String message = formatTipMessage(tip);
        log.info("개발 꿀팁 메시지 전송:\n{}", message);
        
        // TODO: 실제 메시지 전송 로직 구현
        // - 카카오톡 API
        // - 이메일
        // - 슬랙 웹훅
        // - 텔레그램 봇 등
    }
    
    /**
     * 에러 메시지를 전송합니다.
     */
    public void sendErrorMessage(String errorMessage) {
        String message = "❌ 오늘의 개발 꿀팁을 가져오는 중 오류가 발생했습니다.\n\n" +
                        "오류 내용: " + errorMessage + "\n\n" +
                        "🌙 좋은 꿈 꾸세요! 내일도 화이팅! 💪";
        
        log.error("에러 메시지 전송:\n{}", message);
    }
}
