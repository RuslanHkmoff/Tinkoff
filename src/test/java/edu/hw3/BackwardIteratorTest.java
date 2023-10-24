package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class BackwardIteratorTest {
    @Test
    @DisplayName("Test hasNext")
    void tes1() {
        List<String> list = List.of("a", "b", "c");
        BackwardIterator<String> iterator = new BackwardIterator<>(list);
        assertTrue(iterator.hasNext());
        for (int i = 0; i < 3; i++) {
            iterator.next();
        }
        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("Test next")
    void test2() {
        List<String> list = List.of("a", "b", "c");
        BackwardIterator<String> iterator = new BackwardIterator<>(list);
        assertThat(iterator.next()).isEqualTo("c");
        for (int i = 0; i < 2; i++) {
            iterator.next();
        }
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
    void test3() {
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
