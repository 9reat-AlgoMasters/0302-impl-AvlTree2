public class AvlTree implements iAvlTree{
    
   private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }
    public AVLTree(Node root) {
        this.root = root;
        size = 1;
    }

    @Override
    public void insert(int value) {
        //루트부터 넣을 자리 탐색
        Node node = root;
        Node parent = null;

        //넣을 자리를 찾을 때까지 반복
        while(node != null){
            //넣을 자리를 찾았을 때 node는 null이므로 그 부모 노드를 기록해놓아야 함
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            parent = node;
            //넣을 값이 현재 node 값보다 작으면 왼쪽 서브트리로, 크면 오른쪽으로 이동
            if(value < node.value){
                node = node.left;
            }else if (value > node.value){
                node = node.right;
            }else{ // 해당 값이 이미 있으면 추가하지않고 끝
                return ;
            }
        }

        Node newNode = new Node(value);
        //루트가 null인 경우 루트로 추가 그 외에는 값 비교해서 기록해 놓은 부모노드의 자식으로 추가
        if(parent == null){
            root = newNode;
        }else if(value < parent.value){
            parent.left = newNode;
        }else{
            parent.right = newNode;
        }
        newNode.parent = parent;

        //avl트리 균형이 맞는지(두 서브트리 높이 차 1이하인지) 확인
        //부모노드부터 루트노드까지 균형 맞춰줌
        balanceDuringInsertion(parent, value);
        size++;
    }

    public void balanceDuringInsertion(Node node, int value) {
        while(node != null) {
            //아래(자식 노드)에서 균형 맞추다 변경된 높이를 해당 노드에도 반영
            node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

            int bal = getBalance(node);
            if (bal > 1) {
                //왼쪽 서브트리쪽으로 치우침, 넣을 노드가 자식의 왼쪽 : 오른쪽으로 회전 한번만
            /*     N                            C
                 /                            /   \
               C                 ->          A     N
              /
             A

            왼쪽 서브트리쪽으로 치우침, 넣을 노드가 자식 오른쪽 : 자식 왼쪽 회전 후 노드 오른쪽 회전

                    N                  N            A
                  /                   /            / \
                C             ->    A     ->     C     N
                 \                 /
                  A               C
             */
                if (node.left.value < value) {
                   rotateLeft(node.left);
                }
                rotateRight(node);

            } else if (bal < -1) {
                //오른쪽 서브트리쪽으로 치우침, 넣을 노드가 자식의 오른쪽 : 왼쪽 회전 한번만
                /*      N                          C
                          \                       /  \
                           C         ->          N    A
                             \
                              A
             오른쪽 서브트리쪽으로 치우침, 넣을 노드가 자식 왼쪽 : 자식 오른쪽 회전 후 노드 왼쪽 회전
                         N                 N           A
                           \                \         /  \
                             C       ->      A    -> N    C
                            /                 \
                           A                   C
                 */
                if (node.right.value > value) {
                    rotateRight(node.right);
                }
                rotateLeft(node);

            }
            //부모노드로 올라가서 반복
            node = node.parent;
        }
    }

    @Override
    public boolean contains(int value) {
        Node node = root;

        while(node != null){
            if(value < node.value){
                node = node.left;
            }else if (value > node.value){
                node = node.right;
            }else{ // 값을 찾으면 true 리턴
                return true;
            }
        }
        return false;
    }
    @Override
     public void delete(int value) {
        //value를 가진 노드(삭제할 노드) 찾기
        Node node = find(value);

        if(node != null){
            deleteNode(node);
            size--;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }


    public Node find(int value) {
        Node node = root;

        while(node != null){
            if(value < node.value){
                node = node.left;
            }else if (value > node.value){
                node = node.right;
            }else{ // 값을 찾으면 true 리턴
                return node;
            }
        }
        return null;
    }


    private void deleteNode(Node node) {

        //삭제할 노드의 자식이 없음(리프노드) => 부모노드의 자식에서 없애줌
        if(node.left == null && node.right == null){
            //루트를 삭제하는 경우
            if(node.parent == null){
                root = null;
            }else if(node.parent.left == node){
                node.parent.left = null;
            }else{
                node.parent.right = null;
            }
            //부모부터 올라가며 균형 맞춰주기
            balanceDuringDeletion(node.parent, node.value);

            //삭제할 노드가 한쪽 자식만 가짐 => 그 자식을 삭제할 노드의 부모노드의 자식으로 대체
            //루트면 그 자식이 새로운 루트가 됨
        }else if(node.right == null){
            if(node.parent == null) {
                root = node.left;
            }else if(node.parent.left == node){
                node.parent.left = node.left;
            }else{
                node.parent.right = node.left;
            }
            node.left.parent = node.parent;

            balanceDuringDeletion(node.parent, node.value);
        }else if(node.left == null){
            if(node.parent == null){
                root = node.right;
            }else if(node.parent.left == node){
                node.parent.left = node.right;
            }else{
                node.parent.right = node.right;
            }
            node.right.parent = node.parent;

            balanceDuringDeletion(node.parent, node.value);

            //삭제할 노드가 2개의 자식을 가짐
        }else {
            //삭제할 노드의 오른쪽 서브트리에서 가장 작은 값노드를 찾아서 부모노드의 자식 또는 루트로 설정
            Node found = findFromRightSubTree(node);
            Node parent = found.parent;

            //찾아낸 found은 왼쪽 자식이 없음 => 삭제할 노드의 왼쪽 자식을 갖다 붙임
            found.left = node.left;
            node.left.parent = found;

            //found가 삭제할 노드의 오른쪽 자식이었다면 그냥 그대로 삭제할 노드 자리로 올라오면 문제 없음
            //아니라면 found의 기존 오른쪽 자식 노드를 처리한 후 삭제할 노드의 오른쪽 자식을 새로 붙여줘야함
            if(found != node.right){
                if(found.right != null){
                    found.parent.left = found.right;
                    found.right.parent = found.parent;
                }else{
                    found.parent.left = null;
                }
                found.right = node.right;
                node.right.parent = found;
            }else{
                parent = found;
            }

            if(node.parent == null){
                root = found;
            }else if(node.parent.left == node){
                node.parent.left = found;
            }else{
                node.parent.right = found;
            }
            found.parent = node.parent;

            balanceDuringDeletion(parent, node.value);
        }
    }

    public Node findFromRightSubTree(Node node){
        //오른쪽 서브트리에서 제일 작은 값 => 왼쪽으로만 가다가 왼쪽 자식 없으면 해당 노드 리턴
        Node target = node.right;
        while(target.left != null){
            target = target.left;
        }

        return target;
    }

    public void balanceDuringDeletion(Node node, int value) {
        while(node != null) {
            node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

            int bal = getBalance(node);
            //왼쪽 서브트리가 더 깊음 (왼쪽으로 치우쳐져 있음)
            if (bal > 1) {
                //치우쳐진 서브트리의 루트를 기준으로 어느쪽으로 치우쳐져있는지 확인
                int bal2 = getBalance(node.left);
                if (bal2 < 0) { //LR
                    rotateLeft(node.left);
                }
                //LL
                rotateRight(node);

            //오른쪽 서브트리가 더 깊음 (오른쪽으로 치우쳐져 있음)
            } else if (bal < -1) {
                int bal2 = getBalance(node.right);
                if (bal2 > 0) { //RL
                    rotateRight(node.right);
                }
                //RR
                rotateLeft(node);

            }
            //부모노드로 올라가서 반복
            node = node.parent;
        }
    }

    //노드를 오른쪽으로 회전
    public void rotateRight(Node node){
        Node temp = node.left;
        Node temp2 = temp.right;
        //temp의 오른쪽 자식을 node의 왼쪽 자식으로 붙여줄 것
        if(temp2 != null){
            temp2.parent = node;
        }

        //temp를 node의 부모로 바꿔주기
        //기존 node의 부모자식 관계를 temp가 이어받도록 함
        temp.right = node;
        temp.parent = node.parent;
        if(temp.parent == null){
            root = temp;
        }else if(node == temp.parent.left){
            temp.parent.left = temp;
        }else{
            temp.parent.right = temp;
        }

        node.left = temp2;
        node.parent = temp;

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        temp.height = Math.max(getHeight(temp.left), getHeight(temp.right)) + 1;
    }

    public void rotateLeft(Node node){
        Node temp = node.right;
        Node temp2 = temp.left;
        if(temp2 != null){
            temp2.parent = node;
        }

        temp.left = node;
        temp.parent = node.parent;
        if(temp.parent == null){
            root = temp;
        }else if(node == temp.parent.left){
            temp.parent.left = temp;
        }else{
            temp.parent.right = temp;
        }

        node.right = temp2;
        node.parent = temp;

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        temp.height = Math.max(getHeight(temp.left), getHeight(temp.right)) + 1;
    }

    public int getHeight(Node node){
        if(node == null){
            return 0;
        }
        return node.height;
    }

    public int getBalance(Node node){
        if(node == null){
            return 0;
        }
        //왼쪽 서브트리 자식 수가 많으면 양수
        return getHeight(node.left) - getHeight(node.right);
    }

    public void inOrder(Node node){
        if(node != null) {
            inOrder(node.left);
            System.out.println(node.value+" "+node.height);

            inOrder(node.right);
        }
    }

    public void inOrder() {
        inOrder(root);
    }
}
