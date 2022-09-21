package kz.tdcis.poe.democlient.model.journal;

public enum EEventResult {
    SUCCESS("Успешно"),
    FAILURE("Отказ");

    private String code;
    EEventResult(String code) {
        this.code = code;
    }
}
