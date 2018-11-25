package process.kitchen;

import enums.OrderState;
import environment.products.Product;
import process.model.Order;

import java.util.concurrent.CompletableFuture;

public class Manager {

    private static Manager ourInstance = new Manager();

    public static Manager getInstance() {
        return ourInstance;
    }

    private Manager() {}

    private Kitchen kitchen = Kitchen.getInstance();

    public void acceptOrder(Order order) {
        order.setOrderState(OrderState.WAITING);
        System.out.println(order.getOrderState());
        CompletableFuture.supplyAsync(() -> kitchen.startCook(order))
                         .thenAccept(this::acceptProduct);
    }

    public void acceptProduct(Product product) {

    }


}
