package environment.products;

import environment.magical.powers.MagicalPower;
import environment.magical.powers.MagicalPowerList;
import iterator.TravelIterator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MagicalProduct implements Product {

    private List<Product> components;
    protected MagicalPowerList magicalPowerList;
    private String name;

    protected MagicalProduct() {
        components = new ArrayList<>();
        magicalPowerList = new MagicalPowerList();
    }

    public MagicalProduct(String name) {
        this();
        this.name = name;
    }

    @Override
    public void reset() {
        components = new ArrayList<>();
        magicalPowerList = new MagicalPowerList();
        name = "";
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
        return magicalPowerList;
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
        magicalPowerList.merge(product.getMagicalPowerList());
    }

    @Override
    public void removeComponent(Product product) {
        components.remove(product);
        product.getMagicalPowerList()
                .getStream()
                .forEach(magicalPower -> magicalPower.setAmount(magicalPower.getAmount() * -1));
        magicalPowerList.merge(product.getMagicalPowerList());
    }

    @Override
    public boolean hasPower(MagicalPower magicalPower) {
        TravelIterator<MagicalPower> travelIterator = magicalPowerList.createTravelIterator();
        return travelIterator.travel(object -> object.same(magicalPower));
    }

    @Override
    public String getInfoAboutComponents() {
        return components
                .stream()
                .map(product ->
                       "\t" + product.getName() + "\n" +
                               product.getMagicalPowerList().toString()
                )
                .collect(Collectors.joining("\n"));
    }

    /*@Override
    public String toString() {
        return name;
    }*/
}
