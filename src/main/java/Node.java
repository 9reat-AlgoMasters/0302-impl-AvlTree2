public class Node {
    int value, height;
    Node left, right;

    public Node(int value){
        this(value, null, null);
    }

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.right = right;
        this.left = left;
        this.height = 1;
    }
}
