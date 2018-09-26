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
        TravelIterator<MagicalPower> iteratorTo = createTravelIterator();
        Iterator<MagicalPower> iteratorFrom = magicalPowerList.createIterator();
        for (iteratorFrom.first(); !iteratorFrom.isDone(); iteratorFrom.next()) {
            if (iteratorTo.travel(object -> object.merge(iteratorFrom.currentItem()))) {
                continue;
            }
            add(iteratorFrom.currentItem().copy());
        }
    }

    public Stream<MagicalPower> getStream() {
        return magicalPowers.stream();
    }

    public MagicalPowerList add(MagicalPower magicalPower) {
        TravelIterator<MagicalPower> travelIterator = createTravelIterator();
        for (travelIterator.first(); !travelIterator.isDone(); travelIterator.next()) {
            if (travelIterator.travel(object -> object.merge(magicalPower)))
                return this;
        }
        magicalPowers.add(magicalPower.copy());
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
                .map(MagicalPower::toString)
                .collect(Collectors.joining("\n"));
    }
}
