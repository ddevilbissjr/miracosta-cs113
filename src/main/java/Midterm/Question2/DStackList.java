package Midterm.Question2;

import java.util.Stack;

public class DStackList {

    public static void main (String[] args) {
        Stack<ListNode> list = new Stack<ListNode>();
        int[] data = new int[] {4, 2, 8, 3, 6, 5};

        for (int i = 0; i < data.length; i++) {
            ListNode node = new ListNode(data[i]);
            if (!list.isEmpty()) {
                list.get(i - 1).setNext(node);
            }
            list.push(node);
        }

        printStack(list);
        moveSmallestToTop(list);
    }

    public static void moveSmallestToTop(Stack<ListNode> stack) {
        if (stack.empty()) {
            return;
        }

        ListNode min = stack.peek();
        Stack<ListNode> temp = new Stack<>();

        while (!stack.isEmpty()) {
            ListNode head = stack.peek();

            if (head.getVal() < min.getVal()) {
                min = head;
            }

            temp.push(stack.pop());
        }

        while (!temp.isEmpty()) {
            ListNode head = temp.peek();

            if (head.getVal() != min.getVal()) {
                if(!stack.isEmpty()) {
                    stack.peek().setNext(head);
                }
                stack.push(head);
            }

            temp.pop();
        }

        stack.peek().setNext(min);
        stack.push(min);
        min.setNext(null);

        printStack(stack);
    }

    public static void printStack(Stack<ListNode> stack) {
        for (ListNode node : stack) {
            System.out.print(node);
            if(!node.equals(stack.peek())) {
                System.out.print(", ");
            } else {
                System.out.println();
            }
        }
    }
}
