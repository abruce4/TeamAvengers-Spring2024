package Puzzle;

import java.util.Scanner;

/**Class: Puzzle.Puzzle
 * @author Team Avengers / Thuy Vy Pham
 * @version 1.0
 * Course: ITEC 3860 Spring 2024
 * Written: Apr 10, 2024
 * The Puzzle.Puzzle class represents a game puzzle, including its ID, name, description, a fail message, and a hint.
 * It allows reading puzzle details from a file and managing puzzle properties.
 */

public class Puzzle {
    private String puzzleID;
    private String name;
    private String description;
    private String solution;
    private String failMessage;
    private String hint;
    private boolean isSolved;
    private int attemptsLeft;

    // Constructor
    public Puzzle(String puzzleID, String name, String description, String solution, String failMessage, String hint) {
        this.puzzleID = puzzleID;
        this.name = name;
        this.description = description;
        this.solution = solution;
        this.failMessage = failMessage;
        this.hint = hint;
        this.isSolved = false;
        this.attemptsLeft = 5;
    }

    // Getters and Setters
    public String getPuzzleID() { return puzzleID; }
    public void setPuzzleID(String puzzleID) { this.puzzleID = puzzleID; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getFailMessage() { return failMessage; }
    public void setFailMessage(String failMessage) { this.failMessage = failMessage; }
    public String getHint() { return hint; }
    public void setHint(String hint) { this.hint = hint; }
    public boolean isSolved() { return isSolved; }
    public int getAttemptsLeft() { return attemptsLeft; }
    //check if current room has puzzle

    //Attempts to solve the puzzle
    public boolean attemptSolve(String playerInput) {
        if (playerInput.equalsIgnoreCase(solution)) {
            isSolved = true;
            System.out.println("Correct! You have solved the puzzle.");
            return true;
        } else {
            attemptsLeft--;
            if (attemptsLeft > 0) {
                System.out.println("Incorrect solution. Please try again. Attempts left: " + attemptsLeft);
            } else {
                System.out.println(failMessage);
            }
            return false;
        }
    }
    public void interactWithPuzzle() {
        Scanner scanner = new Scanner(System.in);
        while (!isSolved && attemptsLeft > 0) {
            System.out.println(getDescription());
            System.out.println("Enter 'solve' to attempt to solve the puzzle, 'eot' for a hint:");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("solve")) {
                System.out.println("Enter your solution:");
                String solution = scanner.nextLine();
                if (!attemptSolve(solution)) {
                    continue;
                }
                break; // Exit if solved
            } else if (input.equalsIgnoreCase("eot") || input.equalsIgnoreCase("Eye of Truth")) {
                System.out.println(getHint());
            } else {
                System.out.println("Invalid command. Please type 'solve' or 'eot'.");
            }
        }

        if (!isSolved) {
            System.out.println(getFailMessage());
        }
    }
}

