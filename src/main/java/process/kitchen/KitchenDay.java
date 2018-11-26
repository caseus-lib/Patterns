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

public class KitchenDay implements KitchenState {

    private static KitchenDay ourInstance = new KitchenDay();

    public static KitchenDay getInstance() {
        return ourInstance;
    }

    private KitchenDay() {
    }

    private static ExtraditionPlace extraditionPlace = ExtraditionPlace.getInstance();

    @Override
    public List<Product> startCook(Order order) {
        try {
            System.out.println(order.getOrderState());
            sleep(3000);
            order.setOrderState(OrderState.COOKING);
            System.out.println(order.getOrderState());
            sleep(3000);
            List<Product> products = cookProduct(order);
            extraditionPlace.add(order, products);
            return products;
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    private List<Product> cookProduct(Order order) {
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
        return Collections.emptyList();
    }

}
