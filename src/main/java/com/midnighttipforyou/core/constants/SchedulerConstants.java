package com.midnighttipforyou.core.constants;

/**
 * 스케줄링 관련 상수 정의
 */
public final class SchedulerConstants {
    
    private SchedulerConstants() {
        // 유틸리티 클래스이므로 인스턴스화 방지
    }
    
    // ========================================
    // 기본 스케줄 시간 (Cron 표현식)
    // ========================================
    
    /**
     * 매일 밤 11시 59분 (기본값)
     */
    public static final String DEFAULT_MIDNIGHT_CRON = "0 59 23 * * *";
    
    /**
     * 매일 자정 (기본값)
     */
    public static final String DEFAULT_STATUS_CHECK_CRON = "0 0 0 * * *";
    
    /**
     * 매일 오후 6시
     */
    public static final String EVENING_6PM_CRON = "0 0 18 * * *";
    
    /**
     * 매일 오후 9시
     */
    public static final String EVENING_9PM_CRON = "0 0 21 * * *";
    
    /**
     * 매시간
     */
    public static final String EVERY_HOUR_CRON = "0 0 * * * *";
    
    /**
     * 매 30분마다
     */
    public static final String EVERY_30_MINUTES_CRON = "0 */30 * * * *";
    
    /**
     * 매 15분마다
     */
    public static final String EVERY_15_MINUTES_CRON = "0 */15 * * * *";
    
    // ========================================
    // 스케줄러 타입 기본값
    // ========================================
    
    /**
     * 기본 스케줄러 타입
     */
    public static final String DEFAULT_SCHEDULER_TYPE = "tasklet";
    
    /**
     * Quartz 스케줄러 타입
     */
    public static final String QUARTZ_SCHEDULER_TYPE = "quartz";
    
    /**
     * Tasklet 스케줄러 타입
     */
    public static final String TASKLET_SCHEDULER_TYPE = "tasklet";
    
    /**
     * Simple 스케줄러 타입
     */
    public static final String SIMPLE_SCHEDULER_TYPE = "simple";
    
    // ========================================
    // 스케줄러 그룹명
    // ========================================
    
    /**
     * 꿀팁 관련 Job 그룹
     */
    public static final String TIP_GROUP = "tipGroup";
    
    /**
     * 상태 확인 관련 Job 그룹
     */
    public static final String STATUS_GROUP = "statusGroup";
    
    /**
     * 시스템 관련 Job 그룹
     */
    public static final String SYSTEM_GROUP = "systemGroup";
    
    // ========================================
    // Job 이름
    // ========================================
    
    /**
     * 개발 꿀팁 전송 Job
     */
    public static final String MIDNIGHT_TIP_JOB = "midnightTipJob";
    
    /**
     * 상태 확인 Job
     */
    public static final String STATUS_CHECK_JOB = "statusCheckJob";
    
    /**
     * 시스템 정리 Job
     */
    public static final String SYSTEM_CLEANUP_JOB = "systemCleanupJob";
    
    // ========================================
    // Trigger 이름
    // ========================================
    
    /**
     * 개발 꿀팁 전송 Trigger
     */
    public static final String MIDNIGHT_TIP_TRIGGER = "midnightTipTrigger";
    
    /**
     * 상태 확인 Trigger
     */
    public static final String STATUS_CHECK_TRIGGER = "statusCheckTrigger";
    
    /**
     * 시스템 정리 Trigger
     */
    public static final String SYSTEM_CLEANUP_TRIGGER = "systemCleanupTrigger";
    
    // ========================================
    // 스케줄러 설정 관련
    // ========================================
    
    /**
     * 스케줄러 활성화 여부 설정 키
     */
    public static final String SCHEDULER_ENABLED_KEY = "scheduler.enabled";
    
    /**
     * 스케줄러 타입 설정 키
     */
    public static final String SCHEDULER_TYPE_KEY = "scheduler.type";
    
    /**
     * 꿀팁 전송 시간 설정 키
     */
    public static final String SCHEDULER_MIDNIGHT_CRON_KEY = "scheduler.midnight.cron";
    
    /**
     * 상태 확인 시간 설정 키
     */
    public static final String SCHEDULER_STATUS_CHECK_CRON_KEY = "scheduler.status-check.cron";
    
    // ========================================
    // 스케줄러 성능 관련
    // ========================================
    
    /**
     * 기본 스레드 풀 크기
     */
    public static final int DEFAULT_THREAD_POOL_SIZE = 5;
    
    /**
     * 운영 환경 스레드 풀 크기
     */
    public static final int PRODUCTION_THREAD_POOL_SIZE = 10;
    
    /**
     * 스레드 우선순위 (1-10, 5가 기본값)
     */
    public static final int DEFAULT_THREAD_PRIORITY = 5;
    
    /**
     * Job 실행 타임아웃 (밀리초)
     */
    public static final long JOB_EXECUTION_TIMEOUT = 30000; // 30초
    
    /**
     * 스케줄러 시작 지연 시간 (초)
     */
    public static final int SCHEDULER_STARTUP_DELAY = 5;
}
