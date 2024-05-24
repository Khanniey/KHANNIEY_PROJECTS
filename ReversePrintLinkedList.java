class ListNode {
    int val;
    ListNode next;

    ListNode(int value) {
        val = value;
        next = null;
    }
}

public class ReversePrintLinkedList {
    public static void printReverseLinkedList(ListNode head) {
        if (head == null)
            return;
        printReverseLinkedList(head.next);
        System.out.println(head.val);
    }

    public static void main(String[] args) {
        // Example usage:
        // Create a linked list: 1 -> 2 -> 3 -> 4 -> null
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        // Print the linked list in reverse order
        printReverseLinkedList(head);
    }
}

