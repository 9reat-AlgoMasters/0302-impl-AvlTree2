import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
        assertThat(root.getBalanceFactor()).isEqualTo(0);
    }
    
    @DisplayName("오른쪽 자식없을 때")
    @Test
    void balanceFactorTest2() {
        root.left = new Node(2);
        root.height = 2;
        assertThat(root.getBalanceFactor()).isEqualTo(1);
    }
    
    @DisplayName("왼쪽 자식없을 때")
    @Test
    void balanceFactorTest3() {
        root.right = new Node(2);
        root.height = 2;
        assertThat(root.getBalanceFactor()).isEqualTo(-1);
    }
    
}