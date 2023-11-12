package edu.hw2;

import edu.hw2.Task4.CallingInfo;
import edu.hw2.Task4.CallingInfoUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Testing callingInfo")
    void test() {
        CallingInfo info = CallingInfoUtils.callingInfo();
        String expectedClassName = "edu.hw2.Task4Test";
        String expectedMethodName = "test";
        assertThat(info.className()).isEqualTo(expectedClassName);
        assertThat(info.methodName()).isEqualTo(expectedMethodName);
    }
}
