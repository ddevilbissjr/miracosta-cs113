package HW4_DoubleLinkedList;

import java.util.*;

public class DoubleLinkedList<E> implements List<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DoubleLinkedList() {
        tail = null;
        head = null;
        size = 0;
    }

    @Override
    public DoubleListIterator listIterator() {
        return new DoubleListIterator();
    }

    @Override
    public DoubleListIterator iterator() {
        return new DoubleListIterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    public DoubleListIterator listIterator(int index) {
        return new DoubleListIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean add(E e) {
        listIterator().add(e);
        return true;
    }

    @Override
    public void add(int index, E obj) {
        listIterator(index).add(obj);
    }

    @Override
    public E get(int index) {
        DoubleListIterator list = new DoubleListIterator(index);
        if (!(list.hasNext())) {
            throw new IndexOutOfBoundsException();
        }
        return list.next();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    @Override
    public void clear() {
        head = null;
        tail = head;
        size = 0;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        } else {
            DoubleListIterator list = new DoubleListIterator(index);
            if (!(list.hasNext())) {
                throw new IllegalStateException();
            }

            E temp = list.next();
            list.remove();
            return temp;
        }
    }

    @Override
    public boolean remove(Object o) {
        int newSize = size;
        int current = indexOf(o);
        if (current < 0 || current >= size()) {
            return false;
        }

        DoubleListIterator list = new DoubleListIterator(current);
        if (list.hasNext()) {
            list.next();
        } else {
            throw new IndexOutOfBoundsException();
        }

        list.remove();
        if (newSize == (size() - 1)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
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
    public boolean contains(Object o) {
        if (indexOf(o) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public E set(int index, E e) {
        if (index > size() - 1) {
            throw new IndexOutOfBoundsException();
        }
        DoubleListIterator list = new DoubleListIterator(index);
        Node<E> oldInfo = new Node<E>();

        list.next();

        oldInfo.data = list.last.data;
        list.set(e);

        return oldInfo.data;
    }

    @Override
    public int indexOf(Object o) {
        int count = 0;
        if (o == null) {
            return -1;
        }

        Node position = head;
        while (position != null) {
            if (o.equals(position.data)) {
                return count;
            }

            count++;
            position = position.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int count = 0;
        if (o == null) {
            return -1;
        }

        Node position = tail;
        while (position != null) {
            if (o.equals(position.data)) {
                return (count - size - 1);
            }
            count++;
            position = position.previous;
        }
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof LinkedList)) {
            return false;
        }

        List anotherList = (LinkedList) o;
        if (size() != anotherList.size()) {
            return false;
        }
        Node<E> position = head;
        while (position != null) {
            if (!(anotherList.contains(position.data))) {
                return false;
            }
            position = position.next;
        }
        return true;
    }

    private static class Node<E> {
        int j = 0;
        private Node<E> previous;
        private Node<E> next;
        private E data;

        private Node(E dataItem) {
            data = dataItem;
            next = null;
            previous = null;
        }

        private Node() {
            data = null;
            next = null;
            previous = null;
        }

        private Node(E dataItem, Node<E> nextRef, Node<E> previousRef) {
            data = dataItem;
            next = nextRef;
            previous = previousRef;
        }
    }

    private class DoubleListIterator implements ListIterator<E> {
        private Node<E> next;
        private Node<E> last;
        private int index;

        public DoubleListIterator(int i) {
            if (i < 0 || i > size()) {
                throw new IndexOutOfBoundsException();
            }

            last = null;

            if (i == size()) {
                index = size;
                next = null;
            } else {
                next = head;
                for (index = 0; index < i; index++) {
                    next = next.next;
                }
            }
        }

        public DoubleListIterator() {
            last = null;
            next = head;
            for (index = 0; index < size(); index++) {
                next = next.next;
            }
        }

        @Override
        public void add(E e) {
            if (head == null) {
                head = new Node<E>(e);
                tail = head;
            } else if (next == head) {
                Node<E> newNode = new Node<E>(e);
                newNode.next = next;
                next.previous = newNode;
                head = newNode;
            } else if (next == null) {
                Node<E> newNode = new Node<E>(e);
                tail.next = newNode;
                newNode.previous = tail;
                tail = newNode;
            } else {
                Node<E> newNode = new Node<E>(e);
                newNode.previous = next.previous;
                next.previous.next = newNode;
                newNode.next = next;
                next.previous = newNode;
            }
            size++;
            index++;
            last = null;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            last = next;
            next = next.next;
            index++;
            return last.data;
        }

        @Override
        public boolean hasPrevious() {
            if (head == null) {
                return false;
            } else if (next == null) {
                return true;
            } else if (next.previous == null) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            if (next == null) {
                next = tail;
            } else {
                next = next.previous;
            }
            last = next;
            index--;
            System.out.println("in previous. last item return is " + last.data.toString());
            return last.data;
        }

        @Override
        public int previousIndex() {
            return (index - 1);
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public void remove() {
            if (head == null) {
                throw new IllegalStateException();
            } else if (last == null) {
                throw new IllegalStateException();
            } else if (last.previous == null && next == null) {
                head = null;
                tail = null;
                size = 0;
                index = 0;
            } else if (last.previous == null) {
                next.previous = head;
                head = next;
                size--;
                index--;
            } else if (next == null) { // tail
                last.previous.next = null;
                tail = last.previous;
                index--;
                size--;
            } else {
                last.previous.next = next;
                next.previous = last.previous;
                size--;
                index--;
                System.out.println("In the remove");
            }
        }

        @Override
        public void set(E e) {
            if (last == null) {
                throw new IllegalStateException();
            }
            Node<E> newNode = new Node<E>(e);
            last.data = newNode.data;
        }

        @Override
        public String toString() {
            String str = "[";
            Node position = head;

            if (head == null) {
                str += "";
            } else {
                while (position != null) {

                    str += position.data;
                    position = position.next;
                    str += ", ";
                }
                str = str.substring(0, str.length() - 2);

            }
            str += "]";
            return str;
        }
    }
}