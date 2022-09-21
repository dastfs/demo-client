package kz.tdcis.poe.democlient.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ClientHashList {
    @JsonProperty("doc_ids")
    private List<ClientHashReq> docIds;
}
