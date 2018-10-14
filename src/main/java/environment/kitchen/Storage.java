package environment.kitchen;

import environment.products.Product;

import java.util.List;
import java.util.Optional;

public interface Storage {

    void add (Product product);
    void remove (Product product);
    List<Product> getAllProducts();
    Optional<Product> getByName(String name);

}
