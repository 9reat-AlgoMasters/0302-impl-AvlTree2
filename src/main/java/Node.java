public class Node {
    int value, height;
    Node left, right;

    public Node(int value, int height){
        this(value, null, null, height);
    }

    public Node(int value, Node left, Node right, int height) {
        this.value = value;
        this.right = right;
        this.left = left;
        this.height = height;
    }
}
