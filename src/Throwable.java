public class Throwable extends Item {

    private int damageDealt;
    private int dexReduction;

    // Constructor
    public Throwable(String itemID, String itemType, String itemName, String itemDescription, int itemValue, int damageDealt, int dexReduction) {
        super(itemID, itemType, itemName, itemDescription, itemValue);
        this.damageDealt = damageDealt;
        this.dexReduction = dexReduction;
    }

    // Getters and Setters
    public int getSpeedReduction() {
        return dexReduction;
    }

    public void setSpeedReduction(int speedReduction) {
        this.dexReduction = speedReduction;
    }

    public int getDamageDealt() {
        return damageDealt;
    }

    public void setDamageDealt(int damageDealt) {
        this.damageDealt = damageDealt;
    }
}
