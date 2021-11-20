package dev.karlmenzel.demos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MissingResourceException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    public MissingResourceException(String message) {
        super(message);
    }
}