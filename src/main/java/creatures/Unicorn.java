package creatures;

import magical.powers.MagicalPowerList;
import magical.products.Rainbow;

public class Unicorn implements MagicalCreature {

    private MagicalPowerList magicalPowerList;

    public void consume(Rainbow rainbow) {
        magicalPowerList.merge(rainbow.getMagicalPowerList());
    }

}
