package com.jdong.studycafe.common.error.exception;

import lombok.Getter;

public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", " Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", " Invalid Input Value"),
    ENTITY_NOT_FOUND(400, "C003", " Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
    INVALID_TYPE_VALUE(400, "C005", " Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied"),


    // Member
    EMAIL_DUPLICATION(400, "M001", "Email is Duplication"),
    LOGIN_INPUT_INVALID(400, "M002", "Login input is invalid"),
    MEMBER_NOT_FOUND(400, "M003", "Member Entity Not Found"),

    // Cafe
    CAFE_NOT_FOUND(400, "CF001", "Cafe Entity Not Found"),


    // Beverage
    BAVERAGE_NOT_FOUND(400, "B001", "Beverage Entity Not Found"),

    // Favorite
    FAVORITE_DUPLICATION(400, "F001", "Favorite is Duplicated"),
    FAVORITE_NOT_FOUND(400, "F002", "Favorite Entity Not Found"),

    // Study
    MEMBER_IS_STUDYING(400, "S001", "User is Studying"),
    MEMBER_HAS_DUPLICATE_STUDY_RECORD(400, "S002", "This user has more than one study record."),

    // Order
    NOT_ENOUGH_CREDIT(400, "E001", "User does not have enough credit"),
    ;
    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}