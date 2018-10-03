package com.elworld.propellerhead.models.response;

import lombok.Data;

@Data
public class ApplicationErrorResponse {

    private final int errorCode;
    private final String message;

}
