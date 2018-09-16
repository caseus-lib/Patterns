package magical.kitchen;

import magical.products.Product;

import java.util.List;

public interface Storage {

    void add (Product product);
    void remove (Product product);
    List<Product> getAllProducts();

}
