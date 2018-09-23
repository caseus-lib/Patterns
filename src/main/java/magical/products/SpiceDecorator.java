package magical.products;

import magical.powers.MagicalPower;
import magical.powers.MagicalPowerList;
import magical.powers.PowersFactory;

import java.util.List;

public class SpiceDecorator implements Product {

    private Product product;

    public SpiceDecorator(Product product) {
        this.product = product;
    }

    @Override
    public MagicalPowerList getMagicalPowerList() {
        return product.getMagicalPowerList().add(PowersFactory.getRandomPower());
    }

    @Override
    public List<Product> getComponents() {
        return product.getComponents();
    }

    @Override
    public void addComponent(Product product) {
        this.product.addComponent(product);
    }

    @Override
    public void removeComponent(Product product) {
        this.product.removeComponent(product);
    }

    @Override
    public String getName() {
        return product.getName();
    }

    @Override
    public void setName(String name) {
        product.setName(name);
    }

    @Override
    public void addPower(MagicalPower magicalPower) {
        product.addPower(magicalPower);
    }

    @Override
    public boolean hasPower(MagicalPower magicalPower) {
        return product.hasPower(magicalPower);
    }
}
