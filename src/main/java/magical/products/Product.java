package magical.products;

import magical.powers.MagicalPowerList;

import java.util.List;

public interface Product {

    MagicalPowerList getMagicalPowerList();
    List<Product> getComponents();
    void addComponent(Product product);
    void removeComponent(Product product);

}
