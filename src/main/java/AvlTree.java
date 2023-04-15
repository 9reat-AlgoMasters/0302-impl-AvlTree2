public class AvlTree implements iAvlTree{
    
    @Override
    public void insert(int value) {
    
    }
    
    @Override
    public boolean contains(int value) {
        return false;
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
    
    @Override
    public void rotateLeft(Node node) {
        System.out.printf("Rotate Left ---> %s\n", node);
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
        
        updateHeight(node);
    }
    
    @Override
    public void rotateRight(Node node) {
        System.out.printf("rotate Right -- > %s\n", node);
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
        
        updateHeight(node);
    }
    
    /**
     * 현재 노드의 height 를 두 자식 중 큰 높이 + 1 로 갱신합니다.
     * 만약 갱신이 된다면 부모 또한 갱신합니다.
     * @param node
     */
    private void updateHeight(Node node) {
        if (node == null) {
            return;
        }
        
        int originHeight = node.height;
        node.height = node.findMaxChildHeight() + 1;
        
        if (originHeight != node.height) {
            updateHeight(node.parent);
        }
    }
}
