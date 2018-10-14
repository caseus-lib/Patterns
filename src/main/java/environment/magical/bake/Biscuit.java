package environment.magical.bake;

import environment.magical.powers.Strength;

public class Biscuit extends Bake {

    private static String NAME = "Biscuit";

    public Biscuit() {
        super(new Strength(5), NAME);
    }
}
