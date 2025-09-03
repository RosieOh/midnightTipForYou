package com.midnighttipforyou.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 애플리케이션 전체에서 사용하는 에러 코드 정의
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    
    // ========================================
    // 공통 에러 코드 (1000번대)
    // ========================================
    COMMON_INTERNAL_ERROR(1000, "내부 서버 오류가 발생했습니다."),
    COMMON_INVALID_REQUEST(1001, "잘못된 요청입니다."),
    COMMON_RESOURCE_NOT_FOUND(1002, "요청한 리소스를 찾을 수 없습니다."),
    COMMON_UNAUTHORIZED(1003, "인증이 필요합니다."),
    COMMON_FORBIDDEN(1004, "접근 권한이 없습니다."),
    COMMON_VALIDATION_ERROR(1005, "입력값 검증에 실패했습니다."),
    
    // ========================================
    // 스케줄러 관련 에러 코드 (2000번대)
    // ========================================
    SCHEDULER_EXECUTION_FAILED(2000, "스케줄러 실행에 실패했습니다."),
    SCHEDULER_TYPE_NOT_FOUND(2001, "지원하지 않는 스케줄러 타입입니다."),
    SCHEDULER_SERVICE_UNAVAILABLE(2002, "스케줄러 서비스를 사용할 수 없습니다."),
    SCHEDULER_CONFIG_ERROR(2003, "스케줄러 설정에 오류가 있습니다."),
    SCHEDULER_JOB_FAILED(2004, "스케줄러 작업 실행에 실패했습니다."),
    
    // ========================================
    // 개발 꿀팁 관련 에러 코드 (3000번대)
    // ========================================
    TIP_NOT_FOUND(3000, "개발 꿀팁을 찾을 수 없습니다."),
    TIP_CREATION_FAILED(3001, "개발 꿀팁 생성에 실패했습니다."),
    TIP_UPDATE_FAILED(3002, "개발 꿀팁 수정에 실패했습니다."),
    TIP_DELETION_FAILED(3003, "개발 꿀팁 삭제에 실패했습니다."),
    TIP_SEND_FAILED(3004, "개발 꿀팁 전송에 실패했습니다."),
    TIP_NO_ACTIVE_TIPS(3005, "활성화된 개발 꿀팁이 없습니다."),
    
    // ========================================
    // 메시지 전송 관련 에러 코드 (4000번대)
    // ========================================
    MESSAGE_SEND_FAILED(4000, "메시지 전송에 실패했습니다."),
    MESSAGE_FORMAT_ERROR(4001, "메시지 형식에 오류가 있습니다."),
    MESSAGE_TEMPLATE_NOT_FOUND(4002, "메시지 템플릿을 찾을 수 없습니다."),
    
    // ========================================
    // 데이터베이스 관련 에러 코드 (5000번대)
    // ========================================
    DATABASE_CONNECTION_ERROR(5000, "데이터베이스 연결에 실패했습니다."),
    DATABASE_QUERY_ERROR(5001, "데이터베이스 쿼리 실행에 실패했습니다."),
    DATABASE_TRANSACTION_ERROR(5002, "데이터베이스 트랜잭션 처리에 실패했습니다."),
    DATABASE_CONSTRAINT_VIOLATION(5003, "데이터베이스 제약 조건 위반이 발생했습니다."),
    
    // ========================================
    // 외부 API 관련 에러 코드 (6000번대)
    // ========================================
    EXTERNAL_API_ERROR(6000, "외부 API 호출에 실패했습니다."),
    EXTERNAL_API_TIMEOUT(6001, "외부 API 응답 시간이 초과되었습니다."),
    EXTERNAL_API_UNAVAILABLE(6002, "외부 API 서비스를 사용할 수 없습니다."),
    EXTERNAL_API_RATE_LIMIT(6003, "외부 API 호출 제한에 도달했습니다."),
    
    // ========================================
    // 설정 관련 에러 코드 (7000번대)
    // ========================================
    CONFIG_NOT_FOUND(7000, "필요한 설정을 찾을 수 없습니다."),
    CONFIG_INVALID_VALUE(7001, "설정값이 유효하지 않습니다."),
    CONFIG_LOAD_ERROR(7002, "설정 로드에 실패했습니다.");
    
    private final int code;
    private final String message;
    
    /**
     * 에러 코드로 ErrorCode 찾기
     */
    public static ErrorCode findByCode(int code) {
        for (ErrorCode errorCode : values()) {
            if (errorCode.getCode() == code) {
                return errorCode;
            }
        }
        return COMMON_INTERNAL_ERROR; // 기본값
    }
    
    /**
     * 에러 코드로 ErrorCode 찾기 (Optional 반환)
     */
    public static java.util.Optional<ErrorCode> findOptionalByCode(int code) {
        for (ErrorCode errorCode : values()) {
            if (errorCode.getCode() == code) {
                return java.util.Optional.of(errorCode);
            }
        }
        return java.util.Optional.empty();
    }
}
