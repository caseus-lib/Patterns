package environment.creatures;

import environment.magical.powers.MagicalPowerList;
import environment.magical.powers.PowersFactory;
import environment.products.Rainbow;

public class Unicorn implements MagicalCreature {

    private MagicalPowerList magicalPowerList;

    public Unicorn() {
        magicalPowerList = new MagicalPowerList();
        PowersFactory.getPowersList().forEach(magicalPower -> {
            magicalPower.setAmount(100000);
            magicalPowerList.add(magicalPower);
        });
    }

    public void consume(Rainbow rainbow) {
        magicalPowerList.merge(rainbow.getMagicalPowerList());
    }
}
