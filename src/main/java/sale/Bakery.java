package sale;

import magical.powers.MagicalPower;
import magical.products.Product;

import java.util.Optional;

public interface Bakery {

    void addProduct(Product... products);
    Optional<Product> hasProduct(MagicalPower magicalPower);
    Optional<Product> hasAnyProduct();
    void saleProduct(Product bake);

}
