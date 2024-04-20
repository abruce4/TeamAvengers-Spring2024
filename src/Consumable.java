public class Consumable extends Item {

    private int healedHealth;
    private int healedMana;

    // Constructor
    public Consumable(String itemID, String itemType, String itemName, String itemDescription, int itemValue, int healedHealth, int healedMana) {
        super(itemID, itemType, itemName, itemDescription, itemValue);
        this.healedHealth = healedHealth;
        this.healedMana = healedMana;
    }

    // Getters and Setters
    public int getHealedHealth() {
        return healedHealth;
    }

    public void setHealedHealth(int healedHealth) {
        this.healedHealth = healedHealth;
    }

    public int getHealedMana() {
        return healedMana;
    }

    public void setHealedMana(int healedMana) {
        this.healedMana = healedMana;
    }
}
