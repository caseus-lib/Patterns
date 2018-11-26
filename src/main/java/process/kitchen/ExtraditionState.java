package process.kitchen;

import environment.products.Product;
import process.model.Order;

import java.util.List;
import java.util.Map;

public class ExtraditionState {

    private Map<Order, List<Product>> readyOrderList;

    public ExtraditionState() {
    }

    public ExtraditionState(Map<Order, List<Product>> readyOrderList) {
        this.readyOrderList = readyOrderList;
    }

    public Map<Order, List<Product>> getReadyOrderList() {
        return readyOrderList;
    }

    public void setReadyOrderList(Map<Order, List<Product>> readyOrderList) {
        this.readyOrderList = readyOrderList;
    }
}
