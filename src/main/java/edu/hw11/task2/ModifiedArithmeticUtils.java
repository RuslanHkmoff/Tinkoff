package edu.hw11.task2;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

@SuppressWarnings("HideUtilityClassConstructor")
public class ModifiedArithmeticUtils {
    @RuntimeType
    public static int intercept(@AllArguments Object[] args) {
        int a = (int) args[0];
        int b = (int) args[1];
        return a * b;
    }
}
