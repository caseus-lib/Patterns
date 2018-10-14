package app.console.view;

import environment.products.Product;
import printer.ProductView;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class ProductDescriptionText implements ProductView {

    private String bigProduct;
    private String smallProduct;
    private boolean isSmall;

    public ProductDescriptionText() {
    }

    public ProductDescriptionText(Product product) {
        bigProduct = readDescription(product, "big");
        smallProduct = readDescription(product, "small");
        isSmall = false;
    }

    @Override
    public ProductView clone(Product product) {
        return new ProductDescriptionText(product);
    }

    private String readDescription(Product product, String size) {
        try {
            return "\n" + new String(Files.readAllBytes(
                    Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource(
                            "text/" + product.getName() + "_" + size + ".txt"
                    )).toURI()))) + "\n" + product.getName();
        } catch (NullPointerException e) {
            return "";
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void setSize(double x, double y) {
        isSmall = x < 50;
    }

    @Override
    public String toString() {
        if (isSmall)
            return smallProduct;
        else
            return bigProduct;
    }
}
