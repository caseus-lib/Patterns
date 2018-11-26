package environment.kitchen;

import environment.products.Product;
import environment.products.SimpleMagicalProduct;

import java.util.List;
import java.util.Optional;

public class IndirectStorage implements Storage {

    private Fridge fridge = Fridge.getInstance();

    @Override
    public void add(Product product) {
        fridge.add(product);
    }

    @Override
    public void remove(Product product) {
        fridge.remove(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return fridge.getAllProducts();
    }

    @Override
    public Optional<Product> getByName(String name) {
        return fridge.getByName(name);
    }

    public void addSeveral(SimpleMagicalProduct product, int amount) {
        fridge.addSeveral(product, amount);
    }
}
