package process.hall;

import enums.OrderState;
import process.model.Order;
import process.observer.Observer;
import process.observer.Subject;

import java.util.*;
import java.util.stream.Collectors;


public class Monitor extends Subject implements Observer {

    private Map<OrderState, List<Order>> orders = new HashMap<>();

    public Monitor() {
        Arrays.stream(OrderState.values()).forEach(orderState -> orders.put(orderState, new ArrayList<>()));
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof Order) {
            Order order = ((Order) subject);
            orders.forEach((orderState, orderList) -> orderList.remove(order));
            orders.get(order.getOrderState()).add(order);
            notifyObserver();
        }
    }

    public List<Integer> getWaitingOrderList() {
        return orders.get(OrderState.WAITING).stream().map(Order::getOrderNumber).collect(Collectors.toList());
    }

    public List<Integer> getCookingOrderList() {
        return orders.get(OrderState.COOKING).stream().map(Order::getOrderNumber).collect(Collectors.toList());
    }

    public List<Integer> getReadyOrderList() {
        return orders.get(OrderState.READY).stream().map(Order::getOrderNumber).collect(Collectors.toList());
    }

}
