package practice.newbalance.domain.item;

public enum Payment {
    CREDIT_CARD("신용카드"), CHECK_CARD("체크카드"), NO_BANKBOOK("무통장입금");

    private final String value;

    Payment(String value) {
        this.value = value;
    }
}
