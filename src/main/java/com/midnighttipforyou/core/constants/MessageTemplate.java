package com.midnighttipforyou.core.constants;

/**
 * 메시지 템플릿 상수 정의
 */
public final class MessageTemplate {
    
    private MessageTemplate() {
        // 유틸리티 클래스이므로 인스턴스화 방지
    }
    
    // ========================================
    // 개발 꿀팁 메시지 템플릿
    // ========================================
    
    /**
     * 개발 꿀팁 메시지 헤더
     */
    public static final String TIP_HEADER = "🌙 %s 자기 전 말아주는 오늘의 개발 꿀팁\n\n";
    
    /**
     * 개발 꿀팁 제목
     */
    public static final String TIP_TITLE = "📚 %s\n\n";
    
    /**
     * 개발 꿀팁 내용
     */
    public static final String TIP_CONTENT = "💡 %s\n\n";
    
    /**
     * 개발 꿀팁 카테고리
     */
    public static final String TIP_CATEGORY = "🏷️  카테고리: %s\n";
    
    /**
     * 개발 꿀팁 출처
     */
    public static final String TIP_SOURCE = "📖 출처: %s\n";
    
    /**
     * 개발 꿀팁 푸터
     */
    public static final String TIP_FOOTER = "\n🌙 좋은 꿈 꾸세요! 내일도 화이팅! 💪";
    
    /**
     * 에러 메시지 헤더
     */
    public static final String ERROR_HEADER = "❌ 오늘의 개발 꿀팁을 가져오는 중 오류가 발생했습니다.\n\n";
    
    /**
     * 에러 메시지 내용
     */
    public static final String ERROR_CONTENT = "오류 내용: %s\n\n";
    
    /**
     * 에러 메시지 푸터
     */
    public static final String ERROR_FOOTER = "🌙 좋은 꿈 꾸세요! 내일도 화이팅! 💪";
    
    // ========================================
    // 로그 메시지 템플릿
    // ========================================
    
    /**
     * 스케줄러 시작 로그
     */
    public static final String LOG_SCHEDULER_START = "🌙 %s - %s 스케줄러 실행: 자기 전 개발 꿀팁 전송 시작 (타입: %s)";
    
    /**
     * 스케줄러 완료 로그
     */
    public static final String LOG_SCHEDULER_COMPLETE = "🌙 %s - %s 스케줄러 완료: 개발 꿀팁 전송 완료";
    
    /**
     * 스케줄러 실패 로그
     */
    public static final String LOG_SCHEDULER_FAILED = "%s 스케줄러 실행 실패";
    
    /**
     * 상태 확인 시작 로그
     */
    public static final String LOG_STATUS_CHECK_START = "📅 %s - %s 스케줄러 상태 확인 시작";
    
    /**
     * 상태 확인 완료 로그
     */
    public static final String LOG_STATUS_CHECK_COMPLETE = "📅 %s - %s 스케줄러 상태 확인 완료: 정상 동작 중";
    
    // ========================================
    // API 응답 메시지 템플릿
    // ========================================
    
    /**
     * 성공 메시지
     */
    public static final String API_SUCCESS = "요청이 성공적으로 처리되었습니다.";
    
    /**
     * 스케줄러 타입 변경 성공
     */
    public static final String API_SCHEDULER_TYPE_CHANGED = "스케줄러 타입이 성공적으로 변경되었습니다.";
    
    /**
     * 꿀팁 전송 완료
     */
    public static final String API_TIP_SENT = "개발 꿀팁 즉시 전송 완료";
    
    /**
     * 스케줄러 상태 정상
     */
    public static final String API_SCHEDULER_STATUS_OK = "스케줄러가 정상 동작 중입니다.";
    
    // ========================================
    // 날짜/시간 포맷
    // ========================================
    
    /**
     * 날짜 포맷 (yyyy년 MM월 dd일)
     */
    public static final String DATE_FORMAT = "yyyy년 MM월 dd일";
    
    /**
     * 시간 포맷 (HH:mm:ss)
     */
    public static final String TIME_FORMAT = "HH:mm:ss";
    
    /**
     * 전체 날짜시간 포맷 (yyyy-MM-dd HH:mm:ss)
     */
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
}
