package core;

import creatures.OrdinalCreature;
import magical.powers.MagicalPower;
import magical.products.Product;

import java.util.Optional;

public interface Infrastructure {

    void assignNewOrdinalCreature(OrdinalCreature ordinalCreature);
    MagicalPower retrieveOrdinalCreatureMinPower();
    void setBoxActiveState(boolean state);
    boolean isBoxActive();
    Optional<Product> askForProduct();
    Product askForBox();
    void saleProduct(Product product);
    void consumeProduct();
    String retrieveCreatureInformation();
    Product decorateProduct();

}
