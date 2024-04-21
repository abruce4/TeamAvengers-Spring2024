import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**Class: Puzzle
 * @author Team Avengers / Thuy Vy Pham
 * @version 1.0
 * Course: ITEC 3860 Spring 2024
 * Written: Apr 10, 2024
 * The Puzzle class represents a game puzzle, including its ID, name, description, a fail message, and a hint.
 * It allows reading puzzle details from a file and managing puzzle properties.
 */

public class Puzzle implements Serializable {
    private String puzzleID;
    private String name;
    private String description;
    private String failMessage;
    private String hint;
    private boolean isSolved;
    private int attemptsLeft;
    private String solution;
    private String itemSolution;
    private String itemReward;
    private int coinsReward;
    private int magicReward;
    private int damageTaken;
    private String solvedMessage;

    // Constructor
    public Puzzle(String puzzleID, String name, String description, String failMessage, String hint, String solution, String itemSolution, String itemReward, int coinsReward, int magicReward, String solvedMessage, int damageTaken) {
        this.puzzleID = puzzleID;
        this.name = name;
        this.description = description;
        this.failMessage = failMessage;
        this.hint = hint;
        this.isSolved = false;
        this.attemptsLeft = 5;
        this.solution = solution;
        this.itemSolution = itemSolution;
        this.itemReward = itemReward;
        this.coinsReward = coinsReward;
        this.magicReward = magicReward;
        this.solvedMessage = solvedMessage;
        this.damageTaken = damageTaken;
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
    public void setSolved(boolean solved) { isSolved = solved; }
    public int getAttemptsLeft() { return attemptsLeft; }
    public void setAttemptsLeft(int attemptsLeft) { this.attemptsLeft = attemptsLeft; }
    public String getSolution() { return solution; }
    public void setSolution(String solution) { this.solution = solution; }
    public String getItemSolution() { return itemSolution; }
    public void setItemSolution(String itemSolution) { this.itemSolution = itemSolution; }
    public String getItemReward() { return itemReward; }
    public void setItemReward(String itemReward) { this.itemReward = itemReward; }
    public int getCoinsReward() { return coinsReward; }
    public void setCoinsReward(int coinsReward) { this.coinsReward = coinsReward; }
    public int getMagicReward() { return magicReward; }
    public void setMagicReward(int magicReward) { this.magicReward = magicReward; }
    public int getDamageTaken() { return damageTaken; }
    public void setDamageTaken(int damageTaken) { this.damageTaken = damageTaken; }
    public String getSolvedMessage() { return solvedMessage; }
    public void setSolvedMessage(String solvedMessage) { this.solvedMessage = solvedMessage; }

    //toString method
    @Override
    public String toString() {
        return name;
    }

    //Method to read puzzle details from a file
    // Thuy Vy Pham
    public static void readPuzzles(String filePath, ArrayList<Puzzle> listOfPuzzles) {
        try {
            File myPuzzles = new File(filePath);
            Scanner myReader = new Scanner(myPuzzles);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] puzzleData = data.split("-");
                String puzzleID = puzzleData[0];
                String name = puzzleData[1];
                String description = puzzleData[2];
                String failMessage = puzzleData[3];
                String hint = puzzleData[4];
                String solution = puzzleData[5];
                String itemSolution = puzzleData[6];
                String itemReward = puzzleData[7];
                int coinsReward = Integer.parseInt(puzzleData[8]);
                int magicReward = Integer.parseInt(puzzleData[9]);
                String solvedMessage = puzzleData[10];
                int damageTaken = Integer.parseInt(puzzleData[11]);
                Puzzle puzzle = new Puzzle(puzzleID, name, description, failMessage, hint, solution, itemSolution, itemReward, coinsReward, magicReward, solvedMessage, damageTaken);
                listOfPuzzles.add(puzzle);
            }
        } catch (Exception e) {
            System.out.println("An error occurred with the puzzle file.");
            e.printStackTrace();
        }
    }

}

