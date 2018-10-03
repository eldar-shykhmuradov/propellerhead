package com.elworld.propellerhead.models.enums;


public enum ErrorCode {

    INTERNAL_SERVER(1, "Server error. Please, try later"),
    CUSTOMER_NOT_EXIST(2, "Customer does not exist"),
    NOT_VALID_REQUEST_DATA(3, "Not valid request");


    private final int errorCode;
    private final String message;


    ErrorCode(final int errorCode, final String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public static ErrorCode parseErrorCode(int errorCode) {
        for (ErrorCode code : values()) {
            if (code.getErrorCode() == errorCode) {
                return code;
            }
        }
        return ErrorCode.INTERNAL_SERVER;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

}

