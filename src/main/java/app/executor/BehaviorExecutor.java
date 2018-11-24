package app.executor;

import environment.creatures.OrdinalCreature;
import environment.kitchen.BoxMaker;
import environment.magical.powers.MagicalPower;
import environment.products.Product;
import environment.products.SpiceDecorator;
import environment.products.SweetBox;
import environment.sale.Bakery;
import environment.sale.SellerProxy;

import java.util.Optional;

public class BehaviorExecutor implements Behavior {

    private Bakery seller = new SellerProxy();
    private Product product;
    private MagicalPower magicalPower;
    private OrdinalCreature ordinalCreature;
    private boolean isBoxActive = false;

    @Override
    public void assignNewOrdinalCreature(OrdinalCreature ordinalCreature) {
        this.ordinalCreature = ordinalCreature;
    }

    @Override
    public MagicalPower retrieveOrdinalCreatureMinPower() {
        return magicalPower = ordinalCreature.getMinPower();
    }

    @Override
    public void setBoxActiveState(boolean isActive) {
        isBoxActive = isActive;
    }

    @Override
    public boolean isBoxActive() {
        return isBoxActive;
    }

    @Override
    public SweetBox askForBox() {
        SweetBox randomBox = BoxMaker.createRandomBox();
        product = randomBox;
        return randomBox;
    }

    @Override
    public Optional<Product> askForProduct() {
        return seller.hasProduct(magicalPower);
    }

    @Override
    public void saleProduct(Product product) {
        this.product = product;
        seller.saleProduct(product);
    }

    @Override
    public void consumeProduct() {
        ordinalCreature.consume(product);
    }

    @Override
    public String retrieveCreatureInformation() {
        return ordinalCreature.toString();
    }

    @Override
    public Product decorateProduct() {
        return product = new SpiceDecorator(product);
    }
}
