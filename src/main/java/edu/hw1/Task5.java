package edu.hw1;

public class Task5 {
    private static final int TEN = 10;

    private Task5() {
    }

    public boolean isPalindromeDescendant(int number) {
        if (number < TEN) {
            return false;
        }
        String numberStr = Integer.toString(number);
        if (isPalindrome(numberStr)) {
            return true;
        }
        if (numberStr.length() % 2 != 0) {
            return false;
        }
        int child = getChild(numberStr);
        return isPalindromeDescendant(child);
    }

    private int getChild(String number) {
        int child = 0;
        for (int i = 0; i < number.length() - 1; i += 2) {
            int digit1 = Character.getNumericValue(number.charAt(i));
            int digit2 = Character.getNumericValue(number.charAt(i + 1));
            int sum = digit1 + digit2;
            child = child * TEN + sum;
        }
        return child;
    }

    private boolean isPalindrome(String number) {
        int left = 0;
        int right = number.length() - 1;
        while (left < right) {
            if (number.charAt(left) != number.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
