import exceptions.CustomDuplicatedElementException;
import exceptions.CustomNoSuchElementException;

public class AvlTree implements iAvlTree{
    public Node root;
    public int size;

    public AvlTree() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public void insert(int value) {
        if(contains(value)) {
            throw new CustomDuplicatedElementException();
        }
        root = insertRecur(root, value);
        root.height = Math.max(getHeight(root.right), getHeight(root.left)) + 1;
        size++;
    }

    public Node insertRecur(Node node, int value){
        if (node == null) {
            return new Node(value, 1);
        }
        if (value < node.value) {
            node.left = insertRecur(node.left, value);
            node.left.height = Math.max(getHeight(node.left.left), getHeight(node.left.right)) + 1;
        } else if (value > node.value) {
            node.right = insertRecur(node.right, value);
            node.right.height = Math.max(getHeight(node.right.left), getHeight(node.right.right)) + 1;
        }
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;


        return checkAndRotateByCase(node, value);
    }

    public int getHeight(Node node) {
        return node == null ? 0 : node.height;
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

        return n;
    }

    public Node checkAndRotateByCase2(Node n) {
        int bf = balanceFactor(n); // 왼  - 오

        // LL
        if(bf > 1 && balanceFactor(n.left)>=0) {
            System.out.println("LL");
            return rightRotate(n);
        }

        // RR
        if (bf < -1 && balanceFactor(n.right)<=0) {
            System.out.println("RR");
            return leftRotate(n);
        }

        // RL
        if(bf < -1 && balanceFactor(n.right)>0) {
            System.out.println("RL");
            n.right = rightRotate(n.right);
            return leftRotate(n);
        }

        // LR
        if(bf > 1 && balanceFactor(n.left)<0) {
            System.out.println("LR");
            n.left = leftRotate(n.left);
            return rightRotate(n);
        }

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
        if(!contains(value)) {
            throw new CustomNoSuchElementException();
        }
        root = deleteRecur(root, value);
        System.out.println("삭제된 값: " + value);
//        System.out.println(root.value);
        System.out.println();
        inOrder(root);
        System.out.println();
        size--;
    }

    public Node deleteRecur(Node n, int val) {
        Node temp = null;
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
                    // size--;
                }

                // 자식 1개
                if (count == 1) {
                    System.out.println("자식이 한개입니다.");
                    if (temp == n.right) {
                        System.out.println("오른쪽 자식만 있습니다.");
                    } else {
                        if(n.value == 48){
                            System.out.println(temp.value);
//                    System.out.println(n.right.value);
//                            System.out.println(root.value);
                            System.out.println("!!!! " + count);
                        }
                        System.out.println("왼쪽 자식만 있습니다.");
                    }
                    n = temp; // temp 에는 이미 왼쪽 or 오른쪽 노드를 들고있고 재귀 올라가면서 부모와 연결된다

                    // size--;

                }

            } else{ // 자식 2개
                // 자식 2개다  있을때
                // 삭제할 노드가 currentNode
                System.out.println("자식이 2개 있습니다.");
                temp = n.right;
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

                n.right = deleteRecur(n.right, temp.value);
                // size--;
            }

        }
        
        // 한개
        if(n==null) {
            return n;
        }

        n.height = Math.max(getHeight(n.left), getHeight(n.right)) + 1;

        return checkAndRotateByCase2(n); // delete재귀 나오면서 확인후 균형이 아니라면 확인후 바꿔줌
    }
    
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    
    @Override
    public int size() {
        return size;
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


        // System.out.println("바뀐 n의 높이 확인: " + getHeight(n.left)+ getHeight(n.right));
        n.height = Math.max(getHeight(n.left), getHeight(n.right)) + 1;
        // System.out.println("새롭게 올라온 temp의 높이 확인: " + getHeight(temp.left)+" " + getHeight(temp.right));
        temp.height = Math.max(getHeight(temp.left), getHeight(temp.right)) + 1;

        return temp;
    }

    public void inOrder(Node n) {
        if(n == null) {
            return;
        }
        inOrder(n.left);
        System.out.println(n.value);
        inOrder(n.right);

    }
}
