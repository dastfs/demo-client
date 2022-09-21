package kz.tdcis.poe.democlient.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExceptionResponse {
    private LocalDateTime date;
    private String message;
    private String details;
}
