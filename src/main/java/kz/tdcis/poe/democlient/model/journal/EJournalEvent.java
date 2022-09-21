package kz.tdcis.poe.democlient.model.journal;

public enum EJournalEvent {
    ADD_HASH("Передача данных");

    private String code;
    EJournalEvent(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }
}
