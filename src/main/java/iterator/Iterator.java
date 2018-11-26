package iterator;

public interface Iterator<T> {

    void first();
    void next();
    boolean isDone();
    T currentItem();
    void previos();

}
