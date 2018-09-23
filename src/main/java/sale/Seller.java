package sale;

import magical.powers.MagicalPower;
import magical.products.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Seller implements Bakery {
    private List<Product> products;

    Seller() {
        products = new ArrayList<>();
    }

    @Override
    public void addProduct(Product ...products) {
        this.products.addAll(Arrays.asList(products));
    }

    @Override
    public void saleProduct(Product product) {
        products.remove(product);
    }

    @Override
    public Optional<Product> hasProduct(MagicalPower magicalPower) {
        return products.stream()
                .filter(product -> product.hasPower(magicalPower))
                .findFirst();
    }

    @Override
    public Optional<Product> hasAnyProduct() {
        return products.stream().findAny();
    }

}
