package com.midnighttipforyou.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 지원하는 스케줄러 타입 정의
 */
@Getter
@RequiredArgsConstructor
public enum SchedulerType {
    
    TASKLET("tasklet", "Spring Batch Tasklet 기반 스케줄러", "간단하고 직관적, Spring Batch와 완벽 통합"),
    QUARTZ("quartz", "Quartz 기반 고급 스케줄러", "강력한 기능, 클러스터링 지원, Job 체이닝"),
    SIMPLE("simple", "Spring Scheduling 기반 스케줄러", "가장 가벼움, 설정 최소화");
    
    private final String value;
    private final String name;
    private final String description;
    
    /**
     * 문자열 값으로 SchedulerType 찾기
     */
    public static SchedulerType fromValue(String value) {
        for (SchedulerType type : values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return TASKLET; // 기본값
    }
    
    /**
     * 문자열 값으로 SchedulerType 찾기 (Optional 반환)
     */
    public static java.util.Optional<SchedulerType> findOptionalByValue(String value) {
        for (SchedulerType type : values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return java.util.Optional.of(type);
            }
        }
        return java.util.Optional.empty();
    }
    
    /**
     * 지원하는 스케줄러 타입인지 확인
     */
    public static boolean isValid(String value) {
        return findOptionalByValue(value).isPresent();
    }
    
    /**
     * 모든 스케줄러 타입 정보 반환
     */
    public static String[] getAllDescriptions() {
        SchedulerType[] types = values();
        String[] descriptions = new String[types.length];
        
        for (int i = 0; i < types.length; i++) {
            descriptions[i] = String.format("%s - %s (%s)", 
                types[i].getValue(), types[i].getName(), types[i].getDescription());
        }
        
        return descriptions;
    }
}
