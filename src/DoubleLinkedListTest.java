// Node class representing each element of the doubly linked list
class Node {
    int data;
    Node next;
    Node prev;

    public Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

// Doubly Linked List class
class DoublyLinkedList {
    Node head;

    // Constructor to initialize the doubly linked list
    public DoublyLinkedList() {
        this.head = null;
    }

    // Insert a node at the end of the list
    public void insertEnd(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode; // If list is empty, make the new node the head
            return;
        }

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next; // Traverse to the last node
        }

        temp.next = newNode;  // Link the new node after the last node
        newNode.prev = temp;  // Set the previous pointer of new node
    }

    // Insert a node at the beginning of the list
    public void insertBegin(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode; // If list is empty, make the new node the head
            return;
        }

        newNode.next = head;  // Make new node's next point to current head
        head.prev = newNode;  // Make current head's previous point to new node
        head = newNode;       // Make the new node the new head
    }

    // Delete a node from the list by value
    public void delete(int data) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        Node temp = head;

        // If the node to be deleted is the head
        if (temp != null && temp.data == data) {
            head = temp.next;  // Change head to the next node
            if (head != null) {
                head.prev = null; // Set the previous pointer of the new head to null
            }
            return;
        }

        // Traverse the list to find the node to be deleted
        while (temp != null && temp.data != data) {
            temp = temp.next;
        }

        // If the node is not found
        if (temp == null) {
            System.out.println("Node with data " + data + " not found.");
            return;
        }

        // Node is found, adjust the links
        if (temp.next != null) {
            temp.next.prev = temp.prev;  // Set next node's prev to the current node's prev
        }

        if (temp.prev != null) {
            temp.prev.next = temp.next;  // Set previous node's next to the current node's next
        }

        temp = null;  // Remove the reference to the node (it will be garbage collected)
    }

    // Traverse the list from beginning to end and print nodes
    public void printForward() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // Traverse the list from end to beginning and print nodes
    public void printBackward() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        // Move to the last node
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }

        // Traverse backward
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.prev;
        }
        System.out.println();
    }

    // Search for a node with specific data
    public boolean search(int data) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == data) {
                return true;  // Data found
            }
            temp = temp.next;
        }
        return false;  // Data not found
    }
}

// Main class to test the Doubly Linked List
public class DoublyLinkedListTest {
    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();

        dll.insertEnd(10);
        dll.insertEnd(20);
        dll.insertEnd(30);
        dll.insertBegin(5);

        System.out.println("Forward Traversal:");
        dll.printForward();  // Output: 5 10 20 30

        System.out.println("Backward Traversal:");
        dll.printBackward(); // Output: 30 20 10 5

        System.out.println("Search for 20: " + dll.search(20)); // Output: true
        System.out.println("Search for 40: " + dll.search(40)); // Output: false

        dll.delete(20);  // Deleting node with value 20
        System.out.println("After Deleting 20, Forward Traversal:");
        dll.printForward();  // Output: 5 10 30
    }
}

