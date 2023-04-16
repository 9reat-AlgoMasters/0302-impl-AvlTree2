import exceptions.CustomDuplicatedElementException;
import exceptions.CustomNoSuchElementException;

public class AvlTree implements iAvlTree{
    int size;
    Node root;


    public AvlTree() {
        this.size = 0;
        root = null;
    }
    @Override
    public boolean contains(int value) {
        if (root != null) {
            return containsRecur(value, root) != null;
        }
        return true;
    }

    public Node containsRecur(int value, Node node) {
        if (value == node.value) {
            return node;
        }
        else if (value < node.value) {
            if (node.left == null) return null;
            return containsRecur(value, node.left);
        } else {
            if (node.right == null) return null;
            return containsRecur(value, node.right);
        }
    }

    public int height(Node node) {
        return node != null ? node.height : -1;
    }
    public void updateHeight(Node node) { // 던저져지는 node의 height를 자식들의 height와 비교하여, 큰놈에 +1
        if(node == null) return;
        int leftChildHeight = height(node.left);
        int rightChildHeight = height(node.right);
        node.height = Math.max(leftChildHeight, rightChildHeight) + 1;
    }

    // 노드의 balanceFactor : 오른쪽이 더 많으면 양수, 그렇지 않으면 음수 또는 0
    public int balanceFactor(Node node) {
        if (node == null) return 0;
        return height(node.right) - height(node.left);
    }

    @Override
    public void rotateLeft(Node node) {
        rotateLeftReturnLeftChild(node);
    }

    public Node rotateLeftReturnLeftChild(Node node) {
        Node rightChild = node.right;

        node.right = rightChild.left;
        rightChild.left = node;
        if(node.value == root.value) {
            root = rightChild;
        }

        updateHeight(node);
        updateHeight(rightChild);

        return rightChild; // 회전하면서 node의 오른쪽 자식이 올라오면 그 녀석을 리턴
    }

    @Override
    public void rotateRight(Node node) {
        rotateRightReturnLeftChild(node);
    }
    public Node rotateRightReturnLeftChild(Node node) {
        Node leftChild = node.left;
        node.left = leftChild.right;
        leftChild.right = node;

        if(node.value == root.value) {
            root = leftChild;
        }

        updateHeight(node);
        updateHeight(leftChild);

        return leftChild; // 회전하면서 node의 왼쪽 자식이 올라오면 그 녀석을 리턴
    }


    // 해당 노드의 BalacneFactor를 체크하고, 절대값 1보다 크면 balanceFactor를 조정하는 함수
    public Node reBalance(Node node) {
        Node rootCheckNode = node;
        int balanceFactor = balanceFactor(node);
        // 왼쪽 서브트리가 더 큰 경우
        if (balanceFactor < -1) {
            if (balanceFactor(node.left) <= 0) {
                // R회전
                node = rotateRightReturnLeftChild(node);
            } else {
                // LR회전
                node.left = rotateLeftReturnLeftChild(node.left);
                node = rotateRightReturnLeftChild(node);
            }
        }
        if (root != null && rootCheckNode != null && rootCheckNode.value == root.value) {
            root = node;
        }

        // 오른쪽 서브트리가 더 큰 경우
        if (balanceFactor > 1) {
            if (balanceFactor(node.right) >= 0) {
                // L회전
                node = rotateLeftReturnLeftChild(node);
            } else {
                // RL회전
                node.right = rotateRightReturnLeftChild(node.right);
                node = rotateLeftReturnLeftChild(node);
            }
        }

        return node;

    }

    // ========= insert start ========= //
    @Override
    public void insert(int value) {
        root = insert(value, root);
        System.out.printf("root = %d, value = %d, height = %d, BF = %d\n",  root.value, value, root.height, balanceFactor(root));
        size++;
    }
    public Node insert(int key, Node node) {
        node = insertNode(key, node);
        return reBalance(node);
    }
    Node insertNode(int key, Node node) {
        // 노드가 null이면 새로운 노드 만들기
        if (node == null) {
            node = new Node(key);
        }

        // key값과 node의 값을 비교하여 왼쪽, 오른쪽에 넣을지 정하기
        else if (key < node.value) {
            node.left = insertNode(key, node.left);
            updateHeight(node);
        } else if (key > node.value) {
            node.right = insertNode(key, node.right);
            updateHeight(node);
        } else {
            throw new IllegalArgumentException("해당 key 값이 Tree에 존재합니다. " + key);
        }

        return node;
    }

    // ========= insert end ========= //

    // ========= delete start ========= //
    @Override
    public void delete(int value) {
        root = delete(value, root);
        size--;
    }
    public Node delete(int key, Node node) {
        node = deleteNode(key, node);
        updateHeight(node);
        return reBalance(node);
    }

    public Node deleteNode(int key, Node node) {

        // 노드가 존재하지 않을 경우
        if (node == null) {
            throw new CustomNoSuchElementException("해당 값을 가진 노드가 Tree에 없습니다." + key);
        }

        // key값과 node의 값을 비교하여, 같을때까지 함수를 호출하며 삭제해주기
        if (key < node.value) {
            node.left = deleteNode(key, node.left);
        } else if (key > node.value) {
            node.right = deleteNode(key, node.right);
        }

        // 키값과 일치한 녀석 찾았을 떄,
        // 리프 노드일경우
        else if (node.left == null && node.right == null) {
            node = null;
        }

        // 자식이 한개일 경우
        else if (node.left == null) { // 자식이 오른쪽에 있으면
            if(root.value == node.value) {
                node = node.right;
                root = node;
            }
            else {
                node = node.right;
            }
        } else if (node.right == null) { // 자식이 왼쪽에 있으면
            if(root.value == node.value) {
                node = node.left;
                root = node;
            }
            else {
                node = node.left;
            }
        }

        // 자식이 두 개일 경우
        else {
            if (root.value == node.value) {
                deleteHaveTwoChild(node);
                root = node;
            }
            else {
                deleteHaveTwoChild(node);
            }
        }
        return node;
    }

    // 자식이 두개 존재할때 삭제하는 메서드
    public void deleteHaveTwoChild(Node node) {

        //오른쪽 자식노드에 대해서 가장 작은 자식을 찾고
        Node isS = findMinimum(node.right);

        // 교환 해주며
        node.value = isS.value;

        // 오른쪽 자식노드에 대해서도 deleteNode를 다시 호출하며 수행해주어, 결과적으로 이진탐색트리의 형태를 갖게만듬
        node.right = deleteNode(isS.value, node.right);
    }

    public Node findMinimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    // ========= delete end ========= //

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    }