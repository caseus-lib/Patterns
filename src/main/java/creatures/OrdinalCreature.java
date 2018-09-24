package creatures;

import magical.powers.MagicalPower;
import magical.products.Product;

public interface OrdinalCreature {

    void consume(Product product);
    MagicalPower getMinPower();

}
