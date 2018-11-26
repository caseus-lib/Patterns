package environment.kitchen;

import enums.BaseProduct;
import environment.products.MagicalProduct;
import environment.products.Product;
import environment.products.ProductObjectPool;
import exception.NoProductFound;

public class Cooker {

    private static ProductObjectPool productObjectPool = ProductObjectPool.getInstance();
    private static IndirectStorage storage = new IndirectStorage();

    public static Product cookCake() {
        MagicalProduct cake = productObjectPool.asquire();
        cake.setName("Кекс");
        cookProduct(cake, 4, 2, 3);
        return cake;
    }

    public static Product cookCandy() {
        MagicalProduct cookie = productObjectPool.asquire();
        cookie.setName("Конфетка");
        cookProduct(cookie, 0, 0, 20);
        return cookie;
    }

    public static Product cookBiscuit() {
        MagicalProduct biscuit = productObjectPool.asquire();
        biscuit.setName("Печенье");
        cookProduct(biscuit, 2, 4, 2);
        return biscuit;
    }

    private static void cookProduct(Product product, int flour, int egg, int sugar) {
        for (int i = 0; i < flour; i++) {
            product.addComponent(storage.getByName(BaseProduct.FlOUR.getName())
                    .orElseThrow(() -> new NoProductFound(BaseProduct.FlOUR.getName())));
        }
        for (int i = 0; i < egg; i++) {
            product.addComponent(storage.getByName(BaseProduct.EGG.getName())
                    .orElseThrow(() -> new NoProductFound(BaseProduct.EGG.getName())));
        }
        for (int i = 0; i < sugar; i++) {
            product.addComponent(storage.getByName(BaseProduct.SUGAR.getName())
                    .orElseThrow(() -> new NoProductFound(BaseProduct.SUGAR.getName())));
        }
    }

}
