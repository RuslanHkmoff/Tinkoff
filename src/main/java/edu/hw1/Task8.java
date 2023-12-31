package edu.hw1;

public class Task8 {
    private static final int LENGTH = 8;
    private static final int[][] SHIFTS = new int[][] {{-1, -2}, {-2, -1}, {1, 2}, {2, 1}};
    private static final int KNIGHT = 1;
    private static final String EXCEPTION_MESSAGE = "The board must be non null and have size 8x8";

    private Task8() {
    }

    public static boolean knightBoardCapture(int[][] board) {
        if (board == null || board.length != LENGTH) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
        for (int i = 0; i < LENGTH; ++i) {
            if (board[i] == null || board[i].length != LENGTH) {
                throw new IllegalArgumentException(EXCEPTION_MESSAGE);
            }
            for (int j = 0; j < LENGTH; ++j) {
                if (board[i][j] == KNIGHT) {
                    for (int[] shift : SHIFTS) {
                        if (i + shift[0] > 0 && i + shift[0] < LENGTH
                            && j + shift[1] > 0 && j + shift[1] < LENGTH
                            && board[i + shift[0]] != null && board[j + shift[1]] != null
                            && board[i + shift[0]][j + shift[1]] == KNIGHT) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
