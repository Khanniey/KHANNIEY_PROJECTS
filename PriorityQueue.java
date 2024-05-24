public class PriorityQueue {
    private int currentsize;
    private Node root;

    Node[] array;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public int getCurrentsize() {
        return currentsize;
    }

    public void setCurrentsize(int currentsize) {
        this.currentsize = currentsize;
    }

    public PriorityQueue() {
        currentsize = 0;
        root = null;
        array = new Node[25];
    }

    public PriorityQueue(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Max size should be a positive integer.");
        }
        this.currentsize = 0;
        this.root = null;
        this.array = new Node[maxSize];
    }

    void resetArrayElements() {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }

    void clear() {
        currentsize = 0;
        root = null;
        resetArrayElements();
    }

    boolean isEmpty() {
        return currentsize == 0;
    }

    boolean isfull() {
        return currentsize == array.length;
    }

    public boolean add(Node x) {
        if (isfull()) {
            return false;
        }
        array[currentsize] = x;
        currentsize++;
        heapifyUp(currentsize - 1);
        return true;
    }

    private int parentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private int leftChildIndex(int parentIndex) {
        return parentIndex * 2 + 1;
    }

    private int rightChildIndex(int parentIndex) {
        return parentIndex * 2 + 2;
    }

    private void heapifyUp(int index) {
        parentIndex(index);
        Node bottom = array[index];

        while (index > 0 && array[parentIndex(index)].getArrival_time().compareTo(bottom.getArrival_time()) > 0) {
            array[index] = array[parentIndex(index)];
            index = parentIndex(index);
            int parentIndex = (parentIndex(index) - 1) / 2;
        }
        array[index] = bottom;
    }

    private void trickleUp(int index) {
        heapifyUp(index);
    }

    public Node remove() {
        if (isEmpty()) {
            return null;
        }

        Node removedNode = array[0];
        array[0] = array[currentsize - 1];
        currentsize--;
        percolateDown(0);
        return removedNode;
    }

    private void percolateDown(int hole) {
        int child;
        Node temp = array[hole];

        while (leftChildIndex(hole) < currentsize) {
            int leftChild = leftChildIndex(hole), rightChild = rightChildIndex(hole);

            if (rightChild < currentsize
                    && array[rightChild].getArrival_time().compareTo(array[leftChild].getArrival_time()) < 0) {
                child = rightChild;
            } else {
                child = leftChild;
            }

            if (array[child].getArrival_time().compareTo(temp.getArrival_time()) < 0) {
                array[hole] = array[child];
            } else {
                break;
            }
            hole = child;
        }
        array[hole] = temp;
    }

}