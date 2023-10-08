package edu.hw1;

public class Task3 {
    private static final int MIN = Integer.MIN_VALUE;
    private static final int MAX = Integer.MAX_VALUE;
    private Task3() {
    }

    public static boolean isNestable(int[] a1, int[] a2) {
        if (a1 == null || a2 == null) {
            throw new IllegalArgumentException("Expected not null value");
        }
        int min1 = getMin(a1);
        int min2 = getMin(a2);
        int max1 = getMax(a1);
        int max2 = getMax(a2);
        return min1 > min2 && max1 < max2;
    }

    private static int getMin(int[] array) {
        int result = MAX;
        for (int element : array){
            result  = Math.min(result, element);
        }
        return result;
    }

    private static int getMax(int[] array) {
        int result = MIN;
        for (int element : array){
            result  = Math.max(result, element);
        }
        return result;
    }
}
