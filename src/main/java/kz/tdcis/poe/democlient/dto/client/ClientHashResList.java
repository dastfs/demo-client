package kz.tdcis.poe.democlient.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class ClientHashResList {
    private String certificate;
    private String sign;
    @JsonProperty("hashes")
    private List<ClientHashRes> hashes;

    public HashMap<String, List<ClientHashRes>> convertToMap(List<ClientHashRes> hashesClient) {

        if (hashesClient == null) {
            return null;
        }

        HashMap<String, List<ClientHashRes>> mapDB = new HashMap<>();
        for (ClientHashRes hash : hashesClient) {
            String hashId = hash.getDocId();
            List<ClientHashRes> hashList;

            if (mapDB.containsKey(hashId)) {
                hashList = mapDB.get(hashId);
            } else {
                hashList = new ArrayList<>();
            }

            hashList.add(hash);
            mapDB.put(hashId, hashList);
        }
        return mapDB;
    }
}
