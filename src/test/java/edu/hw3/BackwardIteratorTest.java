package edu.hw3;

import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BackwardIteratorTest {

    @Test
    @DisplayName("Test next")
    void test1() {
        List<String> list = List.of("a", "b", "c");
        String[] expected = new String[] {"c", "b", "a"};
        BackwardIterator<String> iterator = new BackwardIterator<>(list);
        for (int i = 0; i < 3; i++) {
            assertThat(iterator.next()).isEqualTo(expected[i]);
        }
    }

    @Test
    @DisplayName("Test hasNext and next")
    void test2() {
        List<String> list = List.of("a", "b", "c");
        BackwardIterator<String> iterator = new BackwardIterator<>(list);
        String[] expected = new String[] {"c", "b", "a"};
        assertTrue(iterator.hasNext());
        for (int i = 0; i < 3; i++) {
            assertThat(iterator.next()).isEqualTo(expected[i]);
        }
        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("Test NoSuchElement")
    void test3() {
        List<String> list = List.of();
        BackwardIterator<String> iterator = new BackwardIterator<>(list);
        String expected = "No such element";
        NoSuchElementException thrown = assertThrows(
            NoSuchElementException.class,
            iterator::next,
            "Expected NoSuchElementException, but didn't"
        );
        assertTrue(expected.contains(thrown.getMessage()));
    }

    @Test
    @DisplayName("Test remove")
    void test4() {
        List<String> list = List.of("a", "b", "c");
        BackwardIterator<String> iterator = new BackwardIterator<>(list);
        String expected = "Operation is not supported";
        UnsupportedOperationException thrown = assertThrows(
            UnsupportedOperationException.class,
            iterator::remove,
            "Expected UnsupportedOperationException, but didn't"
        );
        assertTrue(expected.contains(thrown.getMessage()));
    }

}
