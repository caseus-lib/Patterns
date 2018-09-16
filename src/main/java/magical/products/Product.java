package magical.products;

import iterator.Iterator;
import magical.powers.MagicalPowerList;

public interface Product {

    MagicalPowerList getMagicalPowerList();
    Iterator<Product> getComponents();
    void addComponent(Product product);
    void removeComponent(Product product);

}
