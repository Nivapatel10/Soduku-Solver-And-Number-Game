import java.util.Random;
import java.util.Scanner;

class SudokuGame {
    static int[][] board;
    static int size;
    static int difficulty;
    static int score = 0;
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            System.out.println("\nChoose an option:\n1. Play Sudoku\n2. Play Number Guessing Game\n3. View Sudoku Rules\n4. Auto-Solve Sudoku\n5. Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    setupSudoku();
                    playSudoku();
                    break;
                case 2:
                    numberGame();
                    break;
                case 3:
                    displayRules();
                    break;
                case 4:
                    autoSolveSudoku();
                    break;
                case 5:
                    System.out.println("Final Score: " + score);
                    System.out.println("Exiting the game. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    static void setupSudoku() {
        System.out.println("Choose Sudoku size:\n1. 6x6\n2. 9x9");
        int choice = sc.nextInt();
        size = (choice == 1) ? 6 : 9;
        board = new int[size][size];

        System.out.println("Select difficulty:\n1. Easy\n2. Medium\n3. Hard");
        difficulty = sc.nextInt();
        generateBoard();
    }
    static void generateBoard() {
        Random rand = new Random();
        int clues = (difficulty == 1) ? (size * size / 3) : (difficulty == 2) ? (size * size / 4) : (size * size / 6);
        for (int i = 0; i < clues; i++) {
            int row, col, num;
            do {
                row = rand.nextInt(size);
                col = rand.nextInt(size);
                num = rand.nextInt(size) + 1;
            } while (!isValid(row, col, num) || board[row][col] != 0);
            board[row][col] = num;
        }
    }
    static void playSudoku() {
        int attempts = 0;
        while (true) {
            printBoard();
            System.out.println("Current Score: " + score);
            System.out.println("\nChoose an action:\n1. Enter a number\n2. Check number validity\n3. Reset Board\n4. Display Board\n5. Get Hint\n6. Exit Sudoku");
            int action = sc.nextInt();

            switch (action) {
                case 1:
                    enterNumber();
                    attempts++;
                    if (attempts >= 6) {
                        suggestHint();
                    }
                    break;
                case 2:
                    checkValidity();
                    break;
                case 3:
                    setupSudoku();
                    break;
                case 4:
                    printBoard();
                    break;
                case 5:
                    suggestHint();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
    static void enterNumber() {
        System.out.println("Enter row (1-" + size + "):");
        int row = sc.nextInt() - 1;
        System.out.println("Enter column (1-" + size + "):");
        int col = sc.nextInt() - 1;
        System.out.println("Enter number:");
        int num = sc.nextInt();
        if (row < 0 || row >= size || col < 0 || col >= size || num < 1 || num > size) {
            System.out.println("Invalid input! Row, column, and number must be within valid range.");
            return;
        }
        if (isValid(row, col, num) && board[row][col] == 0) {
            board[row][col] = num;
            score += 10;
            System.out.println("Correct! +10 points");
        } else {
            score -= 5;
            System.out.println("Invalid move. -5 points. Try again.");
        }
    }
    static void suggestHint() {
        Random rand = new Random();
        int row, col;
        do {
            row = rand.nextInt(size);
            col = rand.nextInt(size);
        } while (board[row][col] != 0);
        System.out.println("Try placing a number at row " + (row + 1) + ", column " + (col + 1));
    }
    static void checkValidity() {
        System.out.println("Enter row:");
        int row = sc.nextInt() - 1;
        System.out.println("Enter column:");
        int col = sc.nextInt() - 1;
        System.out.println("Enter number to check:");
        int num = sc.nextInt();

        if (isValid(row, col, num)) {
            System.out.println("Valid move.");
        } else {
            System.out.println("Invalid move.");
        }
    }
    static boolean isValid(int row, int col, int num) {
        for (int i = 0; i < size; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }
        int subGridSize = (size == 9) ? 3 : 2;
        int subRowStart = (row / subGridSize) * subGridSize;
        int subColStart = (col / subGridSize) * subGridSize;

        for (int i = 0; i < subGridSize; i++) {
            for (int j = 0; j < subGridSize; j++) {
                if (board[subRowStart + i][subColStart + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }
    static void autoSolveSudoku() {
        if (solveSudoku(0, 0)) {
            System.out.println("Sudoku solved successfully:");
            printBoard();
        } else {
            System.out.println("No solution found.");
        }
    }
    static boolean solveSudoku(int row, int col) {
        if (row == size) return true;
        if (col == size) return solveSudoku(row + 1, 0);
        if (board[row][col] != 0) return solveSudoku(row, col + 1);
        for (int num = 1; num <= size; num++) {
            if (isValid(row, col, num)) {
                board[row][col] = num;
                if (solveSudoku(row, col + 1)) return true;
                board[row][col] = 0;
            }
        }
        return false;
    }
    static void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print((board[i][j] == 0 ? "." : board[i][j]) + " ");
            }
            System.out.println();
        }
    }
    static void displayRules() {
        System.out.println("Sudoku Rules:");
        System.out.println("1. Each row must contain unique numbers.");
        System.out.println("2. Each column must contain unique numbers.");
        System.out.println("3. Each smaller grid (3x3 or 2x3) must also be unique.");
        System.out.println("4. Use logic to fill in the missing numbers.");
    }
    static void numberGame() {
        Random rand = new Random();
        int numberToGuess = rand.nextInt(100) + 1;
        int attempts = 0;
        System.out.println("Guess a number between 1 and 100:");

        while (true) {
            int guess = sc.nextInt();
            attempts++;
            if (guess < numberToGuess) {
                System.out.println("Too low! Try again.");
            } else if (guess > numberToGuess) {
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Congratulations! You guessed it in " + attempts + " attempts.");
                break;
            }
        }
    }
}