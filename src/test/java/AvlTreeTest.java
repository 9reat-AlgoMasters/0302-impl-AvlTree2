import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AvlTreeTest {
    static AvlTree tree;

    @BeforeEach
    void beforeEach() {
        int[] insertList = {30, 15, 60, 10, 25, 45, 20, 28, 17};
        tree = new AvlTree(30);
        for (int i=1; i<insertList.length; i++) {
            tree.insert(insertList[i]);
        }
    }

    @DisplayName("contains - true Test")
    @ParameterizedTest(name = "test {index}")
    @ValueSource(ints={30, 15, 60, 10, 25, 45, 20, 28, 17})
    void containsTrueTest(int value) {
        assertThat(tree.contains(value)).isTrue();
    }

    @DisplayName("contains - false Test")
    @ParameterizedTest(name = "test {index}")
    @ValueSource(ints={-1, 0,  1, 100, 26, 31, 99, -50})
    void containsFalseTest(int value) {
        assertThat(tree.contains(value)).isFalse();
    }

}