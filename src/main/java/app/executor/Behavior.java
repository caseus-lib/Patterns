package app.executor;

import environment.creatures.OrdinalCreature;
import environment.magical.powers.MagicalPower;
import environment.products.Product;
import environment.products.SweetBox;

import java.util.Optional;

public interface Behavior {

    void assignNewOrdinalCreature(OrdinalCreature ordinalCreature);
    MagicalPower retrieveOrdinalCreatureMinPower();
    void setBoxActiveState(boolean state);
    boolean isBoxActive();
    Optional<Product> askForProduct();
    SweetBox askForBox();
    void saleProduct(Product product);
    void consumeProduct();
    String retrieveCreatureInformation();
    Product decorateProduct();

}
