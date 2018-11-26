package process.model;

import enums.OrderState;
import enums.ProductType;
import process.observer.Subject;

public class Order extends Subject {

    private ProductType productType;
    private int amount;
    private OrderState orderState;
    private int orderNumber;

    public Order() {
    }

    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Order(ProductType productType, int amount, int orderNumber) {
        this.productType = productType;
        this.amount = amount;
        this.orderNumber = orderNumber;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
        notifyObserver();
    }

    public ProductType getProductType() {
        return productType;
    }

    public int getAmount() {
        return amount;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public int getOrderNumber() {
        return orderNumber;
    }
}
