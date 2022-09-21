package kz.tdcis.poe.democlient.exception;

import org.springframework.web.reactive.function.client.WebClientResponseException;

public class ApiServerException extends RuntimeException {
    public ApiServerException(WebClientResponseException e) { super(e); }
}
