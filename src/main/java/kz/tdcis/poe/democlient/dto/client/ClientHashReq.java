package kz.tdcis.poe.democlient.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ClientHashReq {
    @JsonProperty("owner")
    private String owner;
    @JsonProperty("doc_id")
    private String docId;
}
