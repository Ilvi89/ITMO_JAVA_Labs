package memento;

public interface Snapshot<T> {
    T getState();
}