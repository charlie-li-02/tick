package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ListOfReminder extends ListOfItems implements Save, Load {

    private ArrayList<Item> listOfRI;
    private String reminderSavePath = "reminders.txt";

    public String promptTitle = "Enter a title and a time for your new reminder:";
    public String promptAnother = "Do you want to add another reminder?";

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: constructor for Reminder
    public ListOfReminder() {
        super();
        this.listOfRI = new ArrayList<>();
    }

    //REQUIRES: nothing
    //MODIFIES: ReminderItem
    //EFFECTS: creates a new apparent type Item actual type ReminderItem
    public Item itemMaker(String reminder, String time) {
        Item ri = new ReminderItem(reminder, time, false);
        return ri;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: formats the list of items into a list of string
    public ArrayList<String> print() {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < listOfItems.size(); i++) {
            String s = i + 1 + ". Remind me to " + listOfItems.get(i).getTitle()
                    + " at " + listOfItems.get(i).getAttribute();
            result.add(s);
        }
        return result;
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: loads the items in the save file into the list
    public void load() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(reminderSavePath));
        for (String line : lines) {
            ArrayList<String> parts = split(line);
            Item i = new ReminderItem(parts.get(0), parts.get(1), stringToBoolean(parts.get(2)));
            addItem(i);
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the PromptTitle
    public String getPromptTitle() {
        return promptTitle;
    }


    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the PromptAnother
    public String getPromptAnother() {
        return promptAnother;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the SavePath
    public String getSavePath() {
        return reminderSavePath;
    }
}
