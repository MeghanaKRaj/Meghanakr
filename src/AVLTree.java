// AVL Tree implementation in Java

class AVLTree {
    // A class for a tree node
    class Node {
        int key;
        Node left, right;
        int height;

        Node(int key) {
            this.key = key;
            this.height = 1;
        }
    }

    private Node root;

    // A utility function to get the height of the tree
    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    // A utility function to get the balance factor of a node
    private int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    // Right rotation
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // Left rotation
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Insert a key into the tree and balance the tree
    public void insert(int key) {
        root = insertRec(root, key);
    }

    // Recursive function to insert a key in the subtree rooted at node
    private Node insertRec(Node node, int key) {
        // 1. Perform the normal BST insertion
        if (node == null) {
            return new Node(key);
        }

        if (key < node.key) {
            node.left = insertRec(node.left, key);
        } else if (key > node.key) {
            node.right = insertRec(node.right, key);
        } else {
            return node; // Duplicates not allowed
        }

        // 2. Update height of this ancestor node
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        // 3. Get the balance factor and balance the tree
        int balance = getBalance(node);

        // Left Left case
        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }

        // Right Right case
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }

        // Left Right case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // Return the (unchanged) node pointer
        return node;
    }

    // Function to print the tree (inorder traversal)
    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    // Function to delete a node from the tree
    public void delete(int key) {
        root = deleteRec(root, key);
    }

    // Recursive function to delete a node
    private Node deleteRec(Node root, int key) {
        // STEP 1: PERFORM STANDARD BST DELETE
        if (root == null) {
            return root;
        }

        if (key < root.key) {
            root.left = deleteRec(root.left, key);
        } else if (key > root.key) {
            root.right = deleteRec(root.right, key);
        } else {
            // Node with only one child or no child
            if ((root.left == null) || (root.right == null)) {
                Node temp = root.left != null ? root.left : root.right;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else {
                    root = temp;
                }
            } else {
                // Node with two children: Get the inorder successor (smallest in the right subtree)
                Node temp = minValueNode(root.right);

                // Copy the inorder successor's content to this node
                root.key = temp.key;

                // Delete the inorder successor
                root.right = deleteRec(root.right, temp.key);
            }
        }

        // If the tree has only one node, return it
        if (root == null) {
            return root;
        }

        // STEP 2: UPDATE HEIGHT OF THIS ANCESTOR NODE
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether this node became unbalanced)
        int balance = getBalance(root);

        // Left Left case
        if (balance > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }

        // Right Right case
        if (balance < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }

        // Left Right case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Left case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // Utility function to find the node with the minimum key value
    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Main method to test the AVL Tree implementation
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // Insert elements
        tree.insert(20);
        tree.insert(4);
        tree.insert(26);
        tree.insert(3);
        tree.insert(9);
        tree.insert(15);
        tree.insert(30);

        System.out.println("Inorder traversal of the AVL tree:");
        tree.inorder();

        // Deleting nodes
        System.out.println("\nDeleting node 26:");
        tree.delete(26);
        tree.inorder();

        System.out.println("\nDeleting node 4:");
        tree.delete(4);
        tree.inorder();
    }
}
