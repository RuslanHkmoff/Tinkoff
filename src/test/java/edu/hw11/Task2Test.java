package edu.hw11;

import edu.hw11.task2.ArithmeticUtils;
import edu.hw11.task2.ModifiedArithmeticUtils;
import java.lang.reflect.InvocationTargetException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("test modify class")
    void test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException,
        InstantiationException {
        ArithmeticUtils arithmeticUtils = replaceClass(ArithmeticUtils.class, "sum", ModifiedArithmeticUtils.class);
        int result = arithmeticUtils.sum(30, 4);
        assertThat(result).isEqualTo(120);
    }

    public <T> T replaceClass(Class<T> clazz, String methodName, Class<?> replace)
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> dynamicType = new ByteBuddy()
            .subclass(clazz)
            .method(named(methodName))
            .intercept(MethodDelegation.to(replace))
            .make().load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();
        return (T) dynamicType.getDeclaredConstructor().newInstance();
    }
}
