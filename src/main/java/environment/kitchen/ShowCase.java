package environment.kitchen;

import environment.products.Product;

import java.util.*;

public class ShowCase implements Storage {

    private List<Product> products;
    private Map<String, Integer> productAmountMap;

    private static ShowCase ourInstance = new ShowCase();

    public static ShowCase getInstance() {
        return ourInstance;
    }

    private ShowCase() {
        products = new ArrayList<>();
        productAmountMap = new HashMap<>();
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
        changeProductAmount(product, 1);
    }

    private void changeProductAmount(Product product, Integer value) {
        if (!productAmountMap.containsKey(product.getName()))
            productAmountMap.put(product.getName(), 0);
        productAmountMap.replace(product.getName(), productAmountMap.get(product.getName()) + value);
    }

    @Override
    public void remove(Product product) {
        products.remove(product);
        changeProductAmount(product, -1);
    }

    public Map<String, Integer> getProductAmountMap() {
        return productAmountMap;
    }

    @Override
    public List<Product> getAllProducts() {
        Collections.shuffle(products);
        return products;
    }
}
