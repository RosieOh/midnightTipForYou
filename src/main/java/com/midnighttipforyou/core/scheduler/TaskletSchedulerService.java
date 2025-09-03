package com.midnighttipforyou.core.scheduler;

import com.midnighttipforyou.domain.entity.DevelopmentTip;
import com.midnighttipforyou.domain.service.DevelopmentTipService;
import com.midnighttipforyou.domain.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskletSchedulerService {
    
    private final DevelopmentTipService developmentTipService;
    private final MessageService messageService;
    
    /**
     * Tasklet 방식으로 개발 꿀팁 전송
     */
    public void sendTipNow() {
        log.info("🚀 Tasklet 스케줄러 실행: 개발 꿀팁 전송");
        
        try {
            Optional<DevelopmentTip> tipOptional = developmentTipService.getRandomTip();
            
            if (tipOptional.isPresent()) {
                DevelopmentTip tip = tipOptional.get();
                log.info("오늘의 개발 꿀팁: {}", tip.getTitle());
                messageService.sendTipMessage(tip);
                log.info("✅ Tasklet 스케줄러 완료: 개발 꿀팁 전송 완료");
            } else {
                log.warn("전송할 개발 꿀팁이 없습니다.");
                messageService.sendErrorMessage("등록된 개발 꿀팁이 없습니다.");
            }
            
        } catch (Exception e) {
            log.error("Tasklet 스케줄러 실행 실패", e);
            messageService.sendErrorMessage(e.getMessage());
        }
    }
}
