package magical.kitchen;

import magical.products.Product;

import java.util.ArrayList;
import java.util.List;

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
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public void remove(Product product) {
        products.remove(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }
}
