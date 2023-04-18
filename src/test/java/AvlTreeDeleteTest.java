import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AvlTreeDeleteTest {
    static AvlTree tree;

    @DisplayName("자식이 없는 경우 delete 테스트")
    @Test
    void deleteNodeWithZeroChildTest() {
        int[] insertList = {30, 15, 40, 10, 20};
        int[] expectedHeight = {2, 3, 10000, 1, 1};
        tree = new AvlTree(30);
        for (int i=1; i<insertList.length; i++) {
            tree.insert(insertList[i]);
        }

        tree.delete(40);

        for (int i = 0; i < insertList.length; i++) {
            if (insertList[i] == 40) continue;
            assertThat(tree.findHeightByValue(insertList[i]))
                    .isEqualTo(expectedHeight[i]);
        }
    }

    @DisplayName("자식이 하나 있는 경우 delete 테스트")
    @Test
    void deleteNodeWithOneChildTest1() {
        int[] insertList = {30, 15, 40, 50};
        int[] expectedHeight = {2, 1, 1000, 1};
        tree = new AvlTree(30);
        for (int i=1; i<insertList.length; i++) {
            tree.insert(insertList[i]);
        }

        tree.delete(40);

        for (int i = 0; i < insertList.length; i++) {
            if (insertList[i] == 40) continue;
            assertThat(tree.findHeightByValue(insertList[i]))
                    .isEqualTo(expectedHeight[i]);
        }
    }

    @DisplayName("자식이 하나 있고 delete 이후 rotate 일어날 경우 test")
    @Test
    void deleteNodeWithOneChildTest2() {
        int[] insertList = {30, 15, 40, 50, 7, 20, 17};
        int[] expectedHeight = {2, 2, 1000, 1, 1, 3, 1};
        tree = new AvlTree(30);
        for (int i=1; i<insertList.length; i++) {
            tree.insert(insertList[i]);
        }

        tree.delete(40);

        for (int i = 0; i < insertList.length; i++) {
            if (insertList[i] == 40) continue;
            assertThat(tree.findHeightByValue(insertList[i]))
                    .isEqualTo(expectedHeight[i]);
        }
    }

    @DisplayName("자식이 모두 있는 경우 delete 테스트")
    @Test
    void deleteNodeWithTwoChildTest1() {
        int[] insertList = {30, 15, 40, 35, 50};
        int[] expectedHeight = {3, 1, 10000, 2, 1};
        tree = new AvlTree(30);
        for (int i=1; i<insertList.length; i++) {
            tree.insert(insertList[i]);
        }

        tree.delete(40);

        for (int i = 0; i < insertList.length; i++) {
            if (insertList[i] == 40) continue;
            assertThat(tree.findHeightByValue(insertList[i]))
                    .isEqualTo(expectedHeight[i]);
        }
    }

    @DisplayName("자식이 모두 있고 delete 이후 rotate 일어날 경우 test")
    @Test
    void deleteNodeWithTwoChildTest2() {
        int[] insertList = {30, 20, 40, 10, 25, 35, 60, 5, 15, 23, 32, 39, 50, 80, 13, 37, 45, 55, 70, 48};
        int[] expectedHeight = {5, 4, 10000, 3, 2, 2, 3, 1, 2, 1, 1, 3, 4, 2, 1, 1, 2, 1, 1, 1};
        tree = new AvlTree(30);
        for (int i=1; i<insertList.length; i++) {
            tree.insert(insertList[i]);
        }

//        System.out.println("================ delete ==================\n");
        tree.delete(40);

        for (int i = 0; i < insertList.length; i++) {
            if (insertList[i] == 40) continue;
//            System.out.printf("node : %d ----> height : %d, expectedHeight : %d\n", insertList[i], tree.findHeightByValue(insertList[i]), expectedHeight[i]);
            assertThat(tree.findHeightByValue(insertList[i]))
                    .isEqualTo(expectedHeight[i]);
        }
    }

    @DisplayName("delete 시 size 테스트")
    @Test
    void sizeAfterDeleteTest() {
        int[] insertList = {30, 20, 40, 10, 25, 35, 60, 5, 15, 23, 32, 39, 50, 80, 13, 37, 45, 55, 70, 48};
        tree = new AvlTree(30);
        for (int i=1; i<insertList.length; i++) {
            tree.insert(insertList[i]);
        }

        tree.delete(40);

        assertThat(tree.size()).isEqualTo(insertList.length - 1);
    }
}
