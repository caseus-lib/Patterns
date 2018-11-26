package environment.creatures;

import environment.magical.powers.MagicalPower;
import environment.products.Product;
import process.hall.Visitor;

public interface OrdinalCreature {

    void consume(Product product);
    MagicalPower getMinPower();
    void acceptVisitor(Visitor visitor);

}
