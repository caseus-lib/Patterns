package environment.magical.bake;

import environment.magical.powers.Responsibility;

public class Candy extends Bake {

    private static String NAME = "Candy";

    public Candy() {
        super(new Responsibility(10), NAME);
    }
}
