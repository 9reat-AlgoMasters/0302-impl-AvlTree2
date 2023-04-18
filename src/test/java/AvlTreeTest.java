import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class AvlTreeTest {
    static AvlTree avlt;

    @BeforeEach
    void beforeEach() {
        avlt = new AvlTree();
        avlt.insert(5);
        avlt.insert(10);
        avlt.insert(3);
        avlt.insert(22);
        avlt.insert(20);
        avlt.insert(30);
        avlt.insert(45);
        avlt.insert(100);
    }

    @DisplayName("isEmpty() 테스트 - True")
    @Test
    void isEmptyTest1() {
        AvlTree emptyTree = new AvlTree();
        assertThat(emptyTree.isEmpty()).isTrue();
    }

    @DisplayName("isEmpty() 테스트 - False")
    @Test
    void isEmptyTest2() {
        assertThat(avlt.isEmpty()).isFalse();
    }

    @DisplayName("insert 테스트")
    @Test
    void insertTest() {
        AvlTree insertTree = new AvlTree();
        insertTree.insert(10);
        assertThat(insertTree.size()).isEqualTo(1);
        insertTree.insert(45);
        assertThat(insertTree.size()).isEqualTo(2);
        insertTree.insert(30);
        assertThat(insertTree.size()).isEqualTo(3);
        insertTree.insert(-5);
        assertThat(insertTree.size()).isEqualTo(4);
        insertTree.insert(6);
        assertThat(insertTree.size()).isEqualTo(5);
    }

    @DisplayName("delete 테스트") // 5 10 3 22 20
    @Test
    void deleteTest() {
        avlt.delete(5);
        assertThat(avlt.size()).isEqualTo(7);
        avlt.delete(10);
        assertThat(avlt.size()).isEqualTo(6);
        avlt.delete(20);
        assertThat(avlt.size()).isEqualTo(5);
        avlt.delete(22);
        assertThat(avlt.size()).isEqualTo(4);
        avlt.delete(3);
        assertThat(avlt.size()).isEqualTo(3);
        avlt.delete(45);
        assertThat(avlt.size()).isEqualTo(2);
        avlt.delete(30);
        assertThat(avlt.size()).isEqualTo(1);
        avlt.delete(100);
        assertThat(avlt.size()).isEqualTo(0);
    }

    @DisplayName("contains 테스트 - False")
    @Test
    void containsTest1() {
        assertThat(avlt.contains(28)).isFalse();
        assertThat(avlt.contains(33)).isFalse();
    }

    @DisplayName("contains 테스트 - True")
    @Test
    void containsTest2() {
        assertThat(avlt.contains(5)).isTrue();
        assertThat(avlt.contains(22)).isTrue();
    }

    @DisplayName("root 테스트1") // 5 10 3 22 20
    @Test
    void rootTest1() {
        AvlTree rootTestAvlTree = new AvlTree();
        rootTestAvlTree.insert(10);
        assertThat(rootTestAvlTree.root.value).isEqualTo(10);
        rootTestAvlTree.insert(30);
        assertThat(rootTestAvlTree.root.value).isEqualTo(10);
        rootTestAvlTree.insert(45);
        assertThat(rootTestAvlTree.root.value).isEqualTo(30);
        rootTestAvlTree.insert(55);
        assertThat(rootTestAvlTree.root.value).isEqualTo(30);
        rootTestAvlTree.insert(65);
        assertThat(rootTestAvlTree.root.value).isEqualTo(30);
    }

    @DisplayName("root 테스트2") // 5 10 3 22 20
    @Test
    void rootTest2() {
        AvlTree rootTestAvlTree = new AvlTree();
        rootTestAvlTree.insert(5);
        assertThat(rootTestAvlTree.root.value).isEqualTo(5);
        rootTestAvlTree.insert(3);
        assertThat(rootTestAvlTree.root.value).isEqualTo(5);
        rootTestAvlTree.insert(10);
        assertThat(rootTestAvlTree.root.value).isEqualTo(5);
        rootTestAvlTree.insert(22);
        assertThat(rootTestAvlTree.root.value).isEqualTo(5);
        rootTestAvlTree.insert(20);
        assertThat(rootTestAvlTree.root.value).isEqualTo(5);
        rootTestAvlTree.insert(30);
        assertThat(rootTestAvlTree.root.value).isEqualTo(20);
        rootTestAvlTree.insert(50);
        assertThat(rootTestAvlTree.root.value).isEqualTo(20);
        rootTestAvlTree.insert(60);
        assertThat(rootTestAvlTree.root.value).isEqualTo(20);
        rootTestAvlTree.insert(67);
        assertThat(rootTestAvlTree.root.value).isEqualTo(20);
        rootTestAvlTree.insert(70);
        assertThat(rootTestAvlTree.root.value).isEqualTo(20);
        rootTestAvlTree.insert(80);
        assertThat(rootTestAvlTree.root.value).isEqualTo(20);
    }

    @DisplayName("Right rotate 테스트")
    @Test
    void rightTest() {

    }
}