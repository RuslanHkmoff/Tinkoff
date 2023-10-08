package edu.hw1;

public class Task7 {
    private Task7() {
    }

    public int rotateLeft(int number, int shift) {
        int copy = Math.abs(number);
        int sign = number < 0 ? -1 : 1;
        int len = Integer.toBinaryString(number).length();
        int bound = (int) Math.pow(2, len - 1);
        for (int i = 0; i < shift; ++i) {
            int digit = copy / bound == 0 ? 0 : 1;
            copy <<= 1;
            copy -= (bound << 1) * digit;
            copy += digit;
        }
        return copy * sign;
    }

    public int rotateRight(int number, int shift) {
        int copy = Math.abs(number);
        int sign = number < 0 ? -1 : 1;
        int len = Integer.toBinaryString(number).length();
        int bound = (int) Math.pow(2, len - 1);
        for (int i = 0; i < shift; ++i) {
            int digit = copy % 2;
            copy >>= 1;
            copy += bound * digit;
        }
        return copy * sign;
    }
}
