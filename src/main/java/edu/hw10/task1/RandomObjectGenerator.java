package edu.hw10.task1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomObjectGenerator {
    private RandomObjectGenerator() {
    }

    public static Object nextObject(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getConstructors();
        Constructor<?> randomConstructor = constructors[ThreadLocalRandom.current().nextInt(constructors.length)];
        try {
            return randomConstructor.newInstance(generateArguments(randomConstructor.getParameters()));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object nextObject(Class<?> clazz, String methodName) {
        Method[] methods = clazz.getDeclaredMethods();
        Method method = Arrays.stream(methods)
            .filter(m -> m.getName().equals(methodName))
            .findAny().orElseThrow();

        try {
            return method.invoke(clazz, generateArguments(method.getParameters()));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static Object[] generateArguments(Parameter[] parameters) {
        List<Object> result = new ArrayList<>();
        for (Parameter parameter : parameters) {
            Annotation[] annotations = parameter.getAnnotations();
            result.add(TypesGenerator.generateOne(parameter.getType(), annotations));
        }
        return result.toArray();
    }
}
