package HW5_StackQueues;

import java.util.*;

public class CircularArrayQueue<E> implements Queue<E> {
    private int initialCapacity;
    private int front;
    private int rear;
    private int size;
    private ArrayList<E> queue;

    public CircularArrayQueue(int init) {
        initialCapacity = init;
        front = 0;
        rear = init - 1;
        queue = new ArrayList<E>(init);
        size = 0;
    }

    @Override
    public boolean add(E e) {
        if (initialCapacity == size) {
            reallocate();
        }

        size++;
        rear = (rear + 1) % initialCapacity;
        queue.add(rear, e);
        return true;
    }

    @Override
    public E element() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return queue.get(front);
    }

    @Override
    public boolean offer(E e) {
        if (initialCapacity == size) {
            reallocate();
        }
        size++;
        rear = (rear + 1) % initialCapacity;
        queue.add(rear, e);
        return true;
    }

    @Override
    public E peek() {
        if (size == 0) {
            return null;
        }
        return queue.get(front);
    }

    @Override
    public E poll() {
        if (size == 0) {
            return null;
        }
        E item = queue.get(front);
        front = (front + 1) % initialCapacity;
        size--;
        return item;
    }

    @Override
    public E remove() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        E item = queue.get(front);
        front = (front + 1) % initialCapacity;
        size--;
        return item;
    }

    private void reallocate() {
        int newCapacity = initialCapacity * 2;
        ArrayList<E> arr = new ArrayList<>(newCapacity);
        int f = front;
        for (int i = 0; i < size; i++) {
            arr.add(i, queue.get(f));
            f = (f + 1) % initialCapacity;
        }
        front = 0;
        rear = size - 1;
        initialCapacity = newCapacity;
        queue = arr;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public void clear() {
        size = initialCapacity;
        front = 0;
        rear = 0;
        queue = new ArrayList<>(size);
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

}
