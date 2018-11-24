package environment.products;

import enums.Color;

import static enums.Color.WHITE;

public class SweetBox extends MagicalProduct {

    private Color color = WHITE;
    private Boolean withBow = false;

    public SweetBox(String name) {
        super(name);
    }

    public void buildColorBox(Color color) {
        this.color = color;
    }

    public void buildBoxWithBow() {
        this.withBow = true;
    }

    public Color getColor() {
        return color;
    }

    public Boolean getWithBow() {
        return withBow;
    }
}
