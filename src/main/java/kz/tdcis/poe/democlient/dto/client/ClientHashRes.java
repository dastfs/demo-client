package kz.tdcis.poe.democlient.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ClientHashRes {
    @JsonProperty("doc_id")
    private String docId;
    @JsonProperty("doc_hash")
    private String docHash;
    @JsonProperty("owner")
    private String owner;
    @JsonProperty("date_created")
    private String dateCreated;
}
