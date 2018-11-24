package environment.kitchen;

import environment.products.Product;
import environment.products.SweetBox;

import java.util.Optional;
import java.util.Random;

public class BoxMaker {

    private static ShowCase showCase = ShowCase.getInstance();

    public static SweetBox createRandomBox() {
        int amount = new Random().nextInt(5) + 1;
        SweetBox magicalProduct = new SweetBox("Волшебный набор");
        while (amount-- > 0) {
            Optional<Product> optionalProduct = showCase.getAllProducts()
                    .stream()
                    .findAny();
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                magicalProduct.addComponent(product);
                showCase.remove(product);
            }
            else {
                break;
            }
        }
        return magicalProduct;
    }

}
