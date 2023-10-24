package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BackwardIteratorTest {
    @Test
    @DisplayName("Test hasNext")
    void tes1() {
        List<String> list = List.of("a", "b", "c");
        BackwardIterator<String> iterator = new BackwardIterator<>(list);
        assertTrue(iterator.hasNext());
        iterator.next();
        iterator.next();
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("Test next")
    void test2() {
        List<String> list = List.of("a", "b", "c");
        BackwardIterator<String> iterator = new BackwardIterator<>(list);
        assertThat(iterator.next()).isEqualTo("c");
    }
    @Test
    @DisplayName("Test remove")
    void test3() {
        List<String> list = List.of("a", "b", "c");
        BackwardIterator<String> iterator = new BackwardIterator<>(list);
       UnsupportedOperationException thrown = assertThrows(UnsupportedOperationException.class,
           iterator::remove,
           "Expected UnsupportedOperationException, but didn't"
       );
    }

}
