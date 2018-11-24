package enums;

public enum  Goods {

    CAKE("Кекс"),
    CANDY("Конфетка"),
    BISCUIT("Печенье");

    private final String name;
    Goods(String name) { this.name = name; }
    public String getName() { return name; }

}
