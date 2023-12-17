package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("test, hello ByteBuddy")
    void injectToString()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String expected = "Hello, ByteBuddy!";
        Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(named("toString")).intercept(FixedValue.value(expected))
            .make()
            .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();
        assertThat(dynamicType.getDeclaredConstructor()
            .newInstance()
            .toString()).isEqualTo(expected);

    }
}
