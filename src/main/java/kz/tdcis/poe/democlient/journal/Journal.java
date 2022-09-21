package kz.tdcis.poe.democlient.journal;

import kz.tdcis.poe.democlient.model.journal.EEventResult;
import kz.tdcis.poe.democlient.model.journal.EJournalEvent;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

@Data
@Builder
public class Journal {
    private String createdBy;
    private String contractId;
    private LocalDateTime createdAt;
    private EEventResult result;
    private EJournalEvent event;
    private LinkedHashMap<String, String> description;
}
