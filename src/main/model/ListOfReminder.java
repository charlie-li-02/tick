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

public class ListOfReminder implements ListOfItems, Save, Load {

    private ArrayList<Item> listOfRI;
    private Scanner input;

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: constructor for Reminder
    public ListOfReminder(ArrayList<Item> listOfRI) {
        this.listOfRI = listOfRI;
        input = new Scanner(System.in);
    }

    //REQUIRES: nothing
    //MODIFIES: ReminderItem
    //EFFECTS: creates a new apparent type Item actual type ReminderItem
    public Item reminderMaker(String reminder, String time) {
        Item ri = new ReminderItem(reminder, time, false);
        return ri;
    }

    //REQUIRES: nothing
    //MODIFIES: RemainderItem
    //EFFECTS: creates a new ReminderItem based on the user's input
    public void processInput() {
        while (true) {
            System.out.println("Enter a new reminder:");
            String reminder = input.nextLine();
            System.out.println("Enter a time for your new reminder:");
            String time = input.nextLine();
            addItem(reminderMaker(reminder, time));
            System.out.println("Do you want to add another reminder? (y/n)");
            String choice = input.nextLine();
            if (choice.equals("n")) {
                break;
            }
        }
        processOptions();
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds the ReminderItem to the list of remainder items
    public void addItem(Item ri) {
        this.listOfRI.add(ri);
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: prompts the user the option to delete or change the status of a reminder
    private void processOptions() {
        while (this.listOfRI.size() > 0) {
            System.out.println("Would you like to delete an item or change an item's status? (delete/mark/no)");
            String choice = input.nextLine();
            if (choice.equals("delete")) {
                delete();
                System.out.println(listOfRI);
            } else if (choice.equals("mark")) {
                mark();
                System.out.println(listOfRI);
            } else if (choice.equals("no")) {
                System.out.println(listOfRI);
                break;
            } else {
                System.out.println("try again");
            }
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: asks the user if they wish to remove an item from the list
    private void delete() {
        System.out.println(listOfRI);
        System.out.println("Which item would you like to remove? (enter an integer)");
        int i = input.nextInt();
        input.nextLine();
        if (i + 1 > this.listOfRI.size()) {
            System.out.println("Invalid index, try again");
        } else {
            remove(i);
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: asks the user if they wish to change the status of an item in the list
    private void mark() {
        System.out.println(listOfRI);
        System.out.println("Which reminder's status would you like to change? (enter an integer)");
        int i = input.nextInt();
        input.nextLine();
        if (i + 1 > this.listOfRI.size()) {
            System.out.println("Invalid index, try again");
        } else {
            get(i).flipStatus();
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the ith Item
    public Item get(int i) {
        return this.listOfRI.get(i);
    }

    //REQUIRES: at least one item in the array
    //MODIFIES: this
    //EFFECTS: removes the ith Item
    public Item remove(int i) {
        return this.listOfRI.remove(i);
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
    //EFFECTS: splits a single line of the save file into pieces at where there are spaces
    public static ArrayList<String> split(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: converts an item into an entry for the save file
    public static String merge(Item item) {
        String entry = item.getTitle() + " " + item.getAttribute() + " " + booleanToString(item.getIsDone());
        return entry;
    }

    //REQUIRES: s to be either "true" or "false"
    //MODIFIES: nothing
    //EFFECTS: converts a string representation of a boolean value into a boolean
    public static Boolean stringToBoolean(String s) {
        if (s.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: converts a boolean into a string representation of that boolean
    public static String booleanToString(Boolean b) {
        if (b) {
            return "true";
        } else {
            return "false";
        }
    }
}
