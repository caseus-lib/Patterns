package process.kitchen;

import enums.OrderState;
import environment.kitchen.Cooker;
import environment.products.Product;
import process.model.Order;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Thread.sleep;

public class Kitchen {

    private static ExtraditionPlace extraditionPlace = ExtraditionPlace.getInstance();
    private static Kitchen ourInstance = new Kitchen();

    public static Kitchen getInstance() {
        return ourInstance;
    }

    private Kitchen() {
    }

    public List<Product> startCook(Order order) {
        try {
            System.out.println(order.getOrderState());
            sleep(5000);
            order.setOrderState(OrderState.COOKING);
            System.out.println(order.getOrderState());
            sleep(5000);
            List<Product> products = cookProduct(order);
            System.out.println("order = [" + order + "]");
            extraditionPlace.add(order, products);
            return products;
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    private List<Product> cookProduct(Order order) {
        System.out.println("2");
        order.setOrderState(OrderState.READY);
        System.out.println(order.getAmount());
        switch(order.getProductType()) {
            case CAKE: {
                return IntStream.range(0, order.getAmount())
                                .boxed()
                                .map(o -> Cooker.cookCake()).collect(Collectors.toList());
            }
            case BISCUIT: {
                return IntStream.range(0, order.getAmount())
                                .boxed()
                                .map(o -> Cooker.cookBiscuit()).collect(Collectors.toList());
            }
            case CANDY: {
                return IntStream.range(0, order.getAmount())
                                .boxed()
                                .map(o -> Cooker.cookCandy()).collect(Collectors.toList());
            }
        }
        System.out.println("4");
        return Collections.emptyList();
    }

}
