import exceptions.CustomDuplicatedElementException;
import exceptions.CustomNoSuchElementException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class AvlTreeDeleteTest {

    AvlTree avl = new AvlTree();

    List<Integer> list = new ArrayList<>();


    @BeforeEach
    public void init(){
        avl.insert(35);
        avl.insert(20);
        avl.insert(19);
        avl.insert(47);
        avl.insert(48);
        avl.insert(38);
        list.add(35);
        list.add(20);
        list.add(19);
        list.add(47);
        list.add(48);
        list.add(38);
    }

    @Test
    @DisplayName("삭제 확인 테스트")
    public void testDeleteAll(){
        for(int i = 0; i < 6;i++){
            avl.delete(list.get(i));
//            System.out.println("삭제되는 값: " + list.get(i));
//            System.out.println(avl.root.value);
//            System.out.println(avl.root.left.value);
//            System.out.println(avl.root.right.value);
//            System.out.println(avl.root.right.right.value);
        }
//        System.out.println(avl.root.value);
//        System.out.println(avl.root.left.value);
//        System.out.println(avl.root.right.value);
        assertThat(avl.size()).isEqualTo(0);

    }

    @Test
    @DisplayName("존재하지 않는 값 삭제")
    public void test_존재하지_않는_값_삭제확인(){
        assertThatThrownBy(()->avl.delete(1)).isInstanceOf(CustomNoSuchElementException.class);

    }

}
