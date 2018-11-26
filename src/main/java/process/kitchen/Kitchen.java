package process.kitchen;

import enums.TimesOfDay;
import environment.products.Product;
import process.model.Order;

import java.util.List;

public class Kitchen {

    private static Kitchen ourInstance = new Kitchen();

    public static Kitchen getInstance() {
        return ourInstance;
    }

    private KitchenState kitchenState;

    private Kitchen() {
    }

    public List<Product> startCook(Order order) {
        return kitchenState.startCook(order);
    }

    public void setState(TimesOfDay timesOfDay) {
        switch(timesOfDay) {
            case DAY: kitchenState = KitchenDay.getInstance(); break;
            case NIGHT: kitchenState = KitchenNight.getInstance(); break;
        }
    }

}
