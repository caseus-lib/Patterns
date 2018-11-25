package process.bakery;

import environment.creatures.OrdinalCreature;

public interface BakeryShopBehavior {

    void assignNewOrdinalCreature(OrdinalCreature ordinalCreature);
    int generateOrderNumber();
    void acceptOrder();
    String retrieveCreatureInformation();


}
