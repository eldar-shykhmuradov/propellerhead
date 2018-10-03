package com.elworld.propellerhead.exceptions;


import com.elworld.propellerhead.models.enums.ErrorCode;

public class CustomerNoteNotExistsException extends ApplicationException {

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.CUSTOMER_NOTE_NOT_EXIST;
    }
}
