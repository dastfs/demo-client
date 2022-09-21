package kz.tdcis.poe.democlient.dto;

import kz.tdcis.poe.democlient.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDataDto {
    private Long id;
    private LocalDateTime date;
    private String referenceData;
    private String data;
    private String hash;
    private String referenceHash;
    private Status status;
    private LocalDateTime statusCheckDate;
}
