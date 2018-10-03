package com.elworld.propellerhead.exceptions;


import com.elworld.propellerhead.models.enums.ErrorCode;

public abstract class ApplicationException extends RuntimeException {

    public ApplicationException() {
        super(null, null, false, false);
    }

    public abstract ErrorCode getErrorCode();

}
