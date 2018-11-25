package process.kitchen;

import enums.OrderState;
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
        CompletableFuture.supplyAsync(() -> kitchen.startCook(order));
    }


}
