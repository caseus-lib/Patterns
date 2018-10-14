package environment.sale;

import environment.kitchen.ShowCase;
import environment.magical.powers.MagicalPower;
import environment.products.Product;

import java.util.Arrays;
import java.util.Optional;

public class Seller implements Bakery {
    private ShowCase showCase = ShowCase.getInstance();

    @Override
    public void addProduct(Product ...products) {
        Arrays.stream(products).forEach(product ->  showCase.add(product));
    }

    @Override
    public void saleProduct(Product product) {
        showCase.remove(product);
    }

    @Override
    public Optional<Product> hasProduct(MagicalPower magicalPower) {
        return showCase.getAllProducts().stream()
                .filter(product -> product.hasPower(magicalPower))
                .findFirst();
    }

    @Override
    public Optional<Product> hasAnyProduct() {
        return showCase.getAllProducts().stream().findAny();
    }

}
