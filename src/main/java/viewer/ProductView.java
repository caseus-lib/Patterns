package viewer;

import environment.products.Product;

public interface ProductView {

    void setState(Context context);
    ProductView clone(Product product);

}
