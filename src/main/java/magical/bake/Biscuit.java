package magical.bake;

import magical.powers.MagicalPower;
import magical.powers.Strength;

public class Biscuit extends Bake {

    private static String NAME = "Biscuit";

    public Biscuit() {
        super(new Strength(5), NAME);
    }
}
