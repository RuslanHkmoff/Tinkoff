package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TypesGenerator {
    private static final Random RANDOM = ThreadLocalRandom.current();
    private static final int START_LETTER = 97;
    private static final int END_LETTER = 122;
    private static final int MAX_LEN = 100;
    private static final String ERROR_MESSAGE = "Unexpected annotation ";

    private TypesGenerator() {

    }

    public static Object generateOne(Class<?> clazz, Annotation[] annotations) {
        if (clazz == Integer.class || clazz == int.class) {
            return generateInteger(annotations);
        } else if (clazz == String.class) {
            return generateString(annotations);
        }
        throw new IllegalArgumentException("Can't generate: " + clazz.getName());
    }

    private static String generateString(Annotation[] annotations) {
        int length = RANDOM.nextInt(1, MAX_LEN);
        for (Annotation annotation : annotations) {
            if (annotation instanceof NotNull) {
                return RANDOM.ints(START_LETTER, END_LETTER + 1)
                    .limit(length)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            } else {
                throw new IllegalArgumentException(ERROR_MESSAGE + annotation.toString());
            }
        }
        return null;
    }

    private static Integer generateInteger(Annotation[] annotations) {
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min cast) {
                min = cast.value();
            } else if (annotation instanceof Max cast) {
                max = cast.value();
            } else {
                throw new IllegalArgumentException(ERROR_MESSAGE + annotation.toString());
            }
        }
        return RANDOM.nextInt(min, max);
    }
}

