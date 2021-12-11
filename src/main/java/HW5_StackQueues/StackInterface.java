package HW5_StackQueues;

public interface StackInterface<E> {
    boolean empty();
    E peek();
    E pop();
    E push(E obj);
}
