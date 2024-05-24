class ListNode {
    int val;
    ListNode next;

    ListNode(int value) {
        val = value;
        next = null;
    }
}

public class LinkedListLength2 {
    public static int lengthOfLinkedList(ListNode head) {
        if (head == null) {
            return 0; // Base case: empty list has length 0
        } else {
            // Recursive case: length of list is 1 (for current node) + length of rest of
            // the list
            return 1 + lengthOfLinkedList(head.next);
        }
    }

    public static void main(String[] args) {
        // Example usage:
        // Create a linked list: 1 -> 2 -> 3 -> 4 -> null
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        // Find the length of the linked list
        int length = lengthOfLinkedList(head);
        System.out.println("Length of the linked list: " + length);
    }
}
