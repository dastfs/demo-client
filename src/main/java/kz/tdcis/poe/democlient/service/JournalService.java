package kz.tdcis.poe.democlient.service;

import kz.tdcis.poe.democlient.journal.Journal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JournalService {

    @Value("${poe.journal.api}")
    private String url;
    private final WebClientService webClient;

    public void createJournalRecord(Journal journal){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Journal> request = new HttpEntity<>(journal, headers);
        webClient.webClientPost(url, request);
    }

}
