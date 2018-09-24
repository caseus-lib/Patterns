package magical.kitchen;

import magical.products.MagicalProduct;
import magical.products.Product;
import magical.products.SimpleMagicalProduct;

import java.util.Optional;
import java.util.Random;

public class BoxMaker {

    private static ShowCase showCase = ShowCase.getInstance();

    public static Product createRandomBox() {
        int amount = new Random().nextInt(5);
        MagicalProduct magicalProduct = new MagicalProduct("Волшебный набор");
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
