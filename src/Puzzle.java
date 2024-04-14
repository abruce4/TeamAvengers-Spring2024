/**Class: Puzzle
 * @author Team Avengers / Thuy Vy Pham
 * @version 1.0
 * Course: ITEC 3860 Spring 2024
 * Written: Apr 10, 2024
 * The Puzzle class represents a game puzzle, including its ID, name, description, a fail message, and a hint.
 * It allows reading puzzle details from a file and managing puzzle properties.
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Puzzle {
    private String puzzleID;
    private String name;
    private String description;
    private String failMessage;
    private String hint;

    // Constructor
    public Puzzle(String puzzleID, String name, String description, String failMessage, String hint) {
        this.puzzleID = puzzleID;
        this.name = name;
        this.description = description;
        this.failMessage = failMessage;
        this.hint = hint;
    }

    // Getters and Setters
    public String getPuzzleID() {
        return puzzleID;
    }

    public void setPuzzleID(String puzzleID) {
        this.puzzleID = puzzleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFailMessage() {
        return failMessage;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
