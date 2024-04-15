/**Class: Puzzle
 * @author Team Avengers / Thuy Vy Pham
 * @version 1.0
 * Course: ITEC 3860 Spring 2024
 * Written: Apr 10, 2024
 * The Puzzle class represents a game puzzle, including its ID, name, description, a fail message, and a hint.
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

    // Attempt to solve the puzzle with the given answer
    public boolean attemptSolve(String playerInput) {
        if (playerInput.equalsIgnoreCase("eot") || playerInput.equalsIgnoreCase("Eye of Truth")) {
            System.out.println(getHint());
            return false;
        }
        if (attemptsLeft <= 0) {
            System.out.println("The wheels no longer budge.");
            return false;
        }

        if (playerInput.equalsIgnoreCase(solution)) {
            isSolved = true;
            System.out.println("Correct! Puzzle solved.");
            return true;
        } else {
            attemptsLeft--;
            System.out.println("Incorrect. Try again. Attempts left: " + attemptsLeft);
            if (attemptsLeft == 0) {
                System.out.println(failMessage);
            }
            return false;
        }
    }

}
