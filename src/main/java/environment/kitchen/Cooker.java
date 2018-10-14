package environment.kitchen;

import enums.BaseProduct;
import exception.NoProductFound;
import environment.products.MagicalProduct;
import environment.products.Product;

public class Cooker {

    private static Fridge fridge = Fridge.getInstance();

    public static Product cookCake() {
        MagicalProduct cake = new MagicalProduct("Кекс");
        cookProduct(cake, 4, 2, 3);
        return cake;
    }

    public static Product cookCandy() {
        MagicalProduct cookie = new MagicalProduct("Конфетка");
        cookProduct(cookie, 0, 0, 20);
        return cookie;
    }

    public static Product cookBiscuit() {
        MagicalProduct biscuit = new MagicalProduct("Печенье");
        cookProduct(biscuit, 2, 4, 2);
        return biscuit;
    }

    private static void cookProduct(Product product, int flour, int egg, int sugar) {
        for (int i = 0; i < flour; i++) {
            product.addComponent(fridge.getByName(BaseProduct.FlOUR.getName())
                    .orElseThrow(() -> new NoProductFound(BaseProduct.FlOUR.getName())));
        }
        for (int i = 0; i < egg; i++) {
            product.addComponent(fridge.getByName(BaseProduct.EGG.getName())
                    .orElseThrow(() -> new NoProductFound(BaseProduct.EGG.getName())));
        }
        for (int i = 0; i < sugar; i++) {
            product.addComponent(fridge.getByName(BaseProduct.SUGAR.getName())
                    .orElseThrow(() -> new NoProductFound(BaseProduct.SUGAR.getName())));
        }
    }

}
