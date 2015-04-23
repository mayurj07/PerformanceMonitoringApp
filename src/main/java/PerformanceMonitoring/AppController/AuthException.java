package PerformanceMonitoring.AppController;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class AuthException extends IllegalArgumentException {

    public AuthException(String message) {
        super(message);
    }
}