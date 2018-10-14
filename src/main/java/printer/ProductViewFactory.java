package printer;

import app.graphic.ui.services.Size;
import environment.products.Product;

import java.util.HashMap;
import java.util.Map;

public abstract class ProductViewFactory {

    private Map<String, ProductView> productImageMap = new HashMap<>();
    private ProductView productViewPrototype;

    protected ProductViewFactory(ProductView productViewPrototype) {
        this.productViewPrototype = productViewPrototype;
    }

    public ProductView getProductImage(Product product, Size size) {
        if (productImageMap.containsKey(product.getName())) {
            ProductView productImage = productImageMap.get(product.getName());
            productImage.setSize(size.getX(), size.getY());
            return productImage;
        } else {
            ProductView productImage = productViewPrototype.clone(product);
            productImage.setSize(size.getX(), size.getY());
            productImageMap.put(product.getName(), productImage);
            return productImage;
        }
    }

}
