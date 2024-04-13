public class Throwable extends Item{

    private int damageDealt;
    private int speedReduction;

    // Constructor
    public Throwable(int itemID, String itemType, String itemName, String itemDescription, int itemValue, int itemRoomID, int damageDealt, int speedReduction) {
        super(itemID, itemType, itemName, itemDescription, itemValue, itemRoomID);
        this.damageDealt = damageDealt;
        this.speedReduction = speedReduction;
    }

    // Getters and Setters
    public int getSpeedReduction() {
        return speedReduction;
    }

    public void setSpeedReduction(int speedReduction) {
        this.speedReduction = speedReduction;
    }

    public int getDamageDealt() {
        return damageDealt;
    }

    public void setDamageDealt(int damageDealt) {
        this.damageDealt = damageDealt;
    }
}
