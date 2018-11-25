package process.kitchen;

import enums.OrderState;
import environment.products.Product;
import process.model.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtraditionPlace {

    private Map<Order, List<Product>> readyOrderList;

    private static ExtraditionPlace ourInstance = new ExtraditionPlace();

    public static ExtraditionPlace getInstance() {
        return ourInstance;
    }

    private ExtraditionPlace() {
        readyOrderList = new HashMap<>();
    }

    public synchronized void add(Order order, List<Product> products) {
        System.out.println("order = [" + order + "], products = [" + products + "]");
        readyOrderList.put(order, products);
    }

    public void remove(Order order) {
        order.setOrderState(OrderState.RELEASED);
        readyOrderList.remove(order);
    }

    public Map<Order, List<Product>> getReadyOrderList() {
        return readyOrderList;
    }
}
