package magical.products;

import magical.powers.MagicalPower;
import magical.powers.MagicalPowerList;

import java.util.Arrays;

public class SimpleMagicalProduct extends MagicalProduct {

    public SimpleMagicalProduct() {
        super();
    }

    public SimpleMagicalProduct(String name, MagicalPower... magicalPowers) {
        super(name);
        magicalPowerList = new MagicalPowerList(Arrays.asList(magicalPowers));
    }

    public SimpleMagicalProduct(SimpleMagicalProduct simpleMagicalProduct) {
        this();
        this.setName(simpleMagicalProduct.getName());
        this.magicalPowerList = new MagicalPowerList(simpleMagicalProduct.getMagicalPowerList());
    }

    @Override
    public MagicalPowerList getMagicalPowerList() {
        return this.magicalPowerList;
    }
}
