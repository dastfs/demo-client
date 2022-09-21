package kz.tdcis.poe.democlient.journal;

import kz.tdcis.poe.democlient.model.journal.EEventResult;
import kz.tdcis.poe.democlient.model.journal.EJournalEvent;
import kz.tdcis.poe.democlient.service.JournalService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

@Aspect
@Component
@RequiredArgsConstructor
public class JournalAspects {

    private final JournalService journalService;

    @Value("${client.profile.contractId}")
    private String contractId;

    @Value("${client.profile.email}")
    private String email;

    @Async
    @AfterReturning(pointcut = "kz.tdcis.poe.democlient.journal.JournalPointcuts.createHash()")
    public void journalingAfterCreatingHash(){
        Journal journal = Journal.builder()
                .contractId(contractId)
                .createdAt(LocalDateTime.now())
                .createdBy(email)
                .event(EJournalEvent.ADD_HASH)
                .result(EEventResult.SUCCESS)
                .description(new LinkedHashMap<>() {{
                    put("Запись данных", "Запись добавлена пользователем - " + email);
                }})
                .build();

        journalService.createJournalRecord(journal);
    }
}
