package model;

import java.util.Scanner;
import java.util.ArrayList;

public class Reminder {

    private ArrayList<ReminderItem> listOfRI;
    private Scanner input;

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: constructor for Reminder
    public Reminder(ArrayList<ReminderItem> listOfRI) {
        this.listOfRI = listOfRI;
        input = new Scanner(System.in);
        processReminder();
    }

    //REQUIRES: nothing
    //MODIFIES: RemainderItem
    //EFFECTS: creates a new ReminderItem based on the user's input
    private void processReminder() {
        System.out.println("Enter a new reminder:");
        String reminder = input.nextLine();
        System.out.println("Enter a time for your new reminder:");
        String time = input.nextLine();
        ReminderItem ri = new ReminderItem(reminder, time, false);
        addReminder(ri);
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds the ReminderItem to the list of remainder items
    private void addReminder(ReminderItem ri) {
        this.listOfRI.add(ri);
    }

}
