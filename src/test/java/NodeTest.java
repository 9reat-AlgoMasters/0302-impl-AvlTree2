import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NodeTest {
    static Node root;
    
    @BeforeEach
    void beforeEach() {
        root = new Node(1);
    }
    
    @DisplayName("왼쪽 오른쪽 모두 자식이 없을 때")
    @Test
    void balanceFactorTest1() {
        assertThat(root.findBalanceFactor()).isEqualTo(0);
    }
    
    @DisplayName("오른쪽 자식없을 때")
    @Test
    void balanceFactorTest2() {
        root.left = new Node(2);
        root.height = 2;
        assertThat(root.findBalanceFactor()).isEqualTo(1);
    }
    
    @DisplayName("왼쪽 자식없을 때")
    @Test
    void balanceFactorTest3() {
        root.right = new Node(2);
        root.height = 2;
        assertThat(root.findBalanceFactor()).isEqualTo(-1);
    }
    
    @DisplayName("자식 최대 높이 구하기")
    @Test
    void findMaxChildHeightTest1() {
        root.left = new Node(2);
        root.right = new Node(5);
        root.left.height = 5;
        root.right.height = 2;
        assertThat(root.findMaxChildHeight()).isEqualTo(5);
    }
    
    @DisplayName("자식이 하나만 있을 때 최대 높이 구하기")
    @Test
    void findMaxChildHeightTest2() {
        root.left = new Node(2);
        assertThat(root.findMaxChildHeight()).isEqualTo(1);
    }
    
    @DisplayName("자식이 없을 때 최대 높이 구하기")
    @Test
    void findMaxChildHeightTest3() {
        assertThat(root.findMaxChildHeight()).isEqualTo(0);
    }
    
}