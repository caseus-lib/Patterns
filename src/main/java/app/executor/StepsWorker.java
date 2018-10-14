package app.executor;

import iterator.ForwardIterator;
import iterator.Iterator;

import java.util.ArrayList;
import java.util.List;

public class StepsWorker {

    private List<Runnable> stepsArray;
    private Iterator<Runnable> iterator;
    private Steps steps;

    public StepsWorker(Steps steps) {
        this.steps = steps;
        initSteps();
        iterator = new ForwardIterator<>(stepsArray);
        iterator.first();
    }

    private void initSteps() {
        stepsArray = new ArrayList<>();
        stepsArray.add(() -> steps.watchShowCase());
        stepsArray.add(() -> steps.girlHasCame());
        stepsArray.add(() -> steps.personRequestsForMinPower());
        stepsArray.add(() -> steps.sellerGivesProduct());
        stepsArray.add(() -> steps.personGetsProduct());
        stepsArray.add(() -> steps.boyHasCame());
        stepsArray.add(() -> steps.personRequestsForBox());
        stepsArray.add(() -> steps.sellerGivesProduct());
        stepsArray.add(() -> steps.personGetsProduct());
        stepsArray.add(() -> steps.unicornHasCame());
        stepsArray.add(() -> steps.unicornRequestsForRainbow());
        stepsArray.add(() -> steps.sellerGivesProduct());
        stepsArray.add(() -> steps.adapterWorks());
        stepsArray.add(() -> steps.unicornGetsProduct());
        stepsArray.add(() -> steps.girlHasCame());
        stepsArray.add(() -> steps.personRequestsForMinPowerWithSpice());
        stepsArray.add(() -> steps.sellerGivesProductWithSpice());
        stepsArray.add(() -> steps.personGetsProduct());
    }

    public void nextStep() {
        if (!iterator.isDone()) {
            iterator.currentItem().run();
            iterator.next();
        }
    }
}
