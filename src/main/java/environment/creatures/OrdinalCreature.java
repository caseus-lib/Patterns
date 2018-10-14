package environment.creatures;

import environment.magical.powers.MagicalPower;
import environment.products.Product;

public interface OrdinalCreature {

    void consume(Product product);
    MagicalPower getMinPower();

}
