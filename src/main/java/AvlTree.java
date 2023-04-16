import exceptions.CustomDuplicatedElementException;
import exceptions.CustomNoSuchElementException;

public class AvlTree implements iAvlTree{
    private static final boolean LEFT = true;
    private static final boolean RIGHT = false;
    private Node root;
    private int size;
    
    public AvlTree(int rootValue) {
        root = new Node(rootValue);
        size = 1;
    }
    
    // Test를 위한 생성자
    public AvlTree(Node root) {
        this.root = root;
    }
    
    @Override
    public void insert(int value) {
        if (isEmpty()) {
            root = new Node(value);
        } else {
            insertRecur(root, value);
        }
        size++;
    }
    
    private void insertRecur(Node node, int value) {
        if (node.value == value) {
            throw new CustomDuplicatedElementException(String.format("이미 %d는 트리에 있습니다.", value));
        }
        
        if (value < node.value) {
            if (node.left == null) {
                node.left = new Node(value);
                node.left.parent = node;
                updateHeightRecur(node);
            } else {
                insertRecur(node.left, value);
            }
        } else {
            if (node.right == null) {
                node.right = new Node(value);
                node.right.parent = node;
                updateHeightRecur(node);
            } else {
                insertRecur(node.right, value);
            }
        }
        // height 갱신
        // makeBalanced
    }
    
    
    
    @Override
    public boolean contains(int value) {
        return containsRecur(root, value);
    }

    private boolean containsRecur(Node node, int value) {
        if (node == null) {
            return false;
        }

        if (node.value == value) {
            return true;
        }

        if (value < node.value) {
            return containsRecur(node.left, value);
        } else {
            return containsRecur(node.right, value);
        }
    }
    
    @Override
    public void delete(int value) {
    
    }
    
    @Override
    public boolean isEmpty() {
        return false;
    }
    
    @Override
    public int size() {
        return 0;
    }

    /**
     * 높이 업데이트는 없이 부모 자식의 연결관계만을 수정합니다.
     */
    @Override
    public void rotateLeft(Node node) {
//        System.out.printf("Rotate Left ---> %s\n", node);
        Node nextCenter = node.right;
        
        if (nextCenter.left != null) {
            node.right = nextCenter.left;
            nextCenter.left.parent = node;
        } else {
            node.right = null;
        }
        
        if (node.parent != null) {
            if (node.parent.left == node) {
                node.parent.left = nextCenter;
            } else {
                node.parent.right = nextCenter;
            }
            nextCenter.parent = node.parent;
        } else {
            nextCenter.parent = null;
            root = nextCenter;
        }
        
        node.parent = nextCenter;
        nextCenter.left = node;
    }
    
    @Override
    public void rotateRight(Node node) {
//        System.out.printf("rotate Right -- > %s\n", node);
        Node nextCenter = node.left;
        
        if (nextCenter.right != null) {
            node.left = nextCenter.right;
            nextCenter.right.parent = node;
        } else {
            node.left = null;
        }
        
        if (node.parent != null) {
            if (node.parent.left == node) {
                node.parent.left = nextCenter;
            } else {
                node.parent.right = nextCenter;
            }
            nextCenter.parent = node.parent;
        } else {
            nextCenter.parent = null;
            root = nextCenter;
        }
        
        node.parent = nextCenter;
        nextCenter.right = node;
    }
    
    /**
     * 현재 노드의 height 를 두 자식 중 큰 높이 + 1 로 갱신합니다.
     * 만약 갱신이 된다면 부모 또한 갱신합니다.
     */
    private void updateHeightRecur(Node node) {
//        System.out.printf("<UPDATE> node : %s\n", node);
        if (node == null) {
            return;
        }
        
        node = makeBalanced(node);
        
        int originHeight = node.height;
        updateHeight(node);
//        System.out.printf("<UPDATE> 갱신 후 node : %s\n", node);
        
        if (node == root) {
            return;
        }
        
        boolean isUpdatable = (originHeight != node.height)
                || (node.parent.height != node.parent.findMaxChildHeight() + 1);
        
        if (isUpdatable) {
            updateHeightRecur(node.parent);
        }
    }

    private void updateHeight(Node node) {
        node.height = node.findMaxChildHeight() + 1;
    }

    // node를 루트로 가지는 서브트리를 균형잡힌 서브트리로 만들어 준다.
    // 이때 루트가 node에서 node의 자식중 하나로 루트가 바뀌는데 새로 바뀐 루트를 반환한다.
    private Node makeBalanced(Node node) {
        if (node.isBalanced()) return node;
        
        if (node.findBalanceFactor() == 2) { // LL, LR
            if (node.left.findBalanceFactor() >= 0) { // LL
                rotateRight(node);

                updateHeight(node);
            } else { // LR
                rotateLeft(node.left);
                rotateRight(node);

                updateHeight(node);
                updateHeight(node.parent.left);
            }
        } else if (node.findBalanceFactor() == -2) { // RR, RL
            if (node.right.findBalanceFactor() <= 0) { // RR
                rotateLeft(node);

                updateHeight(node);
            } else { // RL
                rotateRight(node.right);
                rotateLeft(node);

                updateHeight(node);
                updateHeight(node.parent.right);
            }
        }
        return node.parent;
    }

    // 테스트용 메서드
    public int findHeightByValue(int value) {
        if (!contains(value)) {
            throw new CustomNoSuchElementException(String.format("%d값은 트리에 없습니다.", value));
        }
        return findHeightByValueRecur(root, value);
    }

    private int findHeightByValueRecur(Node node, int value) {
        if (node.value == value) {
            return node.height;
        }

        if (value < node.value) {
            return findHeightByValueRecur(node.left, value);
        } else {
            return findHeightByValueRecur(node.right, value);
        }
    }

}
