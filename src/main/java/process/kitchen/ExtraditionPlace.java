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

    private ExtraditionMemento extraditionMemento;

    private ExtraditionPlace() {
        readyOrderList = new HashMap<>();
    }

    public synchronized void add(Order order, List<Product> products) {
        recover();
        if (order.getOrderNumber() > 0) {
            readyOrderList.put(order, products);
        }
        backup();
    }

    public void remove(Order order) {
        recover();
        order.setOrderState(OrderState.RELEASED);
        readyOrderList.remove(order);
        backup();
    }

    private void recover() {
        if (extraditionMemento != null) {
            readyOrderList = extraditionMemento.getExtraditionState().getReadyOrderList();
        }
    }

    private void backup() {
        GoodWitch goodWitch = GoodWitch.getInstance();
        extraditionMemento = goodWitch.createMemento(new ExtraditionState(readyOrderList));
    }

    public Map<Order, List<Product>> getReadyOrderList() {
        return readyOrderList;
    }
}
