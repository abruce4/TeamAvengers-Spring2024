public class Consumable extends Item {

    private int healedHealth;

    // Constructor
    public Consumable(String itemID, String itemType, String itemName, String itemDescription, int itemValue, int healedHealth) {
        super(itemID, itemType, itemName, itemDescription, itemValue);
        this.healedHealth = healedHealth;
    }

    // Getters and Setters
    public int getHealedHealth() {
        return healedHealth;
    }

    public void setHealedHealth(int healedHealth) {
        this.healedHealth = healedHealth;
    }
}
