package viewer;

import environment.products.Product;

import java.util.HashMap;
import java.util.Map;

public abstract class ProductViewFactory {

    private Map<String, ProductView> productImageMap = new HashMap<>();
    private ProductView productViewPrototype;

    protected ProductViewFactory(ProductView productViewPrototype) {
        this.productViewPrototype = productViewPrototype;
    }

    public ProductView getProductImage(Product product, Context context) {
        if (productImageMap.containsKey(product.toString() + product.getName())) {
            ProductView productImage = productImageMap.get(product.toString() + product.getName());
            productImage.setState(context);
            return productImage;
        } else {
            ProductView productImage = productViewPrototype.clone(product);
            productImage.setState(context);
            productImageMap.put(product.toString() + product.getName(), productImage);
            return productImage;
        }
    }

}
