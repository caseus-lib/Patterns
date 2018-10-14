package environment.products;

import environment.magical.powers.MagicalPower;
import environment.magical.powers.MagicalPowerList;
import environment.magical.powers.PowersFactory;

import java.util.List;

public class SpiceDecorator implements Product {

    private Product product;

    public SpiceDecorator(Product product) {
        this.product = product;
        this.product.addPower(PowersFactory.getRandomPower());
    }

    @Override
    public String toString() {
        return product.toString();
    }

    @Override
    public MagicalPowerList getMagicalPowerList() {
        return product.getMagicalPowerList();
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

    @Override
    public String getInfoAboutComponents() {
        return product.getInfoAboutComponents();
    }
}
