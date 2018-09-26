package app;

import magical.kitchen.Cooker;
import magical.kitchen.Fridge;
import magical.kitchen.ShowCase;
import magical.powers.Happiness;
import magical.powers.Responsibility;
import magical.powers.Strength;
import magical.products.SimpleMagicalProduct;

public class Initialization {

    private static Fridge fridge = Fridge.getInstance();
    private static ShowCase showCase = ShowCase.getInstance();

    private static void addSimpleProducts() {
        fridge.addSeveral(
                new SimpleMagicalProduct(
                        "Мука", new Happiness(2)
                ),
                100
        );
        fridge.addSeveral(
                new SimpleMagicalProduct(
                        "Яйцо", new Responsibility(5)
                ),
                100
        );
        fridge.addSeveral(
                new SimpleMagicalProduct(
                        "Сахар",
                        new Strength(3), new Happiness(10)
                ),
                500
        );
    }

    public static void init() {
        addSimpleProducts();
        for (int i = 0; i < 8; i++) {
            showCase.add(Cooker.cookCake());
        }
        for (int i = 0; i < 20; i++) {
            showCase.add(Cooker.cookCandy());
        }
        for (int i = 0; i < 16; i++) {
            showCase.add(Cooker.cookBiscuit());
        }
    }

}