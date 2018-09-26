package magical.kitchen;

import magical.products.Product;
import magical.products.SimpleMagicalProduct;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ShowCase implements Storage {

    private List<Product> products;

    private static ShowCase ourInstance = new ShowCase();

    public static ShowCase getInstance() {
        return ourInstance;
    }

    private ShowCase() {
        products = new ArrayList<>();
    }

    @Override
    public Optional<Product> getByName(String name) {
        Optional<Product> first = products.stream().filter(product -> product.getName().equals(name)).findFirst();
        first.ifPresent(this::remove);
        return first;
    }

    @Override
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public void remove(Product product) {
        products.remove(product);
    }

    @Override
    public List<Product> getAllProducts() {
        Collections.shuffle(products);
        return products;
    }
}
