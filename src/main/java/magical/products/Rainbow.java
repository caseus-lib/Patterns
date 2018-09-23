package magical.products;

import magical.powers.MagicalPowerList;
import magical.powers.PowersFactory;

public class Rainbow {

    private MagicalPowerList magicalPowerList;

    public Rainbow() {
        magicalPowerList = new MagicalPowerList(PowersFactory.getPowersList());
        magicalPowerList.createTravelIterator().travel(object -> {
            object.setAmount(1000);
            return true;
        });
    }

    public MagicalPowerList getMagicalPowerList() {
        return magicalPowerList;
    }

}
