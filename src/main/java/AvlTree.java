public class AvlTree implements iAvlTree{
    public Node root;
    int size;

    // 높이 계산 함수
    public int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // 균형 계수 계산 함수
    public int balanceFactor(Node node) {
        return height(node.left) - height(node.right);
    }

    public void rotateLeft(Node x) {
        rotateLeftReturnRightChildNode(x);
    }
    // 회전 함수
    public Node rotateLeftReturnRightChildNode(Node x) {
        Node y = x.right;
        Node z = y.left;
        y.left = x;
        x.right = z;
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }

    public void rotateRight(Node x) {
        rotateRightReturnLeftChildNode(x);
    }

    public Node rotateRightReturnLeftChildNode(Node x) {
        Node y = x.left;
        Node z = y.right;
        y.right = x;
        x.left = z;
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }

    // 삽입 함수
    public void insert(int key) {
        root = insertNode(root, key);
        this.size++;
    }

    public Node insertNode(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }

        if (key < node.value) {
            node.left = insertNode(node.left, key);
        } else if (key > node.value) {
            node.right = insertNode(node.right, key);
        } else {
            return node; // 중복된 값은 삽입하지 않음
        }

        // 노드의 균형 계수가 2 이상이면 회전
        int balance = balanceFactor(node);
        if (balance > 1 && key < node.left.value) {
            return rotateRightReturnLeftChildNode(node);
        }
        if (balance < -1 && key > node.right.value) {
            return rotateLeftReturnRightChildNode(node);
        }
        if (balance > 1 && key > node.left.value) {
            node.left = rotateLeftReturnRightChildNode(node.left);
            return rotateRightReturnLeftChildNode(node);
        }
        if (balance < -1 && key < node.right.value) {
            node.right = rotateRightReturnLeftChildNode(node.right);
            return rotateLeftReturnRightChildNode(node);
        }

        // 노드의 높이 업데이트
        node.height = 1 + Math.max(height(node.left), height(node.right));

        return node;
    }

    // 탐색 함수
    public boolean contains(int key) {
        Node node = root;
        while (node != null) {
            if (key < node.value) {
                node = node.left;
            } else if (key > node.value) {
                node = node.right;
            } else {
                return true;
            }
        }
        return false;
    }

    // 삭제 함수
    public void delete(int key) {
        root = deleteNode(root, key);
        this.size--;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    private Node deleteNode(Node node, int key) {
        if (node == null) {
            return null;
        }

        if (key < node.value) {
            node.left = deleteNode(node.left, key);
        } else if (key > node.value) {
            node.right = deleteNode(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left == null) ? node.right : node.left;
            } else {
                Node minRight = node.right;
                while (minRight.left != null) {
                    minRight = minRight.left;
                }
                node.value = minRight.value;
                node.right = deleteNode(node.right, minRight.value);
            }
        }

        if (node == null) {
            return null;
        }

        // 노드의 균형 계수가 2 이상이면 회전
        int balance = balanceFactor(node);
        if (balance > 1 && balanceFactor(node.left) >= 0) {
            return rotateRightReturnLeftChildNode(node);
        }
        if (balance > 1 && balanceFactor(node.left) < 0) {
            node.left = rotateLeftReturnRightChildNode(node.left);
            return rotateRightReturnLeftChildNode(node);
        }
        if (balance < -1 && balanceFactor(node.right) <= 0) {
            return rotateLeftReturnRightChildNode(node);
        }
        if (balance < -1 && balanceFactor(node.right) > 0) {
            node.right = rotateRightReturnLeftChildNode(node.right);
            return rotateLeftReturnRightChildNode(node);
        }

        // 노드의 높이 업데이트
        node.height = 1 + Math.max(height(node.left), height(node.right));

        return node;
    }
}