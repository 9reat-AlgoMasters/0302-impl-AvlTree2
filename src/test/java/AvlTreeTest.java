import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class AvlTreeTest {
    static AvlTree tree;
    
    
    @DisplayName("부모가 없을 때 rotate left test")
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
        
        assertThat(root.height).isEqualTo(1);
        assertThat(root.parent).isEqualTo(right);
        assertThat(right.left).isEqualTo(root);
    }
    
    @DisplayName("부모가 있을 때 rotate left test")
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
        
        assertThat(root.height).isEqualTo(3);
        assertThat(root.right).isEqualTo(right2);
        assertThat(right2.parent).isEqualTo(root);
        assertThat(right1.parent).isEqualTo(right2);
        assertThat(right2.left).isEqualTo(right1);
        assertThat(right2.height).isEqualTo(2);
        assertThat(right1.height).isEqualTo(1);
    }
    
    @DisplayName("부모가 있고 회전하려는 쪽에 왼쪽 자식이 있을 때 rotate left test")
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
        
        assertThat(root.height).isEqualTo(3);
        assertThat(root.right).isEqualTo(right2);
        assertThat(right2.parent).isEqualTo(root);
        assertThat(right1.parent).isEqualTo(right2);
        assertThat(right2.left).isEqualTo(right1);
        assertThat(right2.height).isEqualTo(2);
        assertThat(right1.height).isEqualTo(2);
        assertThat(right2_left.parent).isEqualTo(right1);
        assertThat(right1.right).isEqualTo(right2_left);
    }
    
    @DisplayName("부모가 있을 때 rotate right test1")
    @Test
    void rotateRightTest1() {
        Node root = new Node(20);
        root.height = 4;
        
        Node right1 = new Node(30);
        root.right = right1;
        root.right.parent = root;
        
        Node left1 = new Node(20);
        left1.height = 3;
        
        Node left2 = new Node(40);
        left2.height = 2;
        
        Node left3 = new Node(50);
        
        left2.left = left3;
        left3.parent = left2;
        
        left1.left = left2;
        left2.parent = left1;
        
        root.left = left1;
        left1.parent = root;
        
        tree = new AvlTree(root);
        tree.rotateRight(left1);
        
        assertThat(root.height).isEqualTo(3);
        assertThat(root.left).isEqualTo(left2);
        assertThat(left2.parent).isEqualTo(root);
        assertThat(left1.parent).isEqualTo(left2);
        assertThat(left2.right).isEqualTo(left1);
        assertThat(left2.height).isEqualTo(2);
        assertThat(left1.height).isEqualTo(1);
    }
}