public class PuzzleItem extends Item{

    private int puzzleID;

    // Constructor
    public PuzzleItem(int itemID, String itemType, String itemName, String itemDescription, int itemValue, int itemRoomID, int puzzleID) {
        super(itemID, itemType, itemName, itemDescription, itemValue, itemRoomID);
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
