package environment;

import environment.products.Product;
import environment.products.SimpleMagicalProduct;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Counter {

    private Map<String, Double> productCostMap = new HashMap<>();
    private static Counter instance = new Counter();

    private Counter() {}

    public static Counter getInstance() {
        return instance;
    }

    public void addProduct(String name, double cost) {
        productCostMap.put(name, cost);
    }

    public double countTotalProductCost(Product product) {
        return product.getComponents().stream().map(component -> {
            if (component instanceof SimpleMagicalProduct) {
                return Optional.ofNullable(productCostMap.get(component.getName())).orElse(0.0);
            }
            else {
                return countTotalProductCost(component);
            }
        }).reduce((x, y) -> x + y).orElse(0.0);
    }


}
