package com.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CoffeeNotFoundException extends RuntimeException{

    public CoffeeNotFoundException(String message) {
        super(message);
    }

    public CoffeeNotFoundException() {
        super("Запись о таком кофе не найдена");
    }

}
