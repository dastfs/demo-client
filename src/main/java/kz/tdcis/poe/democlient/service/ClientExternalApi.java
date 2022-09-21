package kz.tdcis.poe.democlient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.tdcis.poe.democlient.dto.client.ClientHashList;
import kz.tdcis.poe.democlient.dto.client.ClientHashRes;
import kz.tdcis.poe.democlient.dto.client.ClientHashResList;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientExternalApi {
    private final WebClientService webClient;
    private final ClientService clientService;
    @Value("${client.api}")
    private String clientApi;
    @Value("${client.profile.owner}")
    private String orgBin;
    private final Logger logger = LoggerFactory.getLogger(ClientExternalApi.class);

    public ClientHashResList getHashesFromClient(ClientHashList requestBody) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ClientHashList> request = new HttpEntity<>(requestBody, headers);
        String responseBody = webClient.webClientPost(clientApi, request);
        logger.info(responseBody);

        if(responseBody == null){
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        ClientHashResList result;

        try {
            result = mapper.readValue(responseBody, ClientHashResList.class);
        } catch (JsonProcessingException e) {
            throw new Exception(e.getMessage());
        }

        //set doc hash id for test client
        for(ClientHashRes hashDetails : result.getHashes()){
            if(hashDetails.getOwner().equals(orgBin)){
                String hashClient = clientService.setStatusForClient(Long.valueOf(hashDetails.getDocId()), hashDetails.getDocHash());
                hashDetails.setDocHash(hashClient);
            }
        }

        return result;

    }
}
