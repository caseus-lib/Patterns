package environment.kitchen;

import environment.products.SimpleMagicalProduct;
import environment.products.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Fridge implements Storage {

    private List<Product> products;

    private static Fridge ourInstance = new Fridge();

    public static Fridge getInstance() {
        return ourInstance;
    }

    private Fridge() {
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

    public void addSeveral(SimpleMagicalProduct product, int amount) {
        while (amount-- > 0) {
            products.add(new SimpleMagicalProduct(product));
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }
}
