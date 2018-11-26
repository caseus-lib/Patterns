package process.bakery;

import enums.ProductType;
import enums.TimesOfDay;
import environment.creatures.OrdinalCreature;
import environment.kitchen.BoxMaker;
import environment.products.Product;
import environment.products.SweetBox;
import environment.sale.Bakery;
import environment.sale.SellerProxy;
import process.command.OrderCommand;
import process.hall.BadWitch;
import process.hall.JournalVisitor;
import process.kitchen.ExtraditionPlace;
import process.kitchen.Kitchen;
import process.model.Order;

import java.util.Collections;

public class BakeryShopBehaviorExecutor implements BakeryShopBehavior {

    private ProductType productType;
    private Bakery seller = new SellerProxy();
    private Product product;
    private int amount;
    private OrdinalCreature ordinalCreature;
    private int ordersNumber = 0;
    private BadWitch badWitch = new BadWitch();
    private JournalVisitor visitor = new JournalVisitor();

    @Override
    public SweetBox askForBox() {
        SweetBox randomBox = BoxMaker.createRandomBox();
        product = randomBox;
        return randomBox;
    }

    @Override
    public void saleBox() {
        seller.saleProduct(product);
    }

    @Override
    public String getJournalText() {
        return String.join("\n", visitor.getJournal());
    }

    public void visitCreature() {
        ordinalCreature.acceptVisitor(visitor);
    }

    @Override
    public void askForProductAmount(ProductType productType, int amount) {
        this.productType = productType;
        this.amount = amount;
    }

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

    @Override
    public String retrieveOrderInformation() {
        return productType.getName() + ": " + amount;
    }

    @Override
    public void destroyAll() {
        badWitch.destroyAll();
    }

    @Override
    public void recoverAll() {
        ExtraditionPlace instance = ExtraditionPlace.getInstance();
        instance.add(new Order(-1), Collections.emptyList());
    }

    @Override
    public void setTimesOfDay(TimesOfDay timesOfDay) {
        Kitchen.getInstance().setState(timesOfDay);
    }
}
