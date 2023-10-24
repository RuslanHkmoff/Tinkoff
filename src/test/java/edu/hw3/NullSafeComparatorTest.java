package edu.hw3;

import java.util.TreeMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NullSafeComparatorTest {
    @Test
    @DisplayName("Test null key")
    void test() {
        TreeMap<String, Integer> tree = new TreeMap<>(new NullSafeComparator<>());
        tree.put(null, 1);
        assertThat(tree.containsKey(null)).isTrue();
    }
}
