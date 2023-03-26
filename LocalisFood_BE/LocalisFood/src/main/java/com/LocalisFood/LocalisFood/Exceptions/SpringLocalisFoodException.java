package com.LocalisFood.LocalisFood.Exceptions;
import org.springframework.http.HttpStatus;
public class SpringLocalisFoodException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final String message;
    private final HttpStatus httpStatus;

    public SpringLocalisFoodException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
    public SpringLocalisFoodException(String message) {
        super(message);
        this.message = message;
        this.httpStatus = null;
    }
    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
