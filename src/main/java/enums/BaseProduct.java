package enums;

public enum BaseProduct {

    FlOUR("Мука"),
    EGG("Яйцо"),
    SUGAR("Сахар");

    private final String name;
    BaseProduct(String name) { this.name = name; }
    public String getValue() { return name; }

}
