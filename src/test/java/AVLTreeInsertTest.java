import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
class AVLTreeInsertTest {
    static AvlTree tree;
    @BeforeEach
    void beforeEach() {
        tree = new AvlTree();
    }

    @DisplayName("Insert Test - size and height")
    @Test
    void insert1() {
        tree.insert(3);
        assertThat(tree.size()).isEqualTo(1);
        tree.insert(1);
        assertThat(tree.size()).isEqualTo(2);
        tree.insert(-51);
        assertThat(tree.size()).isEqualTo(3);
        tree.insert(3);
        assertThat(tree.size()).isEqualTo(3);

        assertThat(tree.find(3).height).isEqualTo(1);
        assertThat(tree.find(1).height).isEqualTo(2);
    }

    @DisplayName("Insert Test - LL")
    @Test
    void insert2() {
        tree.insert(10);
        assertThat(tree.find(10).left).isEqualTo(null);
        tree.insert(5);
        assertThat(tree.find(10).left.value).isEqualTo(5);
        tree.insert(1);
        assertThat(tree.find(10).left).isEqualTo(null);
        assertThat(tree.find(5).left.value).isEqualTo(1);
        assertThat(tree.find(5).right.value).isEqualTo(10);

        assertThat(tree.find(5).height).isEqualTo(2);
        assertThat(tree.find(1).height).isEqualTo(1);
        assertThat(tree.find(10).height).isEqualTo(1);
    }

    @DisplayName("Insert Test - LR")
    @Test
    void insert3() {
        tree.insert(10);
        tree.insert(5);
        tree.insert(7);
        assertThat(tree.find(10).left).isEqualTo(null);
        assertThat(tree.find(7).left.value).isEqualTo(5);
        assertThat(tree.find(7).right.value).isEqualTo(10);

        assertThat(tree.find(7).height).isEqualTo(2);
        assertThat(tree.find(5).height).isEqualTo(1);
        assertThat(tree.find(10).height).isEqualTo(1);
    }

    @DisplayName("Insert Test - RR")
    @Test
    void insert4() {
        tree.insert(1);
        tree.insert(5);
        tree.insert(7);
        assertThat(tree.find(1).right).isEqualTo(null);
        assertThat(tree.find(5).left.value).isEqualTo(1);
        assertThat(tree.find(5).right.value).isEqualTo(7);

        assertThat(tree.find(5).height).isEqualTo(2);
        assertThat(tree.find(1).height).isEqualTo(1);
        assertThat(tree.find(7).height).isEqualTo(1);
    }
    @DisplayName("Insert Test - RL")
    @Test
    void insert5() {
        tree.insert(1);
        tree.insert(5);
        tree.insert(3);
        assertThat(tree.find(1).right).isEqualTo(null);
        assertThat(tree.find(3).left.value).isEqualTo(1);
        assertThat(tree.find(3).right.value).isEqualTo(5);

        assertThat(tree.find(3).height).isEqualTo(2);
        assertThat(tree.find(1).height).isEqualTo(1);
        assertThat(tree.find(5).height).isEqualTo(1);
    }

    @DisplayName("Insert Test - complex")
    @Test
    void insert6() {
        tree.insert(15);
        tree.insert(4);
        tree.insert(20);
        tree.insert(2);
        tree.insert(3);
        tree.insert(10);
        tree.insert(30);
        tree.insert(25);
        tree.insert(50);
        tree.insert(18);
        assertThat(tree.find(15).left.value).isEqualTo(4);
        assertThat(tree.find(15).right.value).isEqualTo(25);
        assertThat(tree.find(25).left.value).isEqualTo(20);
        assertThat(tree.find(25).right.value).isEqualTo(30);
        assertThat(tree.find(30).left).isEqualTo(null);
        assertThat(tree.find(30).right.value).isEqualTo(50);

        assertThat(tree.find(15).height).isEqualTo(4);
        assertThat(tree.find(4).height).isEqualTo(3);
        assertThat(tree.find(25).height).isEqualTo(3);
        assertThat(tree.find(3).height).isEqualTo(2);
        assertThat(tree.find(10).height).isEqualTo(1);
        assertThat(tree.find(20).height).isEqualTo(2);
        assertThat(tree.find(30).height).isEqualTo(2);
        assertThat(tree.find(2).height).isEqualTo(1);
        assertThat(tree.find(18).height).isEqualTo(1);
        assertThat(tree.find(50).height).isEqualTo(1);
    }
}
