package magical.powers;

import iterator.ForwardIterator;
import iterator.Iterator;
import iterator.TravelIterator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MagicalPowerList {

    private List<MagicalPower> magicalPowers;

    public MagicalPowerList() {
        magicalPowers = new ArrayList<>();
    }

    public MagicalPowerList(List<MagicalPower> powersList) {
        magicalPowers = powersList;
    }

    public MagicalPowerList(MagicalPowerList magicalPowerList) {
        this();
        this.magicalPowers = new ArrayList<>(magicalPowerList.magicalPowers);
    }

    public void merge(MagicalPowerList magicalPowerList) {
        TravelIterator<MagicalPower> iterator = createTravelIterator();
        Iterator<MagicalPower> iteratorFrom = magicalPowerList.createIterator();
        for (iteratorFrom.first(); !iteratorFrom.isDone(); iteratorFrom.next()) {
            if (iterator.travel(object -> iteratorFrom.currentItem().merge(object))) {
                continue;
            }
            add(iteratorFrom.currentItem());
        }
    }

    public Stream<MagicalPower> getStream() {
        return magicalPowers.stream();
    }

    public MagicalPowerList add(MagicalPower magicalPower) {
        for (MagicalPower power : magicalPowers) {
            if (power.merge(magicalPower)) {
                return this;
            }
        }
        magicalPowers.add(magicalPower);
        return this;
    }

    public Iterator<MagicalPower> createIterator() {
        return new ForwardIterator<>(magicalPowers);
    }

    public TravelIterator<MagicalPower> createTravelIterator() {
        return new TravelIterator<>(magicalPowers);
    }

    @Override
    public String toString() {
        return magicalPowers.stream()
                .map(MagicalPower::getName)
                .collect(Collectors.joining("\n"));
    }
}
