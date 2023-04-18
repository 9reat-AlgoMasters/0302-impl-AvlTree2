public class Node {
    int value, height;
    Node left;
    Node right;
    Node parent;
    public Node(int value) {
        this.value = value;
        this.height = 1;
    }

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
