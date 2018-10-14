package printer;

import environment.products.Product;

public interface ProductView {

    void setSize(double x, double y);
    ProductView clone(Product product);

}
