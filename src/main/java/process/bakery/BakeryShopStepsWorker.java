package process.bakery;

import app.executor.StepsWorker;
import iterator.ForwardIterator;

import java.util.ArrayList;

public class BakeryShopStepsWorker extends StepsWorker {

    private BakeryShopSteps steps;

    public BakeryShopStepsWorker(BakeryShopSteps steps) {
        this.steps = steps;
        initSteps();
        iterator = new ForwardIterator<>(stepsArray);
        iterator.first();
    }

    @Override
    protected void initSteps() {
        stepsArray = new ArrayList<>();
        stepsArray.add(() -> steps.setDay());
        stepsArray.add(() -> steps.girlHasCame());
        stepsArray.add(() -> steps.personRequestsForCandy());
        stepsArray.add(() -> steps.sellerAcceptOrder());
        stepsArray.add(() -> steps.boyHasCame());
        stepsArray.add(() -> steps.personRequestsForBiscuit());
        stepsArray.add(() -> steps.sellerAcceptOrder());
        stepsArray.add(() -> steps.unicornHasCame());
        stepsArray.add(() -> steps.personRequestsForCake());
        stepsArray.add(() -> steps.sellerAcceptOrder());
        stepsArray.add(() -> steps.witchHasCome());
        stepsArray.add(() -> steps.fairyHasCome());
        stepsArray.add(() -> steps.setNight());
        stepsArray.add(() -> steps.girlHasCame());
        stepsArray.add(() -> steps.personRequestsForBiscuit());
        stepsArray.add(() -> steps.sellerAcceptOrder());
        stepsArray.add(() -> steps.boyHasCame());
        stepsArray.add(() -> steps.aksForBox());
        stepsArray.add(() -> steps.saleBox());
    }
}
