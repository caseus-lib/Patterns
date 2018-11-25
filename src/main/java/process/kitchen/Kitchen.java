package process.kitchen;

import enums.OrderState;
import environment.products.Product;
import environment.products.SimpleMagicalProduct;
import process.model.Order;

import static java.lang.Thread.sleep;

public class Kitchen {

    private static Kitchen ourInstance = new Kitchen();

    public static Kitchen getInstance() {
        return ourInstance;
    }

    private Kitchen() {
    }

    public Product startCook(Order order) {
        try {
            System.out.println(order.getOrderState());
            sleep(2);
            order.setOrderState(OrderState.COOKING);
            System.out.println(order.getOrderState());
            sleep(2);
            return cookProduct(order);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    private Product cookProduct(Order order) {
        order.setOrderState(OrderState.READY);
        System.out.println(order.getOrderState());
        return new SimpleMagicalProduct();
    }

}
