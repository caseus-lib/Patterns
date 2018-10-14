package app.graphic.ui.components;

import app.graphic.ui.services.Size;
import environment.products.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductImageFactory {

    private static ProductImageFactory ourInstance = new ProductImageFactory();

    public static ProductImageFactory getInstance() {
        return ourInstance;
    }

    private Map<String, ProductImageButton> productImageMap = new HashMap<>();

    public ProductImageButton getProductImage(Product product, Size size) {
        if (productImageMap.containsKey(product.getName())) {
            ProductImageButton productImage = productImageMap.get(product.getName());
            productImage.setSize(size.getX(), size.getY());
            return productImage;
        } else {
            ProductImageButton productImage = new ProductImageButton(product);
            productImage.setSize(size.getX(), size.getY());
            productImageMap.put(product.getName(), productImage);
            return productImage;
        }
    }

}
