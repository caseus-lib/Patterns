package process.bakery;

import enums.ProductType;
import environment.creatures.OrdinalCreature;

public interface BakeryShopBehavior {

    void assignNewOrdinalCreature(OrdinalCreature ordinalCreature);
    int generateOrderNumber();
    void acceptOrder();
    String retrieveCreatureInformation();
    void askForProductAmount(ProductType productType, int amount);
    String retrieveOrderInformation();

}
