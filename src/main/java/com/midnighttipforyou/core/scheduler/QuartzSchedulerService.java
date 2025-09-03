package com.midnighttipforyou.core.scheduler;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuartzSchedulerService {
    
    private final Scheduler scheduler;
    
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    @PostConstruct
    public void initializeJobs() {
        try {
            scheduler.clear(); // 기존 Job 삭제 (중복 방지)
            log.info("🌙 Quartz 스케줄러 초기화 완료");
        } catch (SchedulerException e) {
            log.error("Quartz 스케줄러 초기화 실패", e);
        }
    }
    
    /**
     * 특정 Job을 즉시 실행
     */
    public void triggerJobNow(String jobName, String groupName) {
        try {
            scheduler.triggerJob(new JobKey(jobName, groupName));
            log.info("🚀 Job 즉시 실행: {} (그룹: {})", jobName, groupName);
        } catch (SchedulerException e) {
            log.error("Job 실행 실패: {} (그룹: {})", jobName, groupName, e);
            throw new RuntimeException("Job 실행 실패", e);
        }
    }
    
    /**
     * Job 스케줄 일시정지
     */
    public void pauseJob(String jobName, String groupName) {
        try {
            scheduler.pauseJob(new JobKey(jobName, groupName));
            log.info("⏸️ Job 일시정지: {} (그룹: {})", jobName, groupName);
        } catch (SchedulerException e) {
            log.error("Job 일시정지 실패: {} (그룹: {})", jobName, groupName, e);
            throw new RuntimeException("Job 일시정지 실패", e);
        }
    }
    
    /**
     * Job 스케줄 재개
     */
    public void resumeJob(String jobName, String groupName) {
        try {
            scheduler.resumeJob(new JobKey(jobName, groupName));
            log.info("▶️ Job 재개: {} (그룹: {})", jobName, groupName);
        } catch (SchedulerException e) {
            log.error("Job 재개 실패: {} (그룹: {})", jobName, groupName, e);
            throw new RuntimeException("Job 재개 실패", e);
        }
    }
    
    /**
     * Job 스케줄 삭제
     */
    public void deleteJob(String jobName, String groupName) {
        try {
            scheduler.deleteJob(new JobKey(jobName, groupName));
            log.info("🗑️ Job 삭제: {} (그룹: {})", jobName, groupName);
        } catch (SchedulerException e) {
            log.error("Job 삭제 실패: {} (그룹: {})", jobName, groupName, e);
            throw new RuntimeException("Job 삭제 실패", e);
        }
    }

    /**
     * 모든 Job 정보 조회
     */
    public void listAllJobs() {
        try {
            log.info("📋 등록된 모든 Job 목록:");
            for (String groupName : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                    log.info("  - Job: {} (그룹: {}) - {}", 
                        jobKey.getName(), jobKey.getGroup(), jobDetail.getDescription());
                }
            }
        } catch (SchedulerException e) {
            log.error("Job 목록 조회 실패", e);
            throw new RuntimeException("Job 목록 조회 실패", e);
        }
    }
}
