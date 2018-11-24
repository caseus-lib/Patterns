package process.model;

import enums.Goods;
import enums.State;

public class Order {

    private Goods productType;
    private int amount;
    private State state;
    private int orderNumber;

    public Order() {
    }

    public Order(Goods productType, int amount, State state, int orderNumber) {
        this.productType = productType;
        this.amount = amount;
        this.state = state;
        this.orderNumber = orderNumber;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Goods getProductType() {
        return productType;
    }

    public int getAmount() {
        return amount;
    }

    public State getState() {
        return state;
    }

    public int getOrderNumber() {
        return orderNumber;
    }
}
