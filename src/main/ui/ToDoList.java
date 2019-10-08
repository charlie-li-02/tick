package ui;

import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {

    public ArrayList<Item> listOfToDo;
    public ArrayList<Item> listOfReminder;
    private Scanner takeInput;
    private String toDoSavePath = "todos.txt";
    private String reminderSavePath = "reminders.txt";

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: constructor for ToDoList
    public ToDoList() throws IOException {
        listOfToDo = new ArrayList<>();
        listOfReminder = new ArrayList<>();
        takeInput = new Scanner(System.in);
        startUp();
    }

    //REQUIRES: nothing
    //MODIFIES: makes a new ListOfToDo and a new ListOfReminder
    //EFFECTS: loads the saved items into the right lists
    public void startUp() throws IOException {
        ListOfToDo ltd = new ListOfToDo(listOfToDo);
        ListOfReminder lr = new ListOfReminder(listOfReminder);
        ltd.load(toDoSavePath);
        lr.load(reminderSavePath);
        processInput(ltd, lr);
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: gives the user the choice of adding a to do, reminder, show current lists, or close the program
    public void processInput(ListOfToDo ltd, ListOfReminder lr) throws IOException {
        String type;
        while (true) {
            System.out.println("Please enter one of: (to do/reminder/to do list/reminder list/exit)");
            type = takeInput.nextLine();

            if (type.equals("exit")) {
                break;

            } else if (type.equals("to do")) {
                handleToDo(ltd);

            } else if (type.equals("reminder")) {
                handleReminder(lr);

            } else if (type.equals("to do list")) {
                System.out.println(ltd.print());

            } else if (type.equals("reminder list")) {
                System.out.println(lr.print());

            } else {
                System.out.println("Your entrance did not match any options, please try again. \n");
            }
        }
    }

    //REQUIRES: nothing
    //MODIFIES: ListOfToDo, todos.txt
    //EFFECTS: starts off the processing of adding a to do item
    public void handleToDo(ListOfToDo ltd) throws IOException {
        processToDo(ltd);
        ltd.save(toDoSavePath);
    }

    //REQUIRES: nothing
    //MODIFIES: ToDoItem
    //EFFECTS: creates a new ToDoItem based on the user's input
    public void processToDo(ListOfToDo ltd) {
        while (true) {
            System.out.println("Enter a title for your new to do:");
            String title = takeInput.nextLine();
            System.out.println("Enter a description for your new to do:");
            String description = takeInput.nextLine();
            ltd.addItem(ltd.itemMaker(title, description));
            System.out.println("Do you want to add another to do? (y/n)");
            String choice = takeInput.nextLine();
            if (choice.equals("n")) {
                break;
            }
        }
        processOptions(ltd);
    }

    //REQUIRES: nothing
    //MODIFIES: ListOfReminder, reminders.txt
    //EFFECTS: starts off the processing of adding a reminder item
    public void handleReminder(ListOfReminder lr) throws IOException {
        processReminder(lr);
        lr.save(reminderSavePath);
    }


    //REQUIRES: nothing
    //MODIFIES: RemainderItem
    //EFFECTS: creates a new ReminderItem based on the user's input
    public void processReminder(ListOfReminder lr) {
        while (true) {
            System.out.println("Enter a new reminder:");
            String reminder = takeInput.nextLine();
            System.out.println("Enter a time for your new reminder:");
            String time = takeInput.nextLine();
            lr.addItem(lr.itemMaker(reminder, time));
            System.out.println("Do you want to add another reminder? (y/n)");
            String choice = takeInput.nextLine();
            if (choice.equals("n")) {
                break;
            }
        }
        processOptions(lr);
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: prompts the user the option to delete or change the status of an item
    private void processOptions(ListOfItems li) {
        while (li.getSize() > 0) {
            System.out.println("Would you like to delete an item or change an item's status? (delete/mark/no)");
            String choice = takeInput.nextLine();
            if (choice.equals("delete")) {
                System.out.println(li.print());
                delete(li);
                System.out.println(li.print());
            } else if (choice.equals("mark")) {
                System.out.println(li.print());
                mark(li);
                System.out.println(li.print());
            } else if (choice.equals("no")) {
                System.out.println(li.print());
                break;
            } else {
                System.out.println("Try again");
            }
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: asks the user if they wish to remove an item from the list
    private void delete(ListOfItems li) {
        System.out.println("Which item would you like to remove? (enter an integer)");
        int i = takeInput.nextInt();
        takeInput.nextLine();
        if (i + 1 > li.getSize()) {
            System.out.println("Invalid index, try again");
        } else {
            li.remove(i);
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: asks the user if they wish to change the status of an item in the list
    private void mark(ListOfItems li) {
        System.out.println("Which item's status would you like to change? (enter an integer)");
        int i = takeInput.nextInt();
        takeInput.nextLine();
        if (i + 1 > li.getSize()) {
            System.out.println("Invalid index, try again");
        } else {
            li.get(i).flipStatus();
        }
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: instantiates a new ToDoList and starts the program
    public static void main(String[] args) throws IOException {
        new ToDoList();
    }
}
