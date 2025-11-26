# Sudoku & Number Guessing Game 🧩🎯

A simple Java console application that combines a playable Sudoku game (6x6 or 9x9) with an independent Number Guessing game. The app also includes a Sudoku auto-solver, a hint system, score tracking, and a basic random puzzle generator.

Features
- Play Sudoku in 6x6 or 9x9 sizes 🟦
- Choose difficulty levels: Easy / Medium / Hard 🔧
- Enter numbers, check validity, get hints, reset board, or auto-solve 🧠
- Points-based scoring for Sudoku: +10 for correct placements, -5 for invalid attempts ⭐
- Separate Number Guessing game (1–100) 🎲
- Console-driven menu for easy navigation ▶️

Table of contents
- Features
- Requirements
- Installation & Run
- How to Play
  - Sudoku controls
  - Number Guessing Game
- Rules
- Auto-solver & Hints
- Known limitations & improvements
- License & Contact

Requirements
- Java JDK 8 or newer installed
- Command-line (terminal) access

Installation & Run
1. Save the source code as `SudokuGame.java`.
2. Compile:
   javac SudokuGame.java
3. Run:
   java SudokuGame

How to Play

Main Menu
1. Play Sudoku — start or reset a Sudoku game.
2. Play Number Guessing Game — play a separate guessing game.
3. View Sudoku Rules — quick reference for Sudoku rules.
4. Auto-Solve Sudoku — attempt to fill the current Sudoku board with the solver.
5. Exit — quits the app and displays your final score.

Sudoku flow
- When you choose Play Sudoku:
  - Select size: 6x6 or 9x9.
  - Select difficulty: Easy / Medium / Hard. The program seeds a number of clues based on size/difficulty.
  - The board is displayed in the console; empty cells are shown as `.`.
  - Menu options inside Sudoku:
    1. Enter a number — provide row, column, and number (1-based indexing).
    2. Check number validity — test a number for a particular cell without placing it.
    3. Reset Board — choose size/difficulty again and generate a new board.
    4. Display Board — print the current board.
    5. Get Hint — the program suggests an empty cell to try.
    6. Exit Sudoku — returns to main menu.
- Scoring:
  - Correct placement: +10 points
  - Invalid attempt: -5 points

Number Guessing Game
- The program picks a random integer from 1 to 100.
- Enter guesses until you find the number; the program tells you if your guess is too low or too high.
- The game reports how many attempts it took when you succeed.

Rules (as shown in-app)
1. Each row must contain unique numbers.
2. Each column must contain unique numbers.
3. Each smaller grid must also be unique (3x3 for 9x9; 2x3 for 6x6).
4. Use logic to fill in the missing numbers — no guessing to force acceptance.

Auto-solver & Hints
- Auto-solver: Uses a backtracking solver to fill empty cells and print the solved board if a solution exists.
- Hint: Suggests a random empty cell to try (it does not reveal the correct number, only a cell coordinate).



Contact
- If you have suggestions or questions about the implementation, open an issue on the repository or reach out via your preferred channel.

Enjoy solving and building on this project! ✨
