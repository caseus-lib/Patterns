package enums;

public enum OrderState {

    WAITING("Ожидание"),
    COOKING("Готовится"),
    READY("Готов"),
    RELEASED("Отдан");

    private final String name;
    OrderState(String name) { this.name = name; }
    public String getName() { return name; }

}
