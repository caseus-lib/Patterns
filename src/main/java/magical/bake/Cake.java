package magical.bake;

import magical.powers.Happiness;

public class Cake extends Bake {

    private static String NAME = "Cake";

    public Cake() {
        super(new Happiness(3), NAME);
    }
}
