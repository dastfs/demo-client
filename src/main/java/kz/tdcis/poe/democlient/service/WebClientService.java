package kz.tdcis.poe.democlient.service;

import kz.tdcis.poe.democlient.exception.ApiServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class WebClientService {
    private final WebClient webClient;

    public String webClientPost(String uri, Object body) {
        return webClient.post()
                .uri(uri)
                .body(Mono.just(body), Object.class)
                .retrieve()
                .onStatus(HttpStatus::isError,
                        error -> error.createException().flatMap(e -> {
                            return Mono.error(
                                    new ApiServerException(e));}))
                .bodyToMono(String.class)
                .block();
    }

    public String webClientPost(String uri, Object body, String token) {
        return webClient.post()
                .uri(uri)
                .header("X-Token", token)
                .body(Mono.just(body), Object.class)
                .retrieve()
                .onStatus(HttpStatus::isError,
                        error -> error.createException().flatMap(e -> {
                            return Mono.error(
                                    new ApiServerException(e));}))
                .bodyToMono(String.class)
                .block();
    }

    public String webClientPostXML(String uri, String body) {
        return webClient.post()
                .uri(uri)
                .body(Mono.just(body), String.class)
                .header("Content-Type", "application/xml")
                .retrieve()
                .onStatus(HttpStatus::isError,
                        error -> error.createException().flatMap(e -> Mono.error(
                                new ApiServerException(e))))
                .bodyToMono(String.class)
                .block();
    }

}
