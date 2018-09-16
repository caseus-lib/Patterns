package magical.products;

import magical.powers.MagicalPowerList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MagicalProduct implements Product {

    private List<Product> components;
    private MagicalPowerList magicalPowerList;

    public MagicalProduct() {
        components = new ArrayList<>();
    }

    @Override
    public MagicalPowerList getMagicalPowerList() {
        return Optional.ofNullable(magicalPowerList).orElse(constructMagicalPowerList());
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
}
