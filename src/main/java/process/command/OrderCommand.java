package process.command;

import enums.ProductType;
import process.kitchen.Manager;
import process.model.Order;

public class OrderCommand implements Command {

    private static final Manager manager = Manager.getInstance();
    private ProductType productType;
    private int amount;
    private int orderNumber;

    public OrderCommand(ProductType productType, int amount, int orderNumber) {
        this.productType = productType;
        this.amount = amount;
        this.orderNumber = orderNumber;
    }

    @Override
    public void execute() {
        manager.acceptOrder(buildOrder(productType, amount, orderNumber));
    }

    private Order buildOrder(ProductType productType, int amount, int number) {
        return new Order(productType, amount, number);
    }
}
