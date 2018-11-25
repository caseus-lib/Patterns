package process.hall;

import enums.OrderState;
import process.model.Order;
import process.observer.Observer;
import process.observer.Subject;

import java.util.*;
import java.util.stream.Collectors;


public class Monitor extends Subject implements Observer {

    private static Monitor ourInstance = new Monitor();

    public static Monitor getInstance() {
        return ourInstance;
    }

    private Monitor() {
        Arrays.stream(OrderState.values()).forEach(orderState -> orders.put(orderState, new ArrayList<>()));
    }

    private Map<OrderState, List<Order>> orders = new HashMap<>();

    @Override
    public synchronized void update(Subject subject) {
        if (subject instanceof Order) {
            System.out.println("-----UPDATE");
            Order order = ((Order) subject);
            orders.forEach((orderState, orderList) -> orderList.remove(order));
            orders.get(order.getOrderState()).add(order);
            notifyObserver();
        }
    }
    public synchronized List<Integer> getWaitingOrderList() {
        return orders.get(OrderState.WAITING).stream().map(Order::getOrderNumber).collect(Collectors.toList());
    }

    public synchronized List<Integer> getCookingOrderList() {
        return orders.get(OrderState.COOKING).stream().map(Order::getOrderNumber).collect(Collectors.toList());
    }

    public synchronized List<Integer> getReadyOrderList() {
        return orders.get(OrderState.READY).stream().map(Order::getOrderNumber).collect(Collectors.toList());
    }

    public Map<OrderState, List<Order>> getOrders() {
        return orders;
    }
}
