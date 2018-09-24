package magical.kitchen;

import magical.products.Product;
import magical.products.SimpleMagicalProduct;

import java.util.List;
import java.util.Optional;

public interface Storage {

    void add (Product product);
    void remove (Product product);
    List<Product> getAllProducts();
    Optional<Product> getByName(String name);

}
