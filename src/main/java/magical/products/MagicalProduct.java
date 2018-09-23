package magical.products;

import iterator.TravelIterator;
import magical.powers.MagicalPower;
import magical.powers.MagicalPowerList;

import java.util.ArrayList;
import java.util.List;

public class MagicalProduct implements Product {

    protected List<Product> components;
    protected MagicalPowerList magicalPowerList = new MagicalPowerList();
    private String name;

    public MagicalProduct() {
        components = new ArrayList<>();
    }

    @Override
    public void addPower(MagicalPower magicalPower) {
        magicalPowerList.add(magicalPower);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public MagicalPowerList getMagicalPowerList() {
        return constructMagicalPowerList();
    }

    private MagicalPowerList constructMagicalPowerList() {
        magicalPowerList = new MagicalPowerList();
        components.forEach(
                product -> magicalPowerList.merge(
                        product.getMagicalPowerList()
                )
        );
        return magicalPowerList;
    }

    @Override
    public List<Product> getComponents() {
        return components;
    }

    @Override
    public void addComponent(Product product) {
        components.add(product);
    }

    @Override
    public void removeComponent(Product product) {
        components.remove(product);
    }

    @Override
    public boolean hasPower(MagicalPower magicalPower) {
        TravelIterator<MagicalPower> travelIterator = magicalPowerList.createTravelIterator();
        return travelIterator.travel(object -> object.same(magicalPower));
    }

    @Override
    public String toString() {
        return name;
    }
}
