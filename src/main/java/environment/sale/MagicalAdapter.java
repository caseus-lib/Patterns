package environment.sale;

import environment.creatures.OrdinalCreature;
import environment.creatures.Unicorn;
import environment.magical.powers.Happiness;
import environment.magical.powers.MagicalPower;
import environment.products.Product;
import environment.products.Rainbow;

public class MagicalAdapter implements OrdinalCreature {

    private Unicorn unicorn;

    public MagicalAdapter(Unicorn unicorn) {
        this.unicorn = unicorn;
    }

    @Override
    public void consume(Product product) {
        unicorn.consume(convert(product));
    }

    private Rainbow convert(Product product) {
        Rainbow rainbow = new Rainbow();
        rainbow.getMagicalPowerList().merge(product.getMagicalPowerList());
        return rainbow;
    }

    @Override
    public MagicalPower getMinPower() {
        return new Happiness(0);
    }
}
