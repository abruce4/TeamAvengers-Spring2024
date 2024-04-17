package Items;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

//Thuy Vy Pham
public class Spells
{
    private String id;
    private String name;
    private String description;

    public Spells(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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

    //Read Items.Spells from file
    //Thuy Vy Pham
    public static void readSpells(String filePath, ArrayList<Spells> listOfSpells) {
        try{
            File mySpells = new File(filePath);
            Scanner myReader = new Scanner(mySpells);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] spellData = data.split("-");
                String spellID = spellData[0];
                String name = spellData[1];
                String description = spellData[2];
                Spells spells = new Spells(spellID, name, description);
                listOfSpells.add(spells);
            }
        } catch(Exception e){
            System.out.println("An error occurred with the spell file.");
        }
    }
}

