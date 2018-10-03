package com.elworld.propellerhead.exceptions;


import com.elworld.propellerhead.models.enums.ErrorCode;

public class InvalidCustomerNoteAccessException extends ApplicationException {

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.INVALID_CUSTOMER_NOTE_ACCESS;
    }
}
