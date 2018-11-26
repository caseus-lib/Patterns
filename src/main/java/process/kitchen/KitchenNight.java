package process.kitchen;

import enums.OrderState;
import environment.kitchen.Cooker;
import environment.products.Product;
import process.model.Order;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Thread.sleep;

public class KitchenNight implements KitchenState {

    private static KitchenNight ourInstance = new KitchenNight();

    public static KitchenNight getInstance() {
        return ourInstance;
    }

    private KitchenNight() {
    }

    private static ExtraditionPlace extraditionPlace = ExtraditionPlace.getInstance();

    @Override
    public List<Product> startCook(Order order) {
        try {
            System.out.println(order.getOrderState());
            sleep(7000);
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
        return IntStream.range(0, order.getAmount())
                        .boxed()
                        .map(o -> Cooker.cookCake()).collect(Collectors.toList());
    }

}
