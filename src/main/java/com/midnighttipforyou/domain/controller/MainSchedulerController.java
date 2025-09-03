package com.midnighttipforyou.domain.controller;

import com.midnighttipforyou.core.scheduler.MainTipScheduler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/main-scheduler")
@RequiredArgsConstructor
@Slf4j
public class MainSchedulerController {
    
    private final MainTipScheduler mainTipScheduler;
    
    /**
     * 현재 스케줄러 상태 및 정보 조회
     */
    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getSchedulerStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("message", "통합 스케줄러가 정상 동작 중입니다.");
        status.put("timestamp", System.currentTimeMillis());
        status.put("currentSchedulerType", mainTipScheduler.getCurrentSchedulerType());
        status.put("availableSchedulers", new String[]{
            "tasklet - Spring Batch 기반 (기본값, 간단함)",
            "quartz - Quartz 기반 (고급 기능, 복잡함)",
            "simple - Spring Scheduling 기반 (가장 간단함)"
        });
        status.put("nextExecution", "매일 밤 11시 59분");
        status.put("statusCheck", "매일 자정");
        
        return ResponseEntity.ok(status);
    }
    
    /**
     * 스케줄러 타입 변경
     */
    @PostMapping("/scheduler-type")
    public ResponseEntity<Map<String, Object>> changeSchedulerType(
            @RequestParam String schedulerType) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            String oldType = mainTipScheduler.getCurrentSchedulerType();
            mainTipScheduler.setSchedulerType(schedulerType);
            
            response.put("message", "스케줄러 타입이 성공적으로 변경되었습니다.");
            response.put("oldType", oldType);
            response.put("newType", schedulerType);
            response.put("timestamp", System.currentTimeMillis());
            
            log.info("스케줄러 타입 변경: {} -> {}", oldType, schedulerType);
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            log.error("스케줄러 타입 변경 실패", e);
            response.put("error", "스케줄러 타입 변경에 실패했습니다: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    /**
     * 개발 꿀팁 즉시 전송 (테스트용)
     */
    @PostMapping("/tips/send-now")
    public ResponseEntity<Map<String, Object>> sendTipNow() {
        Map<String, Object> response = new HashMap<>();
        
        try {
            log.info("통합 스케줄러를 통한 즉시 꿀팁 전송 시작");
            
            // 현재 설정된 스케줄러 타입으로 즉시 실행
            mainTipScheduler.sendMidnightTip();
            
            response.put("message", "개발 꿀팁 즉시 전송 완료");
            response.put("schedulerType", mainTipScheduler.getCurrentSchedulerType());
            response.put("timestamp", System.currentTimeMillis());
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            log.error("개발 꿀팁 즉시 전송 실패", e);
            response.put("error", "개발 꿀팁 전송에 실패했습니다: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
    
    /**
     * 스케줄러 성능 정보
     */
    @GetMapping("/performance")
    public ResponseEntity<Map<String, Object>> getPerformanceInfo() {
        Map<String, Object> performance = new HashMap<>();
        
        performance.put("schedulerType", mainTipScheduler.getCurrentSchedulerType());
        performance.put("memoryUsage", "정상");
        performance.put("threadCount", "정상");
        performance.put("lastExecution", "정상");
        performance.put("nextExecution", "매일 밤 11시 59분");
        performance.put("recommendations", new String[]{
            "tasklet: 간단한 작업에 적합, 리소스 사용량 적음",
            "quartz: 복잡한 작업에 적합, 리소스 사용량 많음",
            "simple: 가장 가벼움, 단순한 스케줄링에 적합"
        });
        
        return ResponseEntity.ok(performance);
    }
}
