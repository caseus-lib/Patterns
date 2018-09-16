package magical.powers;

import iterator.ForwardIterator;
import iterator.Iterator;
import iterator.ReverseIterator;
import iterator.TravelIterator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MagicalPowerList {

    private List<MagicalPower> magicalPowers = new ArrayList<>();
    private Iterator<MagicalPower> iterator;

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

    public void add(MagicalPower magicalPower) {
        for (MagicalPower power : magicalPowers) {
            if (power.merge(magicalPower)) {
                return;
            }
        }
        magicalPowers.add(magicalPower);
    }

    public Iterator<MagicalPower> createIterator() {
        return iterator = new ForwardIterator<>(magicalPowers);
    }

    public TravelIterator<MagicalPower> createTravelIterator() {
        return (TravelIterator<MagicalPower>) (iterator = new TravelIterator<MagicalPower>(magicalPowers));
    }
}
