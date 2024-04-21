import java.io.Serializable;

public class Equipable extends Item implements Serializable {

    private int addedHealth;
    private int addedMagic;
    private int addedDexterity;
    private int addedSpeed;
    private int addedDefense;
    private String itemUtility;

    // Constructor
    public Equipable(String itemID, String itemType, String itemName, String itemDescription, int itemValue, int addedHealth, int addedMagic, int addedDexterity, int addedSpeed, int addedDefense, String itemUtility) {
        super(itemID, itemType, itemName, itemDescription, itemValue);
        this.addedHealth = addedHealth;
        this.addedMagic = addedMagic;
        this.addedDexterity = addedDexterity;
        this.addedSpeed = addedSpeed;
        this.addedDefense = addedDefense;
        this.itemUtility = itemUtility;
    }

    // Getters and Setters

    public int getAddedHealth() {
        return addedHealth;
    }

    public void setAddedHealth(int addedHealth) {
        this.addedHealth = addedHealth;
    }

    public int getAddedMagic() {
        return addedMagic;
    }

    public void setAddedMagic(int addedMagic) {
        this.addedMagic = addedMagic;
    }

    public int getAddedDexterity() {
        return addedDexterity;
    }

    public void setAddedDexterity(int addedDexterity) {
        this.addedDexterity = addedDexterity;
    }

    public int getAddedSpeed() {
        return addedSpeed;
    }

    public void setAddedSpeed(int addedSpeed) {
        this.addedSpeed = addedSpeed;
    }

    public int getAddedDefense() {
        return addedDefense;
    }

    public void setAddedDefense(int addedDefense) {
        this.addedDefense = addedDefense;
    }

    public String getItemUtility() {
        return itemUtility;
    }

    public void setItemUtility(String itemUtility) {
        this.itemUtility = itemUtility;
    }
}
