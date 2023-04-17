public class AvlTree implements iAvlTree{
    private Node root;
    private int size;

    @Override
    public void insert(int value) {
        root = insertRecur(root, value);
        size++;
    }

    public Node insertRecur(Node node, int value){
        if (node == null) {
            return new Node(value);
        }
        if (value < node.value) {
            node.left = insertRecur(node.left, value);
        } else if (value > node.value) {
            node.right = insertRecur(node.right, value);
        }

        return checkAndRotateByCase(node, value);
    }

    public int getHeight(Node node) {
        return node == null ? -1 : node.height;
    }



    // 두 서브트리의 높이 차이
    // 균형이려면 -1, 0, 1 중 하나의 값을 리턴해야 한다
    public int balanceFactor(Node n) {
        if (n == null) {
            return 0;
        }

        int leftHeight = getHeight(n.left);
        int rightHeight = getHeight(n.right);

        return leftHeight - rightHeight;
    }

    public Node checkAndRotateByCase(Node n, int val) {
        int bf = balanceFactor(n); // 왼  - 오

        // LL
        if(bf > 1 && val < n.left.value) {
            return rightRotate(n);
        }

        // RR
        if (bf < -1 && val > n.right.value) {
            return leftRotate(n);
        }

        // RL
        if(bf < -1 && val < n.right.value) {
            n.right = rightRotate(n.right);
            return leftRotate(n);
        }

        // LR
        if(bf > 1 && val > n.left.value) {
            n.left = leftRotate(n.left);
            return rightRotate(n);
        }

        System.out.println("----");
        return n;
    }
    
    @Override
    public boolean contains(int value) {
        Node node = root;

        while(node != null){
            if(value < node.value){
                node = node.left;
            }else if (value > node.value){
                node = node.right;
            }else{
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void delete(int value) {
        deleteRecur(root, value);
    }

    public Node deleteRecur(Node n, int val) {
        if (n == null) {
            return null;
        }

        if (val < n.value) {
            n.left = deleteRecur(n.left, val);
        } else if (val > n.value) {
            n.right = deleteRecur(n.right, val);
        } else {
            // 자식 0개 또는 1개
            if ((n.left == null) || (n.right == null)) {
                Node temp = null;
                int count = 0;
                if (n.left != null) {
                    temp = n.left;
                    count++;
                }
                if (n.right != null) {
                    temp = n.right;
                    count++;
                }

                // 자식 0개
                if (count == 0) {
                    System.out.println("자식이 없습니다.");
                    temp = n;
                    n = null; //없앰
                    size--;
                }

                // 자식 1개
                if (count == 1) {
                    System.out.println("자식이 한개입니다.");
                    if (temp == n.right) {
                        System.out.println("오른쪽 자식만 있습니다.");
                    } else {
                        System.out.println("왼쪽 자식만 있습니다.");
                    }
                    n = temp; // temp 에는 이미 왼쪽 or 오른쪽 노드를 들고있고 재귀 올라가면서 부모와 연결된다
                    size--;

                }

            } else{ // 자식 2개
                // 자식 2개다  있을때
                // 삭제할 노드가 currentNode
                System.out.println("자식이 2개 있습니다.");
                Node temp = n.right;
                Node tempParent = null;

                // 삭제할 노드의 오른쪽 자식을 봤을때
                // 왼쪽 자식이 null이라면 삭제할 노드의 오른쪽 자식이 올라와야한다.
                // 오른쪽 서브트리의 가장 작은 값을 찾아간다.
                while (temp.left != null) {
                    temp = temp.left;
                }

                n.value = temp.value; //값 바꿔줌

                // 오른쪽에서 제일 작은 노드를 찾았는데
                // 그 노드의 오른쪽 노드가 있는 경우
                // 바로 위의 부모에 연결 해줌
                // => 재귀로 해결
                deleteRecur(n.right, temp.value);
                size--;
            }

        }

        return checkAndRotateByCase(n, val); // delete재귀 나오면서 확인후 균형이 아니라면 확인후 바꿔줌
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
    public void rotateRight(Node node) {
        // rightRotate(node);
    }

    @Override
    public void rotateLeft(Node node) {
        // leftRotate(node);
    }

    public Node leftRotate(Node n) {
        Node temp = n.right;
        if (n == root) {
            root = temp;
        }
        n.right = temp.left; // n의 오른쪽 자식의 왼쪽 자식을 n의 오른쪽에 붙이는 과정
        temp.left = n; //  n의 오른쪽 자식의 왼쪽 자식을 n으로 바꿔버림


        n.height = Math.max(getHeight(n.right), getHeight(n.left)) + 1;
        temp.height = Math.max(getHeight(temp.right), getHeight(temp.left)) + 1;

        return temp;
    }

    public Node rightRotate(Node n) {
        Node temp = n.left;
        if (n == root) {
            root = temp;
        }
        n.left = temp.right; // n의 왼쪽 자식의 오른쪽 자식을 n의 왼쪽에 붙이는 과정
        temp.right = n; //  n의 왼쪽 자식의 오른쪽 자식을 n으로 바꿔버림

        n.height = Math.max(getHeight(n.left), getHeight(n.right)) + 1;
        temp.height = Math.max(getHeight(temp.right), getHeight(temp.right)) + 1;

        return temp;
    }
}
