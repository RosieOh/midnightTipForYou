package com.midnighttipforyou.core.scheduler;

import com.midnighttipforyou.core.constants.MessageTemplate;
import com.midnighttipforyou.core.constants.SchedulerConstants;
import com.midnighttipforyou.core.enums.SchedulerType;
import com.midnighttipforyou.core.scheduler.QuartzSchedulerService;
import com.midnighttipforyou.core.scheduler.TaskletSchedulerService;
import com.midnighttipforyou.domain.entity.DevelopmentTip;
import com.midnighttipforyou.domain.service.DevelopmentTipService;
import com.midnighttipforyou.domain.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class MainTipScheduler {
    
    private final DevelopmentTipService developmentTipService;
    private final MessageService messageService;
    
    // Optional 의존성 주입 - 빈이 없어도 에러가 발생하지 않음
    private final Optional<QuartzSchedulerService> quartzSchedulerService;
    private final Optional<TaskletSchedulerService> taskletSchedulerService;
    
    @Value("${scheduler.type:" + SchedulerConstants.DEFAULT_SCHEDULER_TYPE + "}")
    private String schedulerType;
    
    @Value("${scheduler.midnight.cron:" + SchedulerConstants.DEFAULT_MIDNIGHT_CRON + "}")
    private String midnightCron;
    
    @Value("${scheduler.status-check.cron:" + SchedulerConstants.DEFAULT_STATUS_CHECK_CRON + "}")
    private String statusCheckCron;
    
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(MessageTemplate.TIME_FORMAT);
    
    /**
     * 설정된 시간에 개발 꿀팁을 전송합니다.
     * 설정에 따라 다른 스케줄러를 사용합니다.
     */
    @Scheduled(cron = "${scheduler.midnight.cron:" + SchedulerConstants.DEFAULT_MIDNIGHT_CRON + "}")
    public void sendMidnightTip() {
        LocalDateTime now = LocalDateTime.now();
        log.info(String.format(MessageTemplate.LOG_SCHEDULER_START, 
                now.format(TIME_FORMATTER), "메인", schedulerType));
        
        try {
            switch (schedulerType.toLowerCase()) {
                case SchedulerConstants.QUARTZ_SCHEDULER_TYPE -> sendTipWithQuartz();
                case SchedulerConstants.TASKLET_SCHEDULER_TYPE -> sendTipWithTasklet();
                case SchedulerConstants.SIMPLE_SCHEDULER_TYPE -> sendTipWithSimple();
                default -> {
                    log.warn("알 수 없는 스케줄러 타입: {}. 기본 스케줄러를 사용합니다.", schedulerType);
                    sendTipWithSimple();
                }
            }
            
            log.info(String.format(MessageTemplate.LOG_SCHEDULER_COMPLETE, 
                    now.format(TIME_FORMATTER), "메인"));
            
        } catch (Exception e) {
            log.error(String.format(MessageTemplate.LOG_SCHEDULER_FAILED, "메인"), e);
            messageService.sendErrorMessage(e.getMessage());
        }
    }
    
    /**
     * 설정된 시간에 스케줄러 상태를 확인합니다.
     */
    @Scheduled(cron = "${scheduler.status-check.cron:" + SchedulerConstants.DEFAULT_STATUS_CHECK_CRON + "}")
    public void checkSchedulerStatus() {
        LocalDateTime now = LocalDateTime.now();
        log.info(String.format(MessageTemplate.LOG_STATUS_CHECK_START, 
                now.format(TIME_FORMATTER), "메인"));
        
        try {
            long activeTipCount = developmentTipService.getAllActiveTips().size();
            log.info("현재 활성화된 개발 꿀팁 개수: {}", activeTipCount);
            log.info("현재 사용 중인 스케줄러 타입: {}", schedulerType);
            log.info(String.format(MessageTemplate.LOG_STATUS_CHECK_COMPLETE, 
                    now.format(TIME_FORMATTER), "메인"));
            
        } catch (Exception e) {
            log.error("메인 스케줄러 상태 확인 실패", e);
        }
    }
    
    /**
     * Quartz를 사용하여 꿀팁 전송
     */
    private void sendTipWithQuartz() {
        quartzSchedulerService.ifPresentOrElse(
            service -> {
                try {
                    log.info("Quartz 스케줄러를 사용하여 꿀팁 전송");
                    service.triggerJobNow(SchedulerConstants.MIDNIGHT_TIP_JOB, SchedulerConstants.TIP_GROUP);
                } catch (Exception e) {
                    log.error("Quartz 스케줄러 실행 실패: {}", e.getMessage());
                    sendTipWithSimple();
                }
            },
            () -> {
                log.warn("Quartz 스케줄러가 등록되지 않았습니다. 기본 스케줄러를 사용합니다.");
                sendTipWithSimple();
            }
        );
    }
    
    /**
     * Tasklet을 사용하여 꿀팁 전송
     */
    private void sendTipWithTasklet() {
        taskletSchedulerService.ifPresentOrElse(
            service -> {
                try {
                    log.info("Tasklet 스케줄러를 사용하여 꿀팁 전송");
                    service.sendTipNow();
                } catch (Exception e) {
                    log.error("Tasklet 스케줄러 실행 실패: {}", e.getMessage());
                    sendTipWithSimple();
                }
            },
            () -> {
                log.warn("Tasklet 스케줄러가 등록되지 않았습니다. 기본 스케줄러를 사용합니다.");
                sendTipWithSimple();
            }
        );
    }
    
    /**
     * 간단한 방식으로 꿀팁 전송 (기본)
     */
    private void sendTipWithSimple() {
        log.info("간단한 스케줄러를 사용하여 꿀팁 전송");
        
        Optional<DevelopmentTip> tipOptional = developmentTipService.getRandomTip();
        
        if (tipOptional.isPresent()) {
            DevelopmentTip tip = tipOptional.get();
            log.info("오늘의 개발 꿀팁: {}", tip.getTitle());
            messageService.sendTipMessage(tip);
        } else {
            log.warn("전송할 개발 꿀팁이 없습니다.");
            messageService.sendErrorMessage("등록된 개발 꿀팁이 없습니다.");
        }
    }
    
    /**
     * 현재 스케줄러 타입 반환
     */
    public String getCurrentSchedulerType() {
        return schedulerType;
    }
    
    /**
     * 스케줄러 타입 변경 (런타임에 동적 변경 가능)
     */
    public void setSchedulerType(String newType) {
        this.schedulerType = newType;
        log.info("스케줄러 타입이 '{}'에서 '{}'로 변경되었습니다.", this.schedulerType, newType);
    }
}
