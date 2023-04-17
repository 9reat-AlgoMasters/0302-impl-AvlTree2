import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AVLTreeDeleteTest {
    static AvlTree tree;
    @BeforeEach
    void beforeEach() {
        tree = new AvlTree();
    }

    @DisplayName("Delete Test - size and height")
    @Test
    void delete1() {
        tree.insert(15);
        tree.insert(5);
        tree.insert(30);

        tree.delete(5);
        assertThat(tree.size()).isEqualTo(2);
        assertThat(tree.find(15).height).isEqualTo(2);
        tree.delete(5);
        assertThat(tree.size()).isEqualTo(2);
        tree.delete(15);
        assertThat(tree.size()).isEqualTo(1);
        assertThat(tree.find(30).height).isEqualTo(1);
    }

    @DisplayName("Delete Test - child 0")
    @Test
    void delete2() {
        tree.insert(15);
        tree.insert(10);
        tree.insert(30);
        tree.insert(5);

        tree.delete(30);

        assertThat(tree.find(15).rightChild).isEqualTo(null);
        assertThat(tree.find(10).leftChild.value).isEqualTo(5);
        assertThat(tree.find(10).rightChild.value).isEqualTo(15);

        assertThat(tree.find(10).height).isEqualTo(2);
        assertThat(tree.find(5).height).isEqualTo(1);
        assertThat(tree.find(15).height).isEqualTo(1);

        tree.delete(15);

        assertThat(tree.find(10).rightChild).isEqualTo(null);
        assertThat(tree.find(10).height).isEqualTo(2);
        assertThat(tree.find(5).height).isEqualTo(1);

        tree.delete(5);
        assertThat(tree.find(5)).isEqualTo(null);
        assertThat(tree.find(10).leftChild).isEqualTo(null);
        assertThat(tree.find(10).height).isEqualTo(1);
    }

    @DisplayName("Delete Test - child 1")
    @Test
    void delete3() {
        tree.insert(15);
        tree.insert(10);
        tree.insert(30);
        tree.insert(5);
        tree.insert(20);
        tree.insert(40);
        tree.insert(25);
        tree.insert(50);

        tree.delete(20);

        assertThat(tree.find(20)).isEqualTo(null);
        assertThat(tree.find(30).leftChild.value).isEqualTo(25);
        assertThat(tree.find(30).rightChild.value).isEqualTo(40);

        assertThat(tree.find(30).height).isEqualTo(3);
        assertThat(tree.find(25).height).isEqualTo(1);

        tree.delete(10);

        assertThat(tree.find(15).leftChild.value).isEqualTo(5);
        assertThat(tree.find(15).rightChild.value).isEqualTo(25);
        assertThat(tree.find(30).leftChild.value).isEqualTo(15);
        assertThat(tree.find(30).rightChild.value).isEqualTo(40);

        assertThat(tree.find(30).height).isEqualTo(3);
        assertThat(tree.find(15).height).isEqualTo(2);
        assertThat(tree.find(40).height).isEqualTo(2);
        assertThat(tree.find(25).height).isEqualTo(1);
        assertThat(tree.find(5).height).isEqualTo(1);
        assertThat(tree.find(50).height).isEqualTo(1);
    }

    @DisplayName("Delete Test - child 1 : root")
    @Test
    void delete4() {
        tree.insert(15);
        tree.insert(10);

        tree.delete(15);

        assertThat(tree.find(15)).isEqualTo(null);
        assertThat(tree.find(10).height).isEqualTo(1);
    }

    @DisplayName("Delete Test - child 2")
    @Test
    void delete5() {
        tree.insert(15);
        tree.insert(10);
        tree.insert(30);
        tree.insert(5);
        tree.insert(12);
        tree.insert(25);
        tree.insert(50);
        tree.insert(3);
        tree.insert(7);
        tree.insert(27);
        tree.insert(13);
        tree.insert(8);

        tree.delete(5);

        assertThat(tree.find(7).leftChild.value).isEqualTo(3);
        assertThat(tree.find(7).rightChild.value).isEqualTo(8);

        assertThat(tree.find(10).height).isEqualTo(3);
        assertThat(tree.find(7).height).isEqualTo(2);
        assertThat(tree.find(3).height).isEqualTo(1);

        tree.delete(15);
        assertThat(tree.find(25).leftChild.value).isEqualTo(10);
        assertThat(tree.find(25).rightChild.value).isEqualTo(30);
        assertThat(tree.find(30).leftChild.value).isEqualTo(27);

        assertThat(tree.find(25).height).isEqualTo(4);
        assertThat(tree.find(30).height).isEqualTo(2);

        tree.delete(25);
        assertThat(tree.find(27).leftChild.value).isEqualTo(10);
        assertThat(tree.find(27).rightChild.value).isEqualTo(30);
        assertThat(tree.find(30).leftChild).isEqualTo(null);

        assertThat(tree.find(27).height).isEqualTo(4);
        assertThat(tree.find(30).height).isEqualTo(2);
    }

    @DisplayName("Delete Test - complex")
    @Test
    void delete6() {
        tree.insert(15);
        tree.insert(10);
        tree.insert(30);
        tree.insert(5);
        tree.insert(12);
        tree.insert(25);
        tree.insert(50);
        tree.insert(7);
        tree.insert(27);
        tree.insert(11);
        tree.insert(13);
        tree.insert(14);

        tree.delete(15);

        assertThat(tree.find(25).leftChild.value).isEqualTo(13);
        assertThat(tree.find(25).rightChild.value).isEqualTo(30);
        assertThat(tree.find(12).leftChild.value).isEqualTo(10);
        assertThat(tree.find(12).rightChild.value).isEqualTo(25);

        assertThat(tree.find(12).height).isEqualTo(4);
        assertThat(tree.find(25).height).isEqualTo(3);

        tree.delete(13);
        tree.delete(14);
        assertThat(tree.find(25).leftChild).isEqualTo(null);
        assertThat(tree.find(25).rightChild.value).isEqualTo(27);
        assertThat(tree.find(30).leftChild.value).isEqualTo(25);

        assertThat(tree.find(12).height).isEqualTo(4);
        assertThat(tree.find(30).height).isEqualTo(3);
        assertThat(tree.find(25).height).isEqualTo(2);
        assertThat(tree.find(27).height).isEqualTo(1);
    }
}
