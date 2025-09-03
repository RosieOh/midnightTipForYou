package com.midnighttipforyou.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SchedulerConfig {
    
    // 스케줄러 서비스들은 @Service 어노테이션으로 자동 등록되므로
    // 별도의 빈 설정이 필요하지 않습니다.
    
    public SchedulerConfig() {
        log.info("스케줄러 설정이 초기화되었습니다.");
    }
}
