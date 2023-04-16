import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AvlTreeInsertTest {
    static AvlTree tree;

    @DisplayName("RR insert 테스트")
    @Test
    void insertTest1() {
        int[] insertList = {10, 5, 20, 15, 40, 50};
        int[] expectedHeight = {2, 1, 3, 1, 2, 1};
        tree = new AvlTree(10);
        for (int i=1; i<insertList.length; i++) {
            tree.insert(insertList[i]);
        }

        for (int i = 0; i < insertList.length; i++) {
            assertThat(tree.findHeightByValue(insertList[i]))
                    .isEqualTo(expectedHeight[i]);
        }
    }

    @DisplayName("LL insert 테스트")
    @Test
    void insertTest2() {
        int[] insertList = {15, 10, 20, 5, 13, 9};
        int[] expectedHeight = {2, 3, 1, 2, 1, 1};
        tree = new AvlTree(15);
        for (int i=1; i<insertList.length; i++) {
            tree.insert(insertList[i]);
        }

        for (int i = 0; i < insertList.length; i++) {
            assertThat(tree.findHeightByValue(insertList[i]))
                    .isEqualTo(expectedHeight[i]);
        }
    }

    @DisplayName("LR insert 테스트")
    @Test
    void insertTest3() {
        int[] insertList = {30, 20, 40, 10, 25, 23, 5, 15};
        int[] expectedHeight = {2, 3, 1, 2, 4, 1, 1, 1};
        tree = new AvlTree(30);
        for (int i=1; i<insertList.length; i++) {
            tree.insert(insertList[i]);
        }

        for (int i = 0; i < insertList.length; i++) {
            assertThat(tree.findHeightByValue(insertList[i]))
                    .isEqualTo(expectedHeight[i]);
        }
    }

    @DisplayName("RL insert 테스트")
    @Test
    void insertTest4() {
        int[] insertList = {20, 10, 40, 30, 60, 35};
        int[] expectedHeight = {2, 1, 2, 3, 1, 1};
        tree = new AvlTree(20);
        for (int i=1; i<insertList.length; i++) {
            tree.insert(insertList[i]);
        }

        for (int i = 0; i < insertList.length; i++) {
            assertThat(tree.findHeightByValue(insertList[i]))
                    .isEqualTo(expectedHeight[i]);
        }
    }

    @DisplayName("root가 아닌 RL insert 테스트")
    @Test
    void insertTest5() {
        int[] insertList = {30, 15, 60, 10, 25, 45, 20, 28, 17};
        int[] expectedHeight = {4, 2, 2, 1, 2, 1, 3, 1, 1};
        tree = new AvlTree(30);
        for (int i=1; i<insertList.length; i++) {
            tree.insert(insertList[i]);
        }

        for (int i = 0; i < insertList.length; i++) {
            assertThat(tree.findHeightByValue(insertList[i]))
                    .isEqualTo(expectedHeight[i]);
        }
    }

}