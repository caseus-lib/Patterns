package app;

import environment.Counter;
import environment.kitchen.Cooker;
import environment.kitchen.Fridge;
import environment.kitchen.ShowCase;
import environment.magical.powers.Happiness;
import environment.magical.powers.Responsibility;
import environment.magical.powers.Strength;
import environment.products.SimpleMagicalProduct;

public class Initialization {

    private static Fridge fridge = Fridge.getInstance();
    private static ShowCase showCase = ShowCase.getInstance();
    private static Counter counter = Counter.getInstance();

    private static void addSimpleProducts() {
        fridge.addSeveral(
                new SimpleMagicalProduct(
                        "Мука", new Happiness(2)
                ),
                100
        );
        counter.addProduct("Мука", 2);
        fridge.addSeveral(
                new SimpleMagicalProduct(
                        "Яйцо", new Responsibility(5)
                ),
                100
        );
        counter.addProduct("Яйцо", 5);
        fridge.addSeveral(
                new SimpleMagicalProduct(
                        "Сахар",
                        new Strength(3), new Happiness(10)
                ),
                500);
        counter.addProduct("Сахар", 1);

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
