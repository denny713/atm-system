package com.bank.exception;

import com.bank.dto.ObjectErrorDto;

import java.util.Arrays;
import java.util.List;

public class ValidationCustomException extends RuntimeException{

    private final List<ObjectErrorDto> objectErrorDtos;

    public ValidationCustomException(List<ObjectErrorDto> objectErrorDtos) {
        this.objectErrorDtos = objectErrorDtos;
    }

    public ValidationCustomException(ObjectErrorDto objectErrorDto) {
        this.objectErrorDtos = Arrays.asList(objectErrorDto);
    }

    public List<ObjectErrorDto> getObjectErrorDtos() {
        return objectErrorDtos;
    }

    public ValidationCustomException(String message, List<ObjectErrorDto> objectErrorDtos) {
        super(message);
        this.objectErrorDtos = objectErrorDtos;
    }
}
