package com.midnighttipforyou.core.scheduler;

import com.midnighttipforyou.domain.service.KakaoMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DailyTipScheduler {

    private final KakaoMessageService kakaoMessageService;

    // 매일 00:00 실행
    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void sendTipAtMidnight() {
        kakaoMessageService.sendDailyTip();
    }
}