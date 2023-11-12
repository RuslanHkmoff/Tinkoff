package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @Test
    @DisplayName("test numberPlateValidation")
    void test() {
      String[][] tests = new String[][]{
          {"achfdbaabgabcaabg", "abc"},
          {"a", "a"},
          {"abcd", "efg"},
          {"afaafa", null},
          {null, "abc"}
      };
        boolean[] expected = new boolean[] {true, true, false, false, false};
        for (int i = 0; i < tests.length; i++) {
            assertThat(Task6.isSubsequence(tests[i][0], tests[i][1])).isEqualTo(expected[i]);
        }

    }
}
