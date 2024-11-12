public class LinkedList {
    Node head;

    LinkedList(){
        head = null;
    }

    public void add(int data){
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;

    }
}
