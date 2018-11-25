package process.model;

import environment.products.Product;

import java.util.List;

public class ReadyOrder {

   private Order order;
   private List<Product> productList;

    public ReadyOrder(Order order, List<Product> productList) {
        this.order = order;
        this.productList = productList;
    }

    public Order getOrder() {
        return order;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
