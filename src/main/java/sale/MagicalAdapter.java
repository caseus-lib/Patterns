package sale;

import creatures.OrdinalCreature;
import creatures.Unicorn;
import magical.products.Product;
import magical.products.Rainbow;

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
}
