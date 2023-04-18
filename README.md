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

# 테스트 정보
## Insert Test
### Insert1
- 삽입 때마다 size 1씩 증가
- 이미 존재하는 값 삽입 시 size 유지
- 노드의 높이 확인

### Insert2
![image](https://user-images.githubusercontent.com/67370317/232674373-6aef5bb3-d6ed-4f61-ad4d-9e2cc31fcb24.png)
- 삽입 후 균형 잡기 -> LL case
- 새 루트 5의 자식과 이전 루트 10의 자식 확인
- 각 노드의 높이 확인

### Insert3
![image](https://user-images.githubusercontent.com/67370317/232675005-767a0a07-ac65-4dc8-8b37-b1d49135813e.png)
- LR case

### Insert4
![image](https://user-images.githubusercontent.com/67370317/232675532-ecf2b9dd-5b61-4afd-bbf1-c76a0ea3f3c8.png)
- RR case 

### Insert5
![image](https://user-images.githubusercontent.com/67370317/232675764-236ad59d-369e-4b75-bfa0-8c0037c29766.png)
- RL case

### Insert6


## Delete Test
### Delete1
- 삭제 시 size와 높이가 줄어드는 지 확인

### Delete2
- 자식이 없는 노드 삭제

### Delete3
- 자식이 하나 있는 노드 삭제

### Delete4
- 자식이 하나있는 루트노드 삭제
### Delete5
- 자식 둘 있는 노드 삭제
  - 교체할 노드가 삭제할 노드의 오른쪽 자식인 경우
  - 교체할 노드에게 자식이 있는 경우
  - 삭제 후 균형이 무너진 경우 
  
### Delete6

## Rotate Test
### RotateLeft1
- LL case 에서의 회전 후 바뀐 부모-자식 관계와 높이 확인

### RotateLeft2
- LR case 에서의 회전 후 바뀐 부모-자식 관계와 높이 확인

### RotateRight1
- RR case 에서의 회전 후 바뀐 부모-자식 관계와 높이 확인

### RotateRight2
- RL case 에서의 회전 후 바뀐 부모-자식 관계와 높이 확인

## ETC Test
### IsEmpty
- 최초 비어있는지 확인(true)
- 노드 하나 삽입 후 확인(false)
- 삭제 후 확인(true)

### Contains
- 트리 내에 존재하는지 확인
