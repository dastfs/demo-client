package kz.tdcis.poe.democlient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.tdcis.poe.democlient.dto.blockchain.BlockChainTokenDto;
import kz.tdcis.poe.democlient.dto.blockchain.BlockchainLoginDto;
import kz.tdcis.poe.democlient.dto.poeHash.AddProofResponse;
import kz.tdcis.poe.democlient.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HashService {

    public static final Logger logger = LoggerFactory.getLogger(HashService.class);
    private final ResourceLoader resourceLoader;
    private final WebClientService webClient;
    private final ObjectMapper objectMapper;

    @Value("${keycloak.username}")
    private String userName;
    @Value("${keycloak.password}")
    private String password;
    @Value("${keycloak.org}")
    private String org;
    @Value("${client.profile.owner}")
    private String owner;
    @Value("${client.profile.contractId}")
    private String contractId;
    @Value("${client.profile.email}")
    private String email;
    @Value("${blockchain.login.api}")
    private String loginApi;
    @Value("${poe.hash.api}")
    private String hashServiceUrl;
    @Value("${blockchain.pub.cert}")
    private String certLocation;
    @Value("${blockchain.key}")
    private String keyLocation;

    private byte[] dataBytes;
    private String publicCertificate;
    private String privateKey;

    public void getCerts(){
        try {
            Resource pubCertResource = resourceLoader.getResource(certLocation);
            InputStream pubCert = pubCertResource.getInputStream();

            Resource keyCertResource = resourceLoader.getResource(keyLocation);
            InputStream keyCert = keyCertResource.getInputStream();

            publicCertificate = new String(pubCert.readAllBytes(), StandardCharsets.UTF_8);
            privateKey = new String(keyCert.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException exception) {
            throw new ResourceNotFoundException("No certs found. Error message: " + exception.getMessage());
        }
    }

    public AddProofResponse add(Long id, String data) {
        getCerts();
        dataBytes = data.getBytes();
        String token = getToken();
        Map<String, Object> body = buildBody(id, dataBytes, publicCertificate, privateKey, token);
        String response =
                webClient.webClientPost(hashServiceUrl, body, token);

        AddProofResponse addProofResponse = null;
        addProofResponse = mapProofResponse(response, addProofResponse);

        return addProofResponse;
    }

    private String getToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        BlockchainLoginDto loginDto = BlockchainLoginDto.builder()
                .user(userName)
                .password(password)
                .org(org)
                .build();

        HttpEntity<String> request = new HttpEntity<>(loginDto.toString(), headers);
        logger.info("Token for " + userName + "received");
        return webClient.webClientPost(loginApi, request);
    }

    private Map<String, Object> buildBody(Long id, byte[] dataBytes, String publicCertificate, String privateKey, String token) {
        Map<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("owner", owner);
        body.put("email", email);
        body.put("token", token);
        body.put("contractId", contractId);
        body.put("fileByte", dataBytes);
        body.put("metadata", "metadata");
        body.put("certificate", publicCertificate);
        body.put("key", privateKey);
        return body;
    }

    private AddProofResponse mapProofResponse(String response, AddProofResponse addProofResponse) {
        try {
            addProofResponse = objectMapper.readValue(response, AddProofResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return addProofResponse;
    }
}
