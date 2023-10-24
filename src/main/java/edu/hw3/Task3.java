package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 {
    private Task3() {
    }

    public static <T> Map<T, Integer> freqDict(List<T> objects) {
        Map<T, Integer> result = new HashMap<>();
        for (T element : objects) {
            result.put(element, result.getOrDefault(element, 0) + 1);
        }
        return result;
    }
}
