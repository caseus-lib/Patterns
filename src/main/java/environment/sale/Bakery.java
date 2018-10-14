package environment.sale;

import environment.magical.powers.MagicalPower;
import environment.products.Product;

import java.util.Optional;

public interface Bakery {

    void addProduct(Product... products);
    Optional<Product> hasProduct(MagicalPower magicalPower);
    Optional<Product> hasAnyProduct();
    void saleProduct(Product bake);

}
