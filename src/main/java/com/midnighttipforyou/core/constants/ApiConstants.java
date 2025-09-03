package com.midnighttipforyou.core.constants;

/**
 * API 응답 관련 상수 정의
 */
public final class ApiConstants {
    
    private ApiConstants() {
        // 유틸리티 클래스이므로 인스턴스화 방지
    }
    
    // ========================================
    // HTTP 상태 코드
    // ========================================
    
    /**
     * 성공 (200)
     */
    public static final int HTTP_OK = 200;
    
    /**
     * 생성됨 (201)
     */
    public static final int HTTP_CREATED = 201;
    
    /**
     * 잘못된 요청 (400)
     */
    public static final int HTTP_BAD_REQUEST = 400;
    
    /**
     * 인증 필요 (401)
     */
    public static final int HTTP_UNAUTHORIZED = 401;
    
    /**
     * 접근 금지 (403)
     */
    public static final int HTTP_FORBIDDEN = 403;
    
    /**
     * 리소스 없음 (404)
     */
    public static final int HTTP_NOT_FOUND = 404;
    
    /**
     * 내부 서버 오류 (500)
     */
    public static final int HTTP_INTERNAL_SERVER_ERROR = 500;
    
    /**
     * 서비스 사용 불가 (503)
     */
    public static final int HTTP_SERVICE_UNAVAILABLE = 503;
    
    // ========================================
    // API 응답 메시지
    // ========================================
    
    /**
     * 성공 메시지
     */
    public static final String SUCCESS_MESSAGE = "요청이 성공적으로 처리되었습니다.";
    
    /**
     * 실패 메시지
     */
    public static final String FAILURE_MESSAGE = "요청 처리에 실패했습니다.";
    
    /**
     * 유효성 검사 실패 메시지
     */
    public static final String VALIDATION_FAILED_MESSAGE = "입력값 검증에 실패했습니다.";
    
    /**
     * 리소스 없음 메시지
     */
    public static final String RESOURCE_NOT_FOUND_MESSAGE = "요청한 리소스를 찾을 수 없습니다.";
    
    /**
     * 권한 없음 메시지
     */
    public static final String ACCESS_DENIED_MESSAGE = "접근 권한이 없습니다.";
    
    /**
     * 인증 필요 메시지
     */
    public static final String AUTHENTICATION_REQUIRED_MESSAGE = "인증이 필요합니다.";
    
    // ========================================
    // API 응답 필드명
    // ========================================
    
    /**
     * 메시지 필드
     */
    public static final String FIELD_MESSAGE = "message";
    
    /**
     * 에러 필드
     */
    public static final String FIELD_ERROR = "error";
    
    /**
     * 타임스탬프 필드
     */
    public static final String FIELD_TIMESTAMP = "timestamp";
    
    /**
     * 상태 코드 필드
     */
    public static final String FIELD_STATUS_CODE = "statusCode";
    
    /**
     * 데이터 필드
     */
    public static final String FIELD_DATA = "data";
    
    /**
     * 페이지 정보 필드
     */
    public static final String FIELD_PAGE_INFO = "pageInfo";
    
    /**
     * 총 개수 필드
     */
    public static final String FIELD_TOTAL_COUNT = "totalCount";
    
    // ========================================
    // API 경로
    // ========================================
    
    /**
     * 메인 스케줄러 API 경로
     */
    public static final String API_MAIN_SCHEDULER = "/api/main-scheduler";
    
    /**
     * 개발 꿀팁 API 경로
     */
    public static final String API_TIPS = "/api/tips";
    
    /**
     * Quartz 스케줄러 API 경로
     */
    public static final String API_QUARTZ_SCHEDULER = "/api/scheduler";
    
    /**
     * Tasklet 스케줄러 API 경로
     */
    public static final String API_TASKLET_SCHEDULER = "/api/tasklet";
    
    /**
     * 헬스체크 API 경로
     */
    public static final String API_HEALTH = "/actuator/health";
    
    // ========================================
    // API 버전
    // ========================================
    
    /**
     * API 버전 1
     */
    public static final String API_VERSION_V1 = "v1";
    
    /**
     * API 버전 2
     */
    public static final String API_VERSION_V2 = "v2";
    
    /**
     * 현재 API 버전
     */
    public static final String CURRENT_API_VERSION = API_VERSION_V1;
    
    // ========================================
    // 페이지네이션 기본값
    // ========================================
    
    /**
     * 기본 페이지 크기
     */
    public static final int DEFAULT_PAGE_SIZE = 20;
    
    /**
     * 최대 페이지 크기
     */
    public static final int MAX_PAGE_SIZE = 100;
    
    /**
     * 기본 페이지 번호
     */
    public static final int DEFAULT_PAGE_NUMBER = 0;
    
    // ========================================
    // 정렬 관련
    // ========================================
    
    /**
     * 기본 정렬 방향 (오름차순)
     */
    public static final String DEFAULT_SORT_DIRECTION = "ASC";
    
    /**
     * 내림차순 정렬
     */
    public static final String SORT_DIRECTION_DESC = "DESC";
    
    /**
     * 기본 정렬 필드
     */
    public static final String DEFAULT_SORT_FIELD = "id";
}
