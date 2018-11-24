package process.kitchen;

import enums.State;
import environment.products.Product;
import environment.products.SimpleMagicalProduct;
import process.model.Order;

import static java.lang.Thread.sleep;

public class Kitchen {

    public Product startCook(Order order) {
        try {
            System.out.println(order.getState());
            sleep(2);
            order.setState(State.COOKING);
            System.out.println(order.getState());
            sleep(2);
            return cookProduct(order);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    private Product cookProduct(Order order) {
        order.setState(State.READY);
        System.out.println(order.getState());
        return new SimpleMagicalProduct();
    }

}
