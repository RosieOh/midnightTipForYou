package com.midnighttipforyou.domain.controller;

import com.midnighttipforyou.core.scheduler.TaskletSchedulerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/tasklet")
@RequiredArgsConstructor
@Slf4j
public class TaskletSchedulerController {
    
    private final TaskletSchedulerService taskletSchedulerService;
    
    /**
     * 개발 꿀팁 Tasklet 즉시 실행 (테스트용)
     */
    @PostMapping("/tips/trigger-now")
    public ResponseEntity<String> triggerMidnightTipTasklet() {
        try {
            taskletSchedulerService.sendTipNow();
            return ResponseEntity.ok("개발 꿀팁 Tasklet 즉시 실행 완료");
        } catch (Exception e) {
            log.error("개발 꿀팁 Tasklet 즉시 실행 실패", e);
            return ResponseEntity.internalServerError().body("Tasklet 실행 실패: " + e.getMessage());
        }
    }
    
    /**
     * Tasklet 스케줄러 상태 정보
     */
    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getTaskletSchedulerStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("message", "Tasklet 스케줄러가 정상 동작 중입니다.");
        status.put("timestamp", System.currentTimeMillis());
        status.put("availableTasklets", new String[]{
            "midnightTipTasklet - 매일 밤 11시 59분 개발 꿀팁 전송",
            "statusCheckTasklet - 매일 자정 스케줄러 상태 확인"
        });
        status.put("schedulerType", "Spring Batch Tasklet");
        status.put("advantages", new String[]{
            "간단하고 직관적인 구현",
            "Spring Batch와의 통합",
            "트랜잭션 관리 자동화",
            "Job 실행 이력 추적"
        });
        
        return ResponseEntity.ok(status);
    }
}
