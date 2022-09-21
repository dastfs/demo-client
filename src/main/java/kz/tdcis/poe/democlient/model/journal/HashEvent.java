package kz.tdcis.poe.democlient.model.journal;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

@Getter
public class HashEvent extends ApplicationEvent {
    private final String email;
    private final String contractId;
    private final LocalDateTime createdAt;
    private final EJournalEvent event;
    private final EEventResult result;
    private final LinkedHashMap<String, String> details;

    public HashEvent(Object source, String email, String contractId,
                     LocalDateTime createdAt, EJournalEvent event,
                     EEventResult result, LinkedHashMap<String, String> details) {
        super(source);
        this.email = email;
        this.contractId = contractId;
        this.createdAt = createdAt;
        this.event = event;
        this.result = result;
        this.details = details;
    }
}
