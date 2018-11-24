package process.kitchen;

import enums.State;
import environment.products.Product;
import process.model.Order;

import java.util.concurrent.CompletableFuture;

public class Manager {

    private static Kitchen kitchen = new Kitchen();

    public static void main(String[] args) {
        acceptOrder(new Order());
    }

    public static void acceptOrder(Order order) {
        order.setState(State.WAITING);
        System.out.println(order.getState());
        CompletableFuture.runAsync(() -> kitchen.startCook(order));
        int a;
        while(true) {
            for(int i = 0; i < 100000000; i++){a = i;}
            System.out.print("1");
        }

    }

    public void acceptProduct(Product product) {

    }


}
