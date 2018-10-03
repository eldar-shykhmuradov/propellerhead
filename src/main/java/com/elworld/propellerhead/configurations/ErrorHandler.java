package com.elworld.propellerhead.configurations;

import com.elworld.propellerhead.exceptions.ApplicationException;
import com.elworld.propellerhead.models.dto.ValidationErrorFieldDto;
import com.elworld.propellerhead.models.enums.ErrorCode;
import com.elworld.propellerhead.models.response.ApplicationErrorResponse;
import com.elworld.propellerhead.models.response.ValidationErrorsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@ControllerAdvice
public class ErrorHandler {


    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApplicationErrorResponse handleApplicationException(Exception e) {
        return handleException(e);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorsResponse processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }

    private ApplicationErrorResponse handleException(Exception e) {
        final ErrorCode errorCode = getErrorCodeForException(e);
        final String message = errorCode.getMessage();
        log.warn(e.getMessage(), e);
        return new ApplicationErrorResponse(errorCode.getErrorCode(), message);
    }

    private ValidationErrorsResponse processFieldErrors(List<FieldError> fieldErrors) {
        final List<ValidationErrorFieldDto> messages = fieldErrors.stream()
            .map(fieldError -> new ValidationErrorFieldDto(fieldError.getField(), fieldError.getDefaultMessage()))
            .collect(Collectors.toList());
        return new ValidationErrorsResponse(messages);
    }


    private ErrorCode getErrorCodeForException(Exception e) {
        if (e instanceof ApplicationException) {
            return ((ApplicationException) e).getErrorCode();
        } else {
            return ErrorCode.INTERNAL_SERVER;
        }
    }
}
