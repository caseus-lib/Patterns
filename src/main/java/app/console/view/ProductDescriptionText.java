package app.console.view;

import enums.ContextType;
import environment.Counter;
import environment.products.Product;
import viewer.Context;
import viewer.ProductView;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class ProductDescriptionText implements ProductView {

    private String bigProduct;
    private String smallProduct;
    private String description;
    private Product product;
    private boolean isSmall;

    public ProductDescriptionText() {
    }

    public ProductDescriptionText(Product product) {
        this.product = product;
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
                    )).toURI()))) + "\n" + product.getName() + "\n";
        } catch (NullPointerException e) {
            return "";
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void setState(Context context) {
        if (context.getContextType() == ContextType.SHOW_CASE) {
            isSmall = false;
            description = bigProduct +
                    (context.getAmount() != null ? "Количество: " + context.getAmount() : "") +
                    "\n\n" + product.getInfoAboutComponents();
        } else {
            isSmall = true;
            description = smallProduct + "\n\n" +
                    "Стоимость: " + Counter.getInstance().countTotalProductCost(product);
        }
    }

    @Override
    public String toString() {
        return description;
    }
}
