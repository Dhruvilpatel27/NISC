import java.util.Arrays;

public class Bingo {
    public static int[] play(int[][] calledSquares, int[][][] cardData) {
        int[] winners = new int[cardData.length];

        // Iterate over each round of the game
        for (int round = 0; round < calledSquares.length; round++) {
            int column = calledSquares[round][0];
            int value = calledSquares[round][1];

            // Iterate over each card
            for (int cardIndex = 0; cardIndex < cardData.length; cardIndex++) {
                int[][] card = cardData[cardIndex];

                // Check for winning conditions (rows, columns, and diagonals)
                if (checkRow(card, value) || checkColumn(card, column) || checkDiagonals(card, value)) {
                    // Mark the card as a winner
                    winners[cardIndex] = 1;
                }

                // Check if there are any winners
                if (Arrays.stream(winners).anyMatch(winner -> winner == 1)) {
                    // End the game if there are winners
                    return winners;
                }
            }
        }

        // Return the array of winners
        return winners;
    }

    private static boolean checkRow(int[][] card, int value) {
        for (int[] row : card) {
            boolean isWinningRow = true;
            for (int num : row) {
                if (num != value) {
                    isWinningRow = false;
                    break;
                }
            }
            if (isWinningRow) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkColumn(int[][] card, int column) {
        for (int row = 0; row < card.length; row++) {
            if (card[row][column] == -1 || card[row][column] == 0) {
                continue; // Skip free spaces
            }
            if (card[row][column] != 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkDiagonals(int[][] card, int value) {
        int numRows = card.length;
        int numColumns = card[0].length;

        // Check main diagonal
        boolean isWinningDiagonal = true;
        for (int i = 0; i < numRows; i++) {
            if (card[i][i] != value) {
                isWinningDiagonal = false;
                break;
            }
        }
        if (isWinningDiagonal) {
            return true;
        }

        // Check secondary diagonal
        isWinningDiagonal = true;
        for (int i = 0; i < numRows; i++) {
            if (card[i][numColumns - 1 - i] != value) {
                isWinningDiagonal = false;
                break;
            }
        }
        return isWinningDiagonal;
    }

    public static void main(String[] args) {
        int[][] calledSquares = new int[][]{
                new int[]{0, 40},
                new int[]{1, 41},
                new int[]{3, 37},
                new int[]{4, 35}
        };

        int[][][] cardData = new int[][][]{
                new int[][]{
                        new int[]{6, 21, 36, 55, 61},
                        new int[]{12, 19, 43, 56, 69},
                        new int[]{9, 24, -1, 46, 71},
                        new int[]{3, 20, 44, 52, 67},
                        new int[]{1, 30, 34, 57, 65}
                },
                new int[][]{
                        new int[]{4, 16, 40, 46, 72},
                        new int[]{10, 17, 41, 58, 62},
                        new int[]{2, 26, -1, 48, 66},
                        new int[]{7, 18, 37, 60, 63},
                        new int[]{14, 30, 35, 59, 73}
                }
        };

        int[] winners = Bingo.play(calledSquares, cardData);

        System.out.println("Winning cards:");
        for (int i = 0; i < winners.length; i++) {
            if (winners[i] == 1) {
                System.out.println("Card " + i);
            }
        }
    }
}
