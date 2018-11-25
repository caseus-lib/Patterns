package enums;

public enum ProductType {

    CAKE("Кекс"),
    CANDY("Конфетка"),
    BISCUIT("Печенье");

    private final String name;
    ProductType(String name) { this.name = name; }
    public String getName() { return name; }

}
