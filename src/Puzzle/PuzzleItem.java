package Puzzle;

import Items.Item;

public class PuzzleItem extends Item {

    private int puzzleID;

    // Constructor
    public PuzzleItem(String itemID, String itemType, String itemName, String itemDescription, int itemValue, int puzzleID) {
        super(itemID, itemType, itemName, itemDescription, itemValue);
        this.puzzleID = puzzleID;
    }

    // Getters and Setters
    public int getPuzzleID() {
        return puzzleID;
    }

    public void setPuzzleID(int puzzleID) {
        this.puzzleID = puzzleID;
    }

}
