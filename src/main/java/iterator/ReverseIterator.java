package iterator;

import java.util.List;

public class ReverseIterator<T> implements Iterator<T> {

    private List<T> list;
    private int index;

    public ReverseIterator(List<T> list) {
        this.list = list;
    }

    @Override
    public void first() {
        index = list.size() - 1;
    }

    @Override
    public void next() {
        index--;
    }

    @Override
    public boolean isDone() {
        return index < 0;
    }

    @Override
    public T currentItem() {
        return list.get(index);
    }

    @Override
    public void previos() {
        index--;
    }
}
