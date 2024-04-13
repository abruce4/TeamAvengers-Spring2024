public class Equipable extends Item {

    private int addedHealth;
    private int addedMagic;
    private int addedDexterity;
    private int addedSpeed;
    private int addedDefense;

    // Constructor
    public Equipable(int itemID, String itemType, String itemName, String itemDescription, int itemValue, int itemRoomID, int addedHealth, int addedMagic, int addedDexterity, int addedSpeed, int addedDefense) {
        super(itemID, itemType, itemName, itemDescription, itemValue, itemRoomID);
        this.addedHealth = addedHealth;
        this.addedMagic = addedMagic;
        this.addedDexterity = addedDexterity;
        this.addedSpeed = addedSpeed;
        this.addedDefense = addedDefense;
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
}
