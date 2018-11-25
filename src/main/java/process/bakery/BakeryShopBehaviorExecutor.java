package process.bakery;

import enums.ProductType;
import environment.creatures.OrdinalCreature;
import process.command.OrderCommand;

public class BakeryShopBehaviorExecutor implements BakeryShopBehavior {

    private ProductType productType;
    private int amount;
    private OrdinalCreature ordinalCreature;
    private int ordersNumber = 0;

    @Override
    public void assignNewOrdinalCreature(OrdinalCreature ordinalCreature) {
        this.ordinalCreature = ordinalCreature;
    }

    @Override
    public int generateOrderNumber() {
        return ++ordersNumber;
    }

    @Override
    public void acceptOrder() {
        new OrderCommand(productType, amount, ordersNumber).execute();
    }

    @Override
    public String retrieveCreatureInformation() {
        return ordinalCreature.toString();
    }
}
