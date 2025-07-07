package java_Practice.spring_test_demo.practice;

public class MyLinkedList {
    private class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            next = null;
        }
    }

    private ListNode head;
    private ListNode tail;

    public MyLinkedList() {
        head = null;
        tail = null;
    }

    // Add a node to the beginning of the list
    public void addToFront(int val) {
        ListNode newNode = new ListNode(val);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    // Add a node to the end of the list
    public void addToEnd(int val) {
        ListNode newNode = new ListNode(val);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    // Remove a node from the beginning of the list
    public void removeFromFront() {
        if (!isEmpty()) {
            head = head.next;
        }
    }

    // Remove a node from the end of the list
    public void removeFromEnd() {
        if (!isEmpty()) {
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                ListNode current = head;
                while (current.next != tail) {
                    current = current.next;
                }
                current.next = null;
                tail = current;
            }
        }
    }

    // Check if the list is empty
    public boolean isEmpty() {
        return head == null;
    }

    // Print the elements in the list in a formatted way
    public void printList() {
        if (isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }

        ListNode current = head;
        System.out.print("List: ");
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();

        list.addToFront(2);
        list.addToFront(1);
        list.addToEnd(3);
        list.addToEnd(4);

        list.printList(); // Output: List: 1 -> 2 -> 3 -> 4

        list.removeFromFront();
        list.removeFromEnd();

        list.printList(); // Output: List: 2 -> 3
    }
}