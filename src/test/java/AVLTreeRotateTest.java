import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AVLTreeRotateTest {
    static AvlTree tree;
    static Node root;
    @BeforeEach
    void beforeEach() {
        tree = new AvlTree();
        root = new Node(50);
    }
    @DisplayName("Rotate Test - LL")
    @Test
    void rotateLeft1() {
        Node node1 = new Node(15);
        node1.parent = root;
        root.left = node1;
        Node node2 = new Node(10);
        node2.parent = node1;
        node1.left = node2;
        Node node3 = new Node(5);
        node3.parent = node2;
        node2.left = node3;

        tree.rotateRight(node1);

        assertThat(node2.left).isEqualTo(node3);
        assertThat(node2.right).isEqualTo(node1);
        assertThat(node2.parent).isEqualTo(root);
        assertThat(node1.left).isEqualTo(null);
        assertThat(node1.right).isEqualTo(null);
        assertThat(node1.parent).isEqualTo(node2);
        assertThat(node1.parent).isEqualTo(node2);
        assertThat(root.left).isEqualTo(node2);
    }

    @DisplayName("Rotate Test - LR")
    @Test
    void rotateLeft2() {
        Node node1 = new Node(15);
        node1.parent = root;
        root.left = node1;
        Node node2 = new Node(5);
        node2.parent = node1;
        node1.left = node2;
        Node node3 = new Node(10);
        node3.parent = node2;
        node2.right = node3;

        tree.rotateLeft(node2);
        tree.rotateRight(node1);

        assertThat(node3.left).isEqualTo(node2);
        assertThat(node3.right).isEqualTo(node1);
        assertThat(node3.parent).isEqualTo(root);
        assertThat(node1.left).isEqualTo(null);
        assertThat(node1.right).isEqualTo(null);
        assertThat(node1.parent).isEqualTo(node3);
        assertThat(node1.parent).isEqualTo(node3);
        assertThat(root.left).isEqualTo(node3);
    }

    @DisplayName("Rotate Test - RR")
    @Test
    void rotateRight1() {
        Node node1 = new Node(70);
        node1.parent = root;
        root.right = node1;
        Node node2 = new Node(80);
        node2.parent = node1;
        node1.right = node2;
        Node node3 = new Node(90);
        node3.parent = node2;
        node2.right = node3;

        tree.rotateLeft(node1);

        assertThat(node2.left).isEqualTo(node1);
        assertThat(node2.right).isEqualTo(node3);
        assertThat(node2.parent).isEqualTo(root);
        assertThat(node1.left).isEqualTo(null);
        assertThat(node1.right).isEqualTo(null);
        assertThat(node1.parent).isEqualTo(node2);
        assertThat(node1.parent).isEqualTo(node2);
        assertThat(root.right).isEqualTo(node2);
    }

    @DisplayName("Rotate Test - RL")
    @Test
    void rotateRight2() {
        Node node1 = new Node(70);
        node1.parent = root;
        root.right = node1;
        Node node2 = new Node(90);
        node2.parent = node1;
        node1.right = node2;
        Node node3 = new Node(80);
        node3.parent = node2;
        node2.left = node3;

        tree.rotateRight(node2);
        tree.rotateLeft(node1);

        assertThat(node3.left).isEqualTo(node1);
        assertThat(node3.right).isEqualTo(node2);
        assertThat(node3.parent).isEqualTo(root);
        assertThat(node1.left).isEqualTo(null);
        assertThat(node1.right).isEqualTo(null);
        assertThat(node1.parent).isEqualTo(node3);
        assertThat(node1.parent).isEqualTo(node3);
        assertThat(root.right).isEqualTo(node3);
    }

}
