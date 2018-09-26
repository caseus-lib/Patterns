package magical.products;

import magical.powers.MagicalPower;
import magical.powers.MagicalPowerList;

import java.util.List;

public interface Product {

    MagicalPowerList getMagicalPowerList();
    List<Product> getComponents();
    void addComponent(Product product);
    void removeComponent(Product product);
    String getName();
    void setName(String name);
    void addPower(MagicalPower magicalPower);
    boolean hasPower(MagicalPower magicalPower);
    String getInfoAboutComponents();

}
