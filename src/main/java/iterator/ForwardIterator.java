package iterator;

import java.util.List;

public class ForwardIterator<T> implements Iterator<T> {

    private List<T> list;
    private int index;

    public ForwardIterator(List<T> list) {
        this.list = list;
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
