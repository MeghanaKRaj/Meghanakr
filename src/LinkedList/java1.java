package LinkedList;

public class java1 {
}class Node {
    int data;
    Node next;

    Node(int data){
        this.data = data;
        this.next = null;
    }

}

class LinkedList {
    Node head;

    LinkedList(){
        this.head = null;
    }

    public void add(int data){
        Node newNode = new Node(data);
        if (head == null){
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null){
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public void display(){
        Node temp = head;
        if (temp == null){
            System.out.println("Nothing here");
        } else {
            while (temp.next != null ){
                System.out.print(temp.data + " -> ");
                temp = temp.next;
            }
            System.out.println(temp.data);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(17);
        list.add(20);
        list.add(25);
        list.display();
    }
}
