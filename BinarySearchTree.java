public class BinarySearchTree {
    private Node2 root;

    public BinarySearchTree() {
        this.root = null;
    }

    public BinarySearchTree(Node2 root) { // LOADED CONSTRUCTOR
        this.root = root;
    }

    public Node2 getRoot() {
        return root;
    }

    public void setRoot(Node2 root) {
        this.root = root;
    }

    private boolean isMatch(Node2 root, int key) { // CHECK IF NODE IS NULL OR IS THERE
        return root == null || root.getID() == key;
    }

    private boolean isLessThanKey(int key, Node2 root) {
        return key < root.getID();
    }

    public Node2 search(int key, Node2 root) {
        if (root == null || root.getID() == key) {
            return root;
        }

        if (key < root.getID()) {
            return search(key, root.getLeft());
        } else {
            return search(key, root.getRight());
        }
    }

    private boolean isNodeNull(Node2 node) {
        return node == null;
    }

    private Node2 insertLeft(int key, Node2 root) {
        root.setLeft(insert(key, root.getLeft()));
        return root;
    }

    public Node2 insert(int key, Node2 root) {
        if (isNodeNull(root)) {
            return new Node2(key, "", null, null);
        }

        if (key < root.getID()) {
            root.setLeft(insert(key, root.getLeft()));
        } else if (key > root.getID()) {
            root.setRight(insert(key, root.getRight()));
        }

        return root;
    }

    private boolean isGreaterThanKey(int key, Node2 root) {
        return key > root.getID();
    }

    private boolean hasNoLeftChild(Node2 root) {
        return root.getLeft() == null;
    }

    private boolean hasNoRightChild(Node2 root) {
        return root.getRight() == null;
    }

    public Node2 delete(int key, Node2 root) { // DELETE METHOD
        if (isNodeNull(root)) {
            return root;
        }

        if (key < root.getID()) {
            root.setLeft(delete(key, root.getLeft()));
        } else if (key > root.getID()) {
            root.setRight(delete(key, root.getRight()));
        } else {

            if (root.getLeft() == null) {
                return root.getRight();
            } else if (root.getRight() == null) {
                return root.getLeft();
            }

            root.setID(minValue(root.getRight()));

            root.setRight(delete(root.getID(), root.getRight()));
        }
        return root;
    }

    private int findMinValue(Node2 root) {
        while (root.getLeft() != null) {
            root = root.getLeft();
        }
        return root.getID();
    }

    private int minValue(Node2 root) { // I AM FINDING THE LOWEST VALUE IN THE SUBTREE
        if (root == null) {
            return Integer.MAX_VALUE;
        }

        int minValue = root.getID();
        while (root.getLeft() != null) {
            minValue = root.getLeft().getID();
            root = root.getLeft();
        }
        return minValue;
    }

    private Node2[] handleNodeNotFound(Node2 root) {
        Node2[] result = new Node2[2];
        result[0] = root;
        result[1] = null;
        return result;
    }

    private void splitLeftSubtree(Node2 node, Node2 parent, Node2[] result) {
        if (node.getLeft() != null) {
            result[0] = node.getLeft();
            node.setLeft(null);
        } else {
            if (parent == null) {
                result[0] = null;
            } else {
                parent.setRight(null);
            }
        }
    }

    public Node2[] split(int key, Node2 root) { // THIS IS WHERE MY SPLITTING OF THE BINARY TREE STARTS
        Node2[] result = new Node2[2];
        Node2[] nodes = findNodeAndParent(key, null, root);
        Node2 node = nodes[0];
        Node2 parent = nodes[1];

        if (node == null) {
            return handleNodeNotFound(root);
        }

        result = new Node2[2];

        splitLeftSubtree(node, parent, result); // I AM NOW SPLITTING THE LEFT SUBTREE

        result[1] = node.getRight();
        node.setRight(null);
        return result;
    }

    private Node2[] handleNodeFoundOrNull(Node2 root, Node2 parent) {
        Node2[] result = new Node2[2];
        result[0] = root;
        result[1] = parent;
        return result;
    }

    private Node2[] findNodeAndParentRecursive(int key, Node2 parent, Node2 root) {
        if (key < root.getID()) {
            return findNodeAndParent(key, root, root.getLeft());
        } else {
            return findNodeAndParent(key, root, root.getRight());
        }
    }

    private Node2[] findNodeAndParent(int key, Node2 parent, Node2 root) {
        if (root == null || root.getID() == key) {
            return handleNodeFoundOrNull(root, parent);
        }

        return findNodeAndParentRecursive(key, parent, root);
    }

    public static void main(String[] args) {

        BinarySearchTree bst = new BinarySearchTree();

        bst.setRoot(bst.insert(41, bst.getRoot()));
        bst.setRoot(bst.insert(11, bst.getRoot()));
        bst.setRoot(bst.insert(61, bst.getRoot()));
        bst.setRoot(bst.insert(30, bst.getRoot()));
        bst.setRoot(bst.insert(5, bst.getRoot()));
        bst.setRoot(bst.insert(55, bst.getRoot()));
        bst.setRoot(bst.insert(70, bst.getRoot()));
        bst.setRoot(bst.insert(10, bst.getRoot()));
        bst.setRoot(bst.insert(43, bst.getRoot()));

        System.out.println("HERE IS THE BINARY SEARCH TREE BELOW:"); // SHOWING BST
        displayInOrder(bst.getRoot());
        System.out.println();

        int keyToSearch = 30;
        Node2 foundNode = bst.search(keyToSearch, bst.getRoot());
        if (foundNode != null) {
            System.out.println("We have found the node with key " + keyToSearch + foundNode.getName());
        } else {
            System.out.println("The node with key  " + keyToSearch + " is not on the Binary Tree.");
        }

        int keyToDelete = 30;
        bst.setRoot(bst.delete(keyToDelete, bst.getRoot()));

        System.out.println("\nHere is the BINARY CEARCH TREE after we have deleted key  " + keyToDelete + ":");
        displayInOrder(bst.getRoot());
        System.out.println();

        bst.setRoot(new Node2(41, "notes", null, null));
        bst.getRoot().setLeft(new Node2(11, "personal", null, null));
        bst.getRoot().getLeft().setRight(new Node2(30, "shopping", null, null));
        bst.getRoot().getLeft().getRight().setLeft(new Node2(5, "recipes", null, null));
        bst.getRoot().setRight(new Node2(61, "work", null, null));
        bst.getRoot().getRight().setLeft(new Node2(55, "proposal", null, null));
        bst.getRoot().getRight().getLeft().setLeft(new Node2(43, "draft", null, null));
        bst.getRoot().getRight().getLeft().setRight(new Node2(70, "thesis", null, null));
        bst.getRoot().getLeft().getRight().getLeft().setLeft(new Node2(10, "muffins", null, null));

        Node2[] splitResult = bst.split(30, bst.getRoot()); // SPLITTING TREE AT 30

        System.out.println(
                "Root of the left subtree after split: " + (splitResult[0] != null ? splitResult[0].getID() : "null"));
        System.out.println(
                "Root of the right subtree after split: " + (splitResult[1] != null ? splitResult[1].getID() : "null"));
    }

    public static void displayInOrder(Node2 root) {
        if (root != null) {
            displayInOrder(root.getLeft());
            System.out.print(root.getID() + "(" + root.getName() + ") ");
            displayInOrder(root.getRight());
        }
    }

}