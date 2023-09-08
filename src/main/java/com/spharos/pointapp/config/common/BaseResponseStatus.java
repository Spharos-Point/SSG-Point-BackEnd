package com.spharos.pointapp.config.common;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {

    /**
     * 1000: 요청 성공
     **/
    SUCCESS(true, 1000, "요청에 성공하였습니다."),

    /**
     * 2000: Request 오류
     **/
    // Common
    POST_EXISTS_LOGIN_ID(false, 2000, "중복된 아이디입니다."),
    POST_EXISTS_PHONE(false, 2001, "중복된 휴대폰 번호입니다."),
    POST_EXISTS_EMAIL(false, 2002, "중복된 이메일입니다."),
    POST_EXISTS_CART(false, 2003, "이미 장바구니에 있는 상품입니다."),
    POST_EXISTS_QNA(false, 2004, "중복된 문의&답변 입니다."),
    AUTH_NUM_IS_NULL(false, 2005, "인증번호가 비어 있습니다."),
    INVALID_PHONE_NUM(false, 2006, "유효하지 않은 전화번호 형식입니다."),
    POST_EXISTS_WISH(false, 2007, "중복된 좋아요 요청입니다."),

    /**
     * 3000: Response 오류
     **/
    // Common
    FAILED_TO_LOGIN(false, 3000, "없는 아이디거나 비밀번호가 틀렸습니다."),
    NO_LOOKUP_VALUE(false, 3001, "조회된 데이터가 없습니다."),
    JWT_CREATE_FAILED(false, 3002, "토큰 생성에 실패하였습니다."),
    NO_EXIST_USER(false, 3003, "존재하지 않는 유저 정보입니다."),


    // User
    USER_INSERT_FAILED(false, 4000, "사용자 회원가입에 실패하였습니다."),
    USER_RETRIEVE_FAILED(false, 4001, "회원정보 조회에 실패했습니다."),
    USER_UPDATE_FAILED(false, 4002, "회원정보 변경에 실패했습니다."),
    PASSWORD_RETRIEVE_FAILED(false, 4003, "비밀번호 조회에 실패했습니다."),
    PASSWORD_UPDATE_FAILED(false, 4004, "비밀번호 변경에 실패했습니다.");


    private final boolean isSuccess;
    private final int code;
    private final String message;


    BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}