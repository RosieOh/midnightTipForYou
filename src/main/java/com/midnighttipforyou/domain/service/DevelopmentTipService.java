package com.midnighttipforyou.domain.service;

import com.midnighttipforyou.domain.entity.DevelopmentTip;
import com.midnighttipforyou.domain.repository.DevelopmentTipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DevelopmentTipService {
    
    private final DevelopmentTipRepository developmentTipRepository;
    
    /**
     * 랜덤한 개발 꿀팁을 가져온다.
     */
    public Optional<DevelopmentTip> getRandomTip() {
        log.info("랜덤 개발 꿀팁을 가져오는 중...");
        return developmentTipRepository.findRandomActiveTip();
    }
    
    /**
     * 특정 카테고리의 랜덤한 개발 꿀팁을 가져온다.
     */
    public Optional<DevelopmentTip> getRandomTipByCategory(String category) {
        log.info("카테고리 '{}'에서 랜덤 개발 꿀팁을 가져오는 중...", category);
        List<DevelopmentTip> tips = developmentTipRepository.findByCategoryAndIsActiveTrue(category);
        if (tips.isEmpty()) {
            return Optional.empty();
        }
        // 랜덤하게 선택
        int randomIndex = (int) (Math.random() * tips.size());
        return Optional.of(tips.get(randomIndex));
    }
    
    /**
     * 모든 활성화된 개발 꿀팁을 가져온다.
     */
    public List<DevelopmentTip> getAllActiveTips() {
        return developmentTipRepository.findByIsActiveTrue();
    }
    
    /**
     * 새로운 개발 꿀팁을 저장합니다.
     */
    public DevelopmentTip saveTip(DevelopmentTip tip) {
        log.info("새로운 개발 꿀팁을 저장합니다: {}", tip.getTitle());
        return developmentTipRepository.save(tip);
    }
    
    /**
     * 개발 꿀팁을 비활성화합니다.
     */
    public void deactivateTip(Long tipId) {
        developmentTipRepository.findById(tipId).ifPresent(tip -> {
            tip.setActive(false);
            developmentTipRepository.save(tip);
            log.info("개발 꿀팁을 비활성화했습니다: {}", tip.getTitle());
        });
    }
}
