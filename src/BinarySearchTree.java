public class BinarySearchTree {

    // Node structure of the BST
    static class Node {
        int data;
        Node left, right;

        // Constructor to create a new node
        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    // Root of the BST
    Node root;

    // Constructor to initialize the BST
    public BinarySearchTree() {
        root = null;
    }

    // Method to insert a new node in the BST
    public void insert(int data) {
        root = insertRec(root, data);
    }

    // A recursive helper function to insert a new node with given data
    private Node insertRec(Node root, int data) {
        // If the tree is empty, return a new node
        if (root == null) {
            root = new Node(data);
            return root;
        }

        // Otherwise, recur down the tree
        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }

        // Return the unchanged node pointer
        return root;
    }

    // Method to perform inorder traversal (left-root-right)
    public void inorder() {
        inorderRec(root);
    }

    // A recursive helper function for inorder traversal
    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.data + " ");
            inorderRec(root.right);
        }
    }

    // Method to search a given key in the BST
    public boolean search(int key) {
        return searchRec(root, key);
    }

    // A recursive helper function to search a key in the BST
    private boolean searchRec(Node root, int key) {
        // Base case: root is null or key is present at the root
        if (root == null || root.data == key)
            return root != null;

        // Key is greater than root's data
        if (key > root.data)
            return searchRec(root.right, key);

        // Key is smaller than root's data
        return searchRec(root.left, key);
    }

    // Method to delete a node in the BST
    public void delete(int key) {
        root = deleteRec(root, key);
    }

    // A recursive helper function to delete a node in the BST
    private Node deleteRec(Node root, int key) {
        // Base case: root is null
        if (root == null)
            return root;

        // Otherwise, recur down the tree
        if (key < root.data) {
            root.left = deleteRec(root.left, key);
        } else if (key > root.data) {
            root.right = deleteRec(root.right, key);
        } else {
            // Node with the key found

            // Case 1: Node has only one child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // Case 2: Node has two children
            root.data = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.data);
        }

        return root;
    }

    // Method to find the minimum value node in the right subtree
    private int minValue(Node root) {
        int minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

    // Main method to test the Binary Search Tree
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Insert elements
        bst.insert(50);
        bst.insert(30);
        bst.insert(20);
        bst.insert(40);
        bst.insert(70);
        bst.insert(60);
        bst.insert(80);

        System.out.println("Inorder traversal of the BST:");
        bst.inorder();  // Output: 20 30 40 50 60 70 80
        System.out.println();

        // Search for a value
        System.out.println("Search 40 in the BST: " + bst.search(40));  // Output: true
        System.out.println("Search 100 in the BST: " + bst.search(100));  // Output: false

        // Delete a node
        System.out.println("Delete 20:");
        bst.delete(20);
        bst.inorder();  // Output: 30 40 50 60 70 80
        System.out.println();

        System.out.println("Delete 30:");
        bst.delete(30);
        bst.inorder();  // Output: 40 50 60 70 80
        System.out.println();
    }
}

