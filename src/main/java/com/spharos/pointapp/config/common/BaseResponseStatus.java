package com.spharos.pointapp.config.common;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {

    /**
     * 200: 요청 성공
     **/
    SUCCESS(true, 200, "요청에 성공하였습니다."),

    /**
     * 2000: Request 오류
     **/
    // Common
    POST_EXISTS_LOGIN_ID(false, 2000, "중복된 아이디입니다."),


    /**
     * 3000: Response 오류
     **/
    // Common
    FAILED_TO_LOGIN(false, 3000, "없는 아이디거나 비밀번호가 틀렸습니다."),
    NO_LOOKUP_VALUE(false, 3001, "조회된 데이터가 없습니다."),
    JWT_CREATE_FAILED(false, 3002, "토큰 생성에 실패했습니다."),
    NO_EXIST_USER(false, 3003, "존재하지 않는 유저 정보입니다."),
    NO_POINT_HISTORY(false, 3004, "포인트 내역 조회에 실패했습니다."),
    FAILED_TO_CARD_NUMBER(false, 3005, "바코드 생성에 실패했습니다"),

    // User
    USER_INSERT_FAILED(false, 4000, "사용자 회원가입에 실패했습니다."),
    USER_RETRIEVE_FAILED(false, 4001, "회원정보 조회에 실패했습니다."),
    USER_UPDATE_FAILED(false, 4002, "회원정보 변경에 실패했습니다."),
    PASSWORD_RETRIEVE_FAILED(false, 4003, "비밀번호 조회에 실패했습니다."),
    PASSWORD_UPDATE_FAILED(false, 4004, "비밀번호 변경에 실패했습니다."),
    PASSWORD_CONTAIN_NUM_FAILED(false, 4005,"휴대폰 번호를 포함한 비밀번호 입니다."),
    PASSWORD_SAME_FAILED(false, 4006,"현재 사용중인 비밀번호 입니다."),
    POINT_PASSWORD_RETRIEVE_FAILED(false, 4007,"포인트 비밀번호 조회에 실패했습니다."),
    POINT_PASSWORD_UPDATE_FAILED(false, 4008, "포인트 비밀번호 변경에 실패했습니다."),
    NO_USER_POINT_LIST_HISTORY_FAILED(false, 4009, "해당하는 유저 포인트 리스트가 없습니다."),
    WITHDRAWAL_USER(false, 4010, "탈퇴한 회원입니다."),


    // Gift
    GIFT_FAILED(false, 5000, "선물하기에 실패했습니다."),
    GIFT_MYSELF_FAILED(false, 5001, "자신에게 선물할 수 없습니다."),
    GIFT_NO_HISTORY_FAILED(false, 5002, "해당하는 선물 내역 조회에 실패했습니다."),
    NO_POINT_FAILED(false, 5003, "선물 가능 포인트 한도에서만 가능합니다."),

    // Trans
    TRANS_FAILED( false, 6000, "전환하기에 실패하였습니다."),
    TRANS_NOT( false, 6001, "전환할 포인트가 없습니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;


    BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}