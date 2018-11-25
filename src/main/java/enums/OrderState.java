package enums;

public enum OrderState {

    WAITING("Ожидание"),
    COOKING("Готовится"),
    READY("Готов");

    private final String name;
    OrderState(String name) { this.name = name; }
    public String getName() { return name; }

}
