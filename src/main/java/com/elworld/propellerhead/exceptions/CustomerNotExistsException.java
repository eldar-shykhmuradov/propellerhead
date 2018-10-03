package com.elworld.propellerhead.exceptions;


import com.elworld.propellerhead.models.enums.ErrorCode;

public class CustomerNotExistsException extends ApplicationException {

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.CUSTOMER_NOT_EXIST;
    }
}
