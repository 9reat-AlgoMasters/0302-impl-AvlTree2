import exceptions.CustomDuplicatedElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class AvlTreeInsertionTest {

    AvlTree avl = new AvlTree();

    @BeforeEach
    public void init(){
        avl.insert(35);
        avl.insert(20);
        avl.insert(19);
        avl.insert(47);
        avl.insert(48);
        avl.insert(38);
    }


    @DisplayName("삽입 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {35, 20 ,19, 47, 48, 38})
    public void testInsertion(int val){
        assertThat(avl.contains(val)).isTrue();
    }

    @DisplayName("삽입 확인 테스트2 (없는 값 조회)")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    public void check_없는_값_조회(int val){
        assertThat(avl.contains(val)).isFalse();
    }

    @Test
    @DisplayName("삽입 완료 후 균형확인")
    public void check_균형확인(){
        assertThat(avl.root.left.height - avl.root.right.height).isIn(-1, 0, 1);
    }
    
    @Test
    @DisplayName("삽입 위치 확인(rotate확인)")
    public void check_삽입위치_확인(){
        assertThat(avl.root.value).isEqualTo(35);
        assertThat(avl.root.left.value).isEqualTo(20);
        assertThat(avl.root.right.value).isEqualTo(47);
        assertThat(avl.root.left.left.value).isEqualTo(19);
        assertThat(avl.root.right.left.value).isEqualTo(38);
        assertThat(avl.root.right.right.value).isEqualTo(48);
    }

    @Test
    @DisplayName("size 확인")
    public void check_size(){
        assertThat(avl.size).isEqualTo(6);
    }

    @Test
    @DisplayName("중복값 삽입")
    public void check_중복값_삽입(){
        assertThatThrownBy(()->avl.insert(35)).isInstanceOf(CustomDuplicatedElementException.class);
    }

}
