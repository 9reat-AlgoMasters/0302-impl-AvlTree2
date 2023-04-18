# Test 정보

## 테스트 참고사항
- 두 개의 이진탐색트리가 같다는 것을 테스트하기 위해 
`각 노드의 높이가 같은 이진트리는 서로 같은 트리이다`
라는 개념을 활용하였습니다. 
  - 이진 탐색 트리 각 노드의 높이가 주어졌을 때,`어떤 이진탐색트리에서나 루트노드의 높이는 가장 높다` 라는 사실을 이용하면 분할정복을 이용해 유일한 이진 탐색 트리를 만들 수 있습니다.

## 테스트를 하기 위해 추가해야 할 사항

### `Node` Class

- **Field**
    - `Node parent`
- **Method**
    - `int findBalanceFactor()`
      ```java
      int findBalanceFactor() {
          return getChildHeight(LEFT) - getChildHeight(RIGHT);
      }
      ```

    - `int findMaxChildHeight()`
        ```java
        public int findMaxChildHeight() {
                return Math.max(getChildHeight(LEFT), getChildHeight(RIGHT));
            }
        ```

### `AvlTree` Class

- **Method**
    - 생성자1
        ```java
        public AvlTree(int rootValue) {
        root = new Node(rootValue);
        size = 1;
        }
        ```
    - 생성자2
        ```java
        public AvlTree(Node root) {
                this.root = root;
            }
        ```
    - `int findHeightByValue(int value)`
        ```java
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
        ```

## 테스트 사항
### NodeTest
- BalanceFactor Test
    1. 왼쪽 오른쪽 모두 자식이 없을 때
    2. 오른쪽 자식없을 때
    3. 왼쪽 자식없을 때
- 자식 중 Max Height Test
    1. 양쪽 자식 모두 있을 때
    2. 자식이 하나만 있을 때
    3. 자식이 없을 때

### AvlTreeRotateTest - 회전
1. 부모가 없을 때 rotateLeft(RR) 테스트
2. 부모가 있을 때 rotateLeft(RR) 테스트
3. 부모가 있고 회전하려는 쪽에 왼쪽 자식이 있을 때 rotateLeft(RR) 테스트
4. 부모가 없을 때 rotateRight -> rotateLeft (RL) 테스트
5. 부모가 있을 때 rotateLeft (LL) 테스트

### AvlTreeInsertTest - 삽입
1. insert 결과 RR 조건의 불균형 트리가 만들어질 때 테스트
2. insert 결과 LL 조건의 불균형 트리가 만들어질 때 테스트
3. insert 결과 LR 조건의 불균형 트리가 만들어질 때 테스트
4. insert 결과 RL 조건의 불균형 트리가 만들어질 때 테스트
5. 회전하는 상위 노드가 루트노드가 아닐 때 RL insert 테스트
6. insert 시 올바른 size 테스트

### AvlTreeDeleteTest - 삭제
1. 자식이 없는 경우 
2. 자식이 하나 있는 경우 
3. 자식이 하나 있고 delete 이후 rotate 일어날 경우
4. 자식이 모두 있는 경우
5. 자식이 모두 있고 delete 이후 rotate 일어날 경우
6. delete 시 올바른 size 테스트

### AvlTreeTest
1. 이진 탐색 트리에 포함된 노드 test : `contains` -> `true`
 2. 이진 탐색 트리에 포함되지 않은 노드 test : `contains` -> `true`

# Implementation 정보

## interface - `iAvlTree`

### `void insert(int value)`

`value`를 적절한 곳에 삽입합니다.

### `boolean contains(int value)`

현재 이진탐색트리에 `value`가 있는지 확인해서 `boolean`값으로 반환합니다.

### `void delete(int value)`

이진탐색트리에서 `value`값을 삭제합니다.

### `void insert(int value)`

`value`를 적절한 곳에 삽입합니다.

### `boolean isEmpty()`

이진탐색트리가 비어있는지를 `boolean` 값으로 반환합니다.

### `int size()`

이진탐색트리에 들어있는 값들의 개수를 반환합니다.

### `void rotateLeft(Node node)`

`node`를 루트로 하는 트리(혹은 서브트리)를 왼쪽으로 회전시킵니다.

### `void rotateRight(Node node)`

`node`를 루트로 하는 트리(혹은 서브트리)를 오른쪽으로 회전시킵니다.

## `Node` 클래스 정보

### 공통 필드

- `int value` : 각 노드에 담긴 값
- `int height` : 해당 노드를 루트로 하는 이진탐색트리 높이
- `Node left` : 왼쪽 자식
- `Node right` : 오른쪽 자식
