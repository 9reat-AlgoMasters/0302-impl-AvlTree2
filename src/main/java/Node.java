public class Node {
    private static final boolean LEFT = true;
    private static final boolean RIGHT = false;
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
    
    public int getChildHeight(boolean sideOfChild) {
        if (sideOfChild == LEFT) {
            return left==null ? 0 : left.height;
        } else {
            return right==null ? 0 : right.height;
        }
    }
    
    public int findMaxChildHeight() {
        return Math.max(getChildHeight(LEFT), getChildHeight(RIGHT));
    }
    
    
    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", height=" + height +
                '}';
    }
}
