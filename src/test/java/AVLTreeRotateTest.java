import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AVLTreeRotateTest {
    static AVLTree tree;
    static IAVLTree.Node root;
    @BeforeEach
    void beforeEach() {
        tree = new AVLTree();
        root = new IAVLTree.Node(50);
    }
    @DisplayName("Rotate Test - LL")
    @Test
    void rotateLeft1() {
        IAVLTree.Node node1 = new IAVLTree.Node(15);
        node1.parent = root;
        root.leftChild = node1;
        IAVLTree.Node node2 = new IAVLTree.Node(10);
        node2.parent = node1;
        node1.leftChild = node2;
        IAVLTree.Node node3 = new IAVLTree.Node(5);
        node3.parent = node2;
        node2.leftChild = node3;

        tree.rotateRight(node1);

        assertThat(node2.leftChild).isEqualTo(node3);
        assertThat(node2.rightChild).isEqualTo(node1);
        assertThat(node2.parent).isEqualTo(root);
        assertThat(node1.leftChild).isEqualTo(null);
        assertThat(node1.rightChild).isEqualTo(null);
        assertThat(node1.parent).isEqualTo(node2);
        assertThat(node1.parent).isEqualTo(node2);
        assertThat(root.leftChild).isEqualTo(node2);
    }

    @DisplayName("Rotate Test - LR")
    @Test
    void rotateLeft2() {
        IAVLTree.Node node1 = new IAVLTree.Node(15);
        node1.parent = root;
        root.leftChild = node1;
        IAVLTree.Node node2 = new IAVLTree.Node(5);
        node2.parent = node1;
        node1.leftChild = node2;
        IAVLTree.Node node3 = new IAVLTree.Node(10);
        node3.parent = node2;
        node2.rightChild = node3;

        tree.rotateLeft(node2);
        tree.rotateRight(node1);

        assertThat(node3.leftChild).isEqualTo(node2);
        assertThat(node3.rightChild).isEqualTo(node1);
        assertThat(node3.parent).isEqualTo(root);
        assertThat(node1.leftChild).isEqualTo(null);
        assertThat(node1.rightChild).isEqualTo(null);
        assertThat(node1.parent).isEqualTo(node3);
        assertThat(node1.parent).isEqualTo(node3);
        assertThat(root.leftChild).isEqualTo(node3);
    }

    @DisplayName("Rotate Test - RR")
    @Test
    void rotateRight1() {
        IAVLTree.Node node1 = new IAVLTree.Node(70);
        node1.parent = root;
        root.rightChild = node1;
        IAVLTree.Node node2 = new IAVLTree.Node(80);
        node2.parent = node1;
        node1.rightChild = node2;
        IAVLTree.Node node3 = new IAVLTree.Node(90);
        node3.parent = node2;
        node2.rightChild = node3;

        tree.rotateLeft(node1);

        assertThat(node2.leftChild).isEqualTo(node1);
        assertThat(node2.rightChild).isEqualTo(node3);
        assertThat(node2.parent).isEqualTo(root);
        assertThat(node1.leftChild).isEqualTo(null);
        assertThat(node1.rightChild).isEqualTo(null);
        assertThat(node1.parent).isEqualTo(node2);
        assertThat(node1.parent).isEqualTo(node2);
        assertThat(root.rightChild).isEqualTo(node2);
    }

    @DisplayName("Rotate Test - RL")
    @Test
    void rotateRight2() {
        IAVLTree.Node node1 = new IAVLTree.Node(70);
        node1.parent = root;
        root.rightChild = node1;
        IAVLTree.Node node2 = new IAVLTree.Node(90);
        node2.parent = node1;
        node1.rightChild = node2;
        IAVLTree.Node node3 = new IAVLTree.Node(80);
        node3.parent = node2;
        node2.leftChild = node3;

        tree.rotateRight(node2);
        tree.rotateLeft(node1);

        assertThat(node3.leftChild).isEqualTo(node1);
        assertThat(node3.rightChild).isEqualTo(node2);
        assertThat(node3.parent).isEqualTo(root);
        assertThat(node1.leftChild).isEqualTo(null);
        assertThat(node1.rightChild).isEqualTo(null);
        assertThat(node1.parent).isEqualTo(node3);
        assertThat(node1.parent).isEqualTo(node3);
        assertThat(root.rightChild).isEqualTo(node3);
    }


}
