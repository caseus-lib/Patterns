package iterator;

import environment.magical.powers.BooleanAction;

import java.util.List;

public class TravelIterator<T> implements Iterator<T> {

    private List<T> list;
    private int index;

    public TravelIterator(List<T> list) {
        this.list = list;
    }

    public boolean travel(BooleanAction<T> action) {
        for (first(); !isDone(); next()) {
            if (action.action(currentItem()))
                return true;
        }
        return false;
    }

    @Override
    public void first() {
        index = 0;
    }

    @Override
    public void next() {
        index++;
    }

    @Override
    public boolean isDone() {
        return index >= list.size();
    }

    @Override
    public T currentItem() {
        return list.get(index);
    }
}
