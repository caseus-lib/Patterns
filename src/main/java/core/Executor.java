package core;

import creatures.OrdinalCreature;
import magical.kitchen.BoxMaker;
import magical.powers.MagicalPower;
import magical.products.Product;
import magical.products.SpiceDecorator;
import sale.Bakery;
import sale.SellerProxy;

import java.util.Optional;

public class Executor implements Infrastructure {

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
    public Product askForBox() {
        return product = BoxMaker.createRandomBox();
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
