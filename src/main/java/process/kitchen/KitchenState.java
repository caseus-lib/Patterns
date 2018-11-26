package process.kitchen;

import environment.products.Product;
import process.model.Order;

import java.util.List;

public interface KitchenState {

    List<Product> startCook(Order order);

}
