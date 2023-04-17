public class Node {
    int value, height;
    Node leftChild;
    Node rightChild;
    Node parent;
    public Node(int value) {
        this.value = value;
        this.height = 1;
    }

    public Node(int value, Node leftChild, Node rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
}
