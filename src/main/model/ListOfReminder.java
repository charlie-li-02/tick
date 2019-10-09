package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class ListOfReminder extends ListOfItems implements Save, Load {

    private ArrayList<Item> listOfRI;
    public String promptTitle = "Enter a new reminder:";
    public String promptAttribute = "Enter a time for your new reminder:";
    public String promptAnother = "Do you want to add another reminder? (y|n)";

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: constructor for Reminder
    public ListOfReminder(ArrayList<Item> lri) {
        super(lri);
        this.listOfRI = lri;
    }

    //REQUIRES: nothing
    //MODIFIES: ReminderItem
    //EFFECTS: creates a new apparent type Item actual type ReminderItem
    public Item itemMaker(String reminder, String time) {
        Item ri = new ReminderItem(reminder, time, false);
        return ri;
    }

    //REQUIRES: reminders.txt is in the right path
    //MODIFIES: this, ReminderItem
    //EFFECTS: reads the save file and adds the saved reminder items into the list
    public void load(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        for (String line : lines) {
            ArrayList<String> parts = split(line);
            Item ri = new ReminderItem(parts.get(0), parts.get(1), stringToBoolean(parts.get(2)));
            addItem(ri);
        }
    }

    //REQUIRES: reminders.txt is in the right path
    //MODIFIES: reminders.txt
    //EFFECTS: adds the ReminderItems into the save file
    public void save(String path) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter fileClearer = new PrintWriter(path, "UTF-8");
        fileClearer.close();
        PrintWriter writer = new PrintWriter(path, "UTF-8");
        for (Item item: this.listOfRI) {
            String line = merge(item);
            writer.println(line);
        }
        writer.close();
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: formats the list of items into a list of string
    public ArrayList<String> print() {
        ArrayList<String> result = new ArrayList<>();
        for (Item i: this.listOfItem) {
            String s = "Remind me to " + i.getTitle() + " at " + i.getAttribute() + " Done? " + i.getIsDone();
            result.add(s);
        }
        return result;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the PromptTitle
    public String getPromptTitle() {
        return this.promptTitle;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the PromptAttribute
    public String getPromptAttribute() {
        return this.promptAttribute;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the PromptAnother
    public String getPromptAnother() {
        return this.promptAnother;
    }

}
