public class Node {
    int value, height;
    Node left, right;
    Node parent;
    
    // Node는 생성시 높이 1을 default로 가진다.
    public Node(int value) {
        this.value = value;
        height = 1;
        parent = null;
        left = null;
        right = null;
    }
    
    int getBalanceFactor() {
        int leftNodeHeight = left == null ? 0 : left.height;
        int rightNodeHeight = right == null ? 0 : right.height;
        System.out.printf("%s ----> left : %d, right : %d\n", this, leftNodeHeight, rightNodeHeight);
        return leftNodeHeight - rightNodeHeight;
    }
    
    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", height=" + height +
                '}';
    }
}
