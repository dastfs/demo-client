package kz.tdcis.poe.democlient.model;

import kz.tdcis.poe.democlient.dto.ClientDataDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client_data")
public class ClientData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime modifiedDate;
    @Column(nullable = false, name = "referenceData")
    private String referenceData;
    @Column(nullable = false, name = "data")
    private String data;
    private Status status;
    private LocalDateTime statusCheckDate;
    private String referenceHash;
    private String hash;

    public static ClientData createDataWithStatus(Status status, ClientDataDto data){
        return ClientData.builder()
                .referenceData(data.getReferenceData())
                .data(data.getReferenceData())
                .date(data.getDate())
                .status(status)
                .statusCheckDate(null)
                .build();
    }
}
