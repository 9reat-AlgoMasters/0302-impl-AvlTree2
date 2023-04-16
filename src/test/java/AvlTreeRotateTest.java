import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class AvlTreeRotateTest {
    static AvlTree tree;
    
    
    @DisplayName("부모가 없을 때 RR test")
    @Test
    void rotateLeftTest1() {
        Node root = new Node(10);
        root.height = 3;
        
        Node right = new Node(20);
        right.height = 2;
        
        Node rightRight = new Node(40);
        
        right.right = rightRight;
        root.right = right;
        
        tree = new AvlTree(root);
        tree.rotateLeft(root);


        assertThat(root.height).isEqualTo(3);
        assertThat(root.parent).isEqualTo(right);
        assertThat(right.left).isEqualTo(root);
    }
    
    @DisplayName("부모가 있을 때 RR test")
    @Test
    void rotateLeftTest2() {
        Node root = new Node(10);
        root.height = 4;
        
        Node left1 = new Node(5);
        root.left = left1;
        root.left.parent = root;
        
        Node right1 = new Node(20);
        right1.height = 3;
        
        Node right2 = new Node(40);
        right2.height = 2;
        
        Node right3 = new Node(50);
        
        right2.right = right3;
        right3.parent = right2;
        
        right1.right = right2;
        right2.parent = right1;
        
        root.right = right1;
        right1.parent = root;
        
        tree = new AvlTree(root);
        tree.rotateLeft(right1);

        // 높이 변화 없음 테스트
        assertThat(root.height).isEqualTo(4);
        assertThat(right1.height).isEqualTo(3);
        assertThat(right2.height).isEqualTo(2);

        // 올바른 링크 테스트
        assertThat(root.right).isEqualTo(right2);
        assertThat(right2.parent).isEqualTo(root);
        assertThat(right1.parent).isEqualTo(right2);
        assertThat(right2.left).isEqualTo(right1);

    }
    
    @DisplayName("부모가 있고 회전하려는 쪽에 왼쪽 자식이 있을 때 RR test")
    @Test
    void rotateLeftTest3() {
        Node root = new Node(10);
        root.height = 4;
        
        Node left1 = new Node(5);
        root.left = left1;
        root.left.parent = root;
        
        Node right1 = new Node(20);
        right1.height = 3;
        
        Node right2 = new Node(40);
        right2.height = 2;
        Node right2_left = new Node(30);
        right2.left = right2_left;
        right2.left.parent = right2;
        
        Node right3 = new Node(50);
        
        right2.right = right3;
        right3.parent = right2;
        
        right1.right = right2;
        right2.parent = right1;
        
        root.right = right1;
        right1.parent = root;
        
        tree = new AvlTree(root);
        tree.rotateLeft(right1);
        
        assertThat(root.height).isEqualTo(4);
        assertThat(right1.height).isEqualTo(3);
        assertThat(right2.height).isEqualTo(2);

        assertThat(root.right).isEqualTo(right2);
        assertThat(right2.parent).isEqualTo(root);
        assertThat(right1.parent).isEqualTo(right2);
        assertThat(right2.left).isEqualTo(right1);
        assertThat(right2_left.parent).isEqualTo(right1);
        assertThat(right1.right).isEqualTo(right2_left);
    }

    @DisplayName("부모가 없을 때 RL test")
    @Test
    void RLTest1() {
        Node root = new Node(10);
        root.height = 4;

        Node l = new Node(5);
        root.left = l;
        root.left.parent = root;

        Node r = new Node(20);
        r.height = 3;

        Node rl = new Node(15);
        rl.height = 2;
        r.left = rl;
        rl.parent = r;

        rl.left = new Node(13);
        rl.left.parent = rl;
        rl.right = new Node(18);
        rl.right.parent = rl;

        Node rr = new Node(40);

        r.right = rr;
        rr.parent = r;

        root.right = r;
        r.parent = root;

        tree = new AvlTree(root);
        tree.rotateRight(root.right);
        tree.rotateLeft(root);

        assertThat(root.height).isEqualTo(4);
        assertThat(r.height).isEqualTo(3);
        assertThat(rl.height).isEqualTo(2);
        assertThat(rr.height).isEqualTo(1);

        assertThat(rl.left).isEqualTo(root);
        assertThat(root.parent).isEqualTo(rl);
        assertThat(rl.right).isEqualTo(r);
        assertThat(r.parent).isEqualTo(rl);
        assertThat(r.right).isEqualTo(rr);
        assertThat(rr.parent).isEqualTo(r);
    }
    
    @DisplayName("부모가 있을 때 LL test")
    @Test
    void rotateRightTest1() {
        Node root = new Node(20);
        root.height = 4;
        
        Node right1 = new Node(30);
        root.right = right1;
        root.right.parent = root;
        
        Node left1 = new Node(15);
        left1.height = 3;
        
        Node left2 = new Node(10);
        left2.height = 2;
        
        Node left3 = new Node(5);
        
        left2.left = left3;
        left3.parent = left2;
        
        left1.left = left2;
        left2.parent = left1;
        
        root.left = left1;
        left1.parent = root;
        
        tree = new AvlTree(root);
        tree.rotateRight(left1);
        
        assertThat(root.height).isEqualTo(4);
        assertThat(left1.height).isEqualTo(3);
        assertThat(left2.height).isEqualTo(2);

        assertThat(root.left).isEqualTo(left2);
        assertThat(left2.parent).isEqualTo(root);
        assertThat(left1.parent).isEqualTo(left2);
        assertThat(left2.right).isEqualTo(left1);
    }
}