import java.io.Serializable;

public class Aura extends Item implements Serializable {

    private int addedMagic;
    private int addedDex;
    private int addedDefense;

    // Constructor
    public Aura(String itemID, String itemType, String itemName, String itemDescription, int itemValue, int addedMagic, int addedDex, int addedDefense) {
        super(itemID, itemType, itemName, itemDescription, itemValue);
        this.addedMagic = addedMagic;
        this.addedDex = addedDex;
        this.addedDefense = addedDefense;
    }

    // Getters and Setters
    public int getAddedMagic() {
        return addedMagic;
    }

    public void setAddedMagic(int addedMagic) {
        this.addedMagic = addedMagic;
    }

    public int getAddedDex() {
        return addedDex;
    }

    public void setAddedDex(int addedDex) {
        this.addedDex = addedDex;
    }

    public int getAddedDefense() {
        return addedDefense;
    }

    public void setAddedDefense(int addedDefense) {
        this.addedDefense = addedDefense;
    }

}
