package com.midnighttipforyou.domain.controller;

import com.midnighttipforyou.domain.entity.DevelopmentTip;
import com.midnighttipforyou.domain.service.DevelopmentTipService;
import com.midnighttipforyou.domain.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tips")
@RequiredArgsConstructor
@Slf4j
public class DevelopmentTipController {
    
    private final DevelopmentTipService developmentTipService;
    private final MessageService messageService;
    
    /**
     * 모든 활성화된 개발 꿀팁을 조회.
     */
    @GetMapping
    public ResponseEntity<List<DevelopmentTip>> getAllTips() {
        List<DevelopmentTip> tips = developmentTipService.getAllActiveTips();
        return ResponseEntity.ok(tips);
    }
    
    /**
     * 랜덤한 개발 꿀팁을 조회.
     */
    @GetMapping("/random")
    public ResponseEntity<DevelopmentTip> getRandomTip() {
        Optional<DevelopmentTip> tip = developmentTipService.getRandomTip();
        return tip.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * 특정 카테고리의 랜덤한 개발 꿀팁을 조회.
     */
    @GetMapping("/random/{category}")
    public ResponseEntity<DevelopmentTip> getRandomTipByCategory(@PathVariable String category) {
        Optional<DevelopmentTip> tip = developmentTipService.getRandomTipByCategory(category);
        return tip.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * 새로운 개발 꿀팁을 생성.
     */
    @PostMapping
    public ResponseEntity<DevelopmentTip> createTip(@RequestBody DevelopmentTip tip) {
        DevelopmentTip savedTip = developmentTipService.saveTip(tip);
        return ResponseEntity.ok(savedTip);
    }
    
    /**
     * 개발 꿀팁을 비활성화.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateTip(@PathVariable Long id) {
        developmentTipService.deactivateTip(id);
        return ResponseEntity.ok().build();
    }
    
    /**
     * 테스트용: 즉시 메시지 전송 (스케줄러 테스트)
     */
    @PostMapping("/{id}/send")
    public ResponseEntity<String> sendTipMessage(@PathVariable Long id) {
        Optional<DevelopmentTip> tip = developmentTipService.getAllActiveTips().stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
        
        if (tip.isPresent()) {
            messageService.sendTipMessage(tip.get());
            return ResponseEntity.ok("메시지 전송 완료");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * 테스트용: 랜덤 꿀팁 즉시 전송
     */
    @PostMapping("/send-random")
    public ResponseEntity<String> sendRandomTipMessage() {
        Optional<DevelopmentTip> tip = developmentTipService.getRandomTip();
        
        if (tip.isPresent()) {
            messageService.sendTipMessage(tip.get());
            return ResponseEntity.ok("랜덤 꿀팁 메시지 전송 완료: " + tip.get().getTitle());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
