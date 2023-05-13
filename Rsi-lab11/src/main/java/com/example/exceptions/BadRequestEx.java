package com.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestEx extends RuntimeException {

    public BadRequestEx() {
        super("The specified person does not exist");
    }
    public BadRequestEx(int id) {
        super(String.valueOf(id));
    }
}
