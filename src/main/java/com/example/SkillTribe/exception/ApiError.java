package com.example.SkillTribe.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ApiError {
    private HttpStatus status;
    private String message;
    private List<String> errors;
    private LocalDateTime time = LocalDateTime.now();
    private Integer statusCode;

    public ApiError(HttpStatus httpStatus, String localizedMessage, String errors) {
        this.status = httpStatus;
        this.message = localizedMessage;
        this.errors = List.of(errors);
        this.statusCode = httpStatus.value();
    }

    public ApiError(HttpStatus httpStatus, String localizedMessage, List<String> errors) {
        this.status = httpStatus;
        this.message = localizedMessage;
        this.errors = errors;
        this.statusCode = httpStatus.value();
    }

    public ApiError(HttpStatus httpStatus, String localizedMessage) {
        this.status = httpStatus;
        this.message = localizedMessage;
        this.statusCode = httpStatus.value();
    }
}
