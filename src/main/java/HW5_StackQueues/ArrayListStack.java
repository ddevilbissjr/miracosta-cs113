package HW5_StackQueues;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class ArrayListStack<E> implements StackInterface<E> {
    private ArrayList<E> stack;

    ArrayListStack() {
        stack = new ArrayList<E>();
    }

    @Override
    public E peek() {
        if (!stack.isEmpty()) {
            return stack.get(0);
        } else {
            throw new EmptyStackException();
        }
    }

    @Override
    public E pop() {
        if (this.stack.size() == 0) {
            throw new EmptyStackException();
        } else {
            E e = stack.get(0);
            stack.remove(0);
            return e;
        }
    }

    @Override
    public E push(E obj) {
        stack.add(0, obj);
        return obj;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof ArrayListStack)) {
            return false;
        }

        ArrayListStack compare = (ArrayListStack) o;
        if (stack.size() != compare.stack.size()) {
            return false;
        }

        boolean isEqual = true;
        for (int i = 0; i < stack.size(); i++) {
            if (!stack.get(i).equals(compare.stack.get(i))) {
                isEqual = false;
            }
        }

        return isEqual;
    }

    public boolean empty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        String toReturn = "[";
        for (int i = stack.size() - 1; i >= 0; i--) {
            toReturn += stack.get(i) + "";
        }
        toReturn += "]";
        return toReturn;
    }
}
