package com.cvdejan.booking.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ApiError {
    private List<String> message;
    private HttpStatus status;
    private LocalDateTime timeStamp;
}
