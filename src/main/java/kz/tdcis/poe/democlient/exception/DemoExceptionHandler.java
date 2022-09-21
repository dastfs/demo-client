package kz.tdcis.poe.democlient.exception;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDateTime;
import java.util.Locale;

@RestControllerAdvice
@RequiredArgsConstructor
public class DemoExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(DemoExceptionHandler.class);

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleUsernameNotFoundException(RuntimeException e,
                                                                             Locale locale) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                LocalDateTime.now(),
                e.getMessage(),
                null);
        logger.error(exceptionResponse.toString());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ApiServerException.class})
    public ResponseEntity<ExceptionResponse> handleApiServerException(WebClientResponseException e) {
        logger.error(e.getMessage(), e);
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                LocalDateTime.now(),
                e.getStatusText(),
                e.getResponseBodyAsString());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
