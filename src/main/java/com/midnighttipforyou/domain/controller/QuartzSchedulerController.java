package com.midnighttipforyou.domain.controller;

import com.midnighttipforyou.core.scheduler.QuartzSchedulerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/scheduler")
@RequiredArgsConstructor
@Slf4j
public class QuartzSchedulerController {
    
    private final QuartzSchedulerService quartzSchedulerService;
    
    /**
     * 모든 Job 목록 조회
     */
    @GetMapping("/jobs")
    public ResponseEntity<String> listAllJobs() {
        try {
            quartzSchedulerService.listAllJobs();
            return ResponseEntity.ok("Job 목록이 로그에 출력되었습니다. 콘솔을 확인하세요.");
        } catch (Exception e) {
            log.error("Job 목록 조회 실패", e);
            return ResponseEntity.internalServerError().body("Job 목록 조회 실패: " + e.getMessage());
        }
    }
    
    /**
     * 특정 Job 즉시 실행
     */
    @PostMapping("/jobs/{jobName}/trigger")
    public ResponseEntity<String> triggerJobNow(
            @PathVariable String jobName,
            @RequestParam String groupName) {
        try {
            quartzSchedulerService.triggerJobNow(jobName, groupName);
            return ResponseEntity.ok("Job 즉시 실행 완료: " + jobName);
        } catch (Exception e) {
            log.error("Job 즉시 실행 실패", e);
            return ResponseEntity.internalServerError().body("Job 실행 실패: " + e.getMessage());
        }
    }
    
    /**
     * 특정 Job 일시정지
     */
    @PostMapping("/jobs/{jobName}/pause")
    public ResponseEntity<String> pauseJob(
            @PathVariable String jobName,
            @RequestParam String groupName) {
        try {
            quartzSchedulerService.pauseJob(jobName, groupName);
            return ResponseEntity.ok("Job 일시정지 완료: " + jobName);
        } catch (Exception e) {
            log.error("Job 일시정지 실패", e);
            return ResponseEntity.internalServerError().body("Job 일시정지 실패: " + e.getMessage());
        }
    }
    
    /**
     * 특정 Job 재개
     */
    @PostMapping("/jobs/{jobName}/resume")
    public ResponseEntity<String> resumeJob(
            @PathVariable String jobName,
            @RequestParam String groupName) {
        try {
            quartzSchedulerService.resumeJob(jobName, groupName);
            return ResponseEntity.ok("Job 재개 완료: " + jobName);
        } catch (Exception e) {
            log.error("Job 재개 실패", e);
            return ResponseEntity.internalServerError().body("Job 재개 실패: " + e.getMessage());
        }
    }
    
    /**
     * 특정 Job 삭제
     */
    @DeleteMapping("/jobs/{jobName}")
    public ResponseEntity<String> deleteJob(
            @PathVariable String jobName,
            @RequestParam String groupName) {
        try {
            quartzSchedulerService.deleteJob(jobName, groupName);
            return ResponseEntity.ok("Job 삭제 완료: " + jobName);
        } catch (Exception e) {
            log.error("Job 삭제 실패", e);
            return ResponseEntity.internalServerError().body("Job 삭제 실패: " + e.getMessage());
        }
    }
    
    /**
     * 개발 꿀팁 Job 즉시 실행 (테스트용)
     */
    @PostMapping("/tips/trigger-now")
    public ResponseEntity<String> triggerMidnightTipJobNow() {
        try {
            quartzSchedulerService.triggerJobNow("midnightTipJob", "tipGroup");
            return ResponseEntity.ok("개발 꿀팁 Job 즉시 실행 완료");
        } catch (Exception e) {
            log.error("개발 꿀팁 Job 즉시 실행 실패", e);
            return ResponseEntity.internalServerError().body("Job 실행 실패: " + e.getMessage());
        }
    }
    
    /**
     * 스케줄러 상태 정보
     */
    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getSchedulerStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("message", "Quartz 스케줄러가 정상 동작 중입니다.");
        status.put("timestamp", System.currentTimeMillis());
        status.put("availableJobs", new String[]{
            "midnightTipJob (tipGroup) - 매일 밤 11시 59분 개발 꿀팁 전송",
            "statusCheckJob (statusGroup) - 매일 자정 스케줄러 상태 확인"
        });
        
        return ResponseEntity.ok(status);
    }
}
