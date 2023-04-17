import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AVLTreeETCTest {
    static AvlTree tree;
    @BeforeEach
    void beforeEach() {
        tree = new AvlTree();
    }

    @DisplayName("Empty Test")
    @Test
    void isEmpty() {
        assertThat(tree.isEmpty()).isEqualTo(true);
        tree.insert(1);
        assertThat(tree.isEmpty()).isEqualTo(false);
        tree.delete(1);
        assertThat(tree.isEmpty()).isEqualTo(true);
    }

    @DisplayName("Contains Test")
    @Test
    void contains() {
        assertThat(tree.contains(15)).isEqualTo(false);
        tree.insert(15);
        assertThat(tree.contains(15)).isEqualTo(true);
        assertThat(tree.contains(1555)).isEqualTo(false);
        tree.insert(1555);
        assertThat(tree.contains(1555)).isEqualTo(true);

        tree.delete(15);
        assertThat(tree.contains(1555)).isEqualTo(true);
        tree.delete(1555);
        assertThat(tree.contains(1555)).isEqualTo(false);
    }
}
