package magical.bake;

import iterator.TravelIterator;
import magical.powers.MagicalPower;
import magical.products.MagicalProduct;

public abstract class Bake extends MagicalProduct {

    private String name;

    Bake(String name) {
        this.name = name;
    }

    Bake(MagicalPower magicalPower, String name) {
        this.name = name;
        magicalPowerList.add(magicalPower);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean hasPower(MagicalPower magicalPower) {
        TravelIterator<MagicalPower> travelIterator = magicalPowerList.createTravelIterator();
        return travelIterator.travel(object -> object.same(magicalPower));
    }
}
