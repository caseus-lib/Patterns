package enums;

public enum State {

    WAITING("Ожидание"),
    COOKING("Готовится"),
    READY("Готов");

    private final String name;
    State(String name) { this.name = name; }
    public String getName() { return name; }

}
