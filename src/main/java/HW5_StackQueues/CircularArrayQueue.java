package HW5_StackQueues;

import java.util.*;

public class CircularArrayQueue<E> implements Queue<E> {

    private Queue<E> arrQ = new LinkedList<E>();

    public CircularArrayQueue(int initialCapacity) {
        arrQ = (Queue) Arrays.asList(new Object[initialCapacity]);
    }

    @Override
    public int size() {
        return arrQ.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return arrQ.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return arrQ.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return arrQ.toArray(ts);
    }

    @Override
    public boolean add(E e) {
        return arrQ.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return arrQ.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return arrQ.containsAll(collection);
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        return arrQ.addAll(collection);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return arrQ.removeAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return arrQ.retainAll(collection);
    }

    @Override
    public void clear() {
        arrQ.clear();
    }

    @Override
    public boolean offer(E e) {
        return arrQ.offer(e);
    }

    @Override
    public E remove() {
        return arrQ.remove();
    }

    @Override
    public E poll() {
        return arrQ.poll();
    }

    @Override
    public E element() {
        return arrQ.element();
    }

    @Override
    public E peek() {
        return arrQ.peek();
    }
}
