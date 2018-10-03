package com.elworld.propellerhead.models.response;

import com.elworld.propellerhead.models.dto.ValidationErrorFieldDto;
import com.elworld.propellerhead.models.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ValidationErrorsResponse {

    private final int errorCode = ErrorCode.NOT_VALID_REQUEST_DATA.getErrorCode();
    private final List<ValidationErrorFieldDto> errors;

}
