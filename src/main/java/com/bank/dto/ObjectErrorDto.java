package com.bank.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ObjectErrorDto {

    private String object;
    private String message;
    private Object value;

    public ObjectErrorDto(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
