package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class ListOfReminder extends ListOfItems implements Save, Load {

    private ArrayList<Item> listOfRI;

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

}
