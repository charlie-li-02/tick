package ui;

import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {

    public ArrayList<Item> listOfToDo;
    public ArrayList<Item> listOfReminder;
    private Scanner takeInput;

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: constructor for ToDoList
    public ToDoList() throws IOException {
        listOfToDo = new ArrayList<>();
        listOfReminder = new ArrayList<>();
        takeInput = new Scanner(System.in);
        processInput();
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: gives the user the choice of adding a to do, reminder, show current lists, or close the program
    public void processInput() throws IOException {
        String type;
        while (true) {
            System.out.println("Add a new to do, reminder, show current lists, or exit (to do/reminder/list/exit):");
            type = takeInput.nextLine();

            if (type.equals("exit")) {
                break;

            } else if (type.equals("to do")) {
                handleToDo();

            } else if (type.equals("reminder")) {
                handleReminder();

            } else if (type.equals("list")) {
                processOutput();

            } else {
                System.out.println("Your entrance did not match any options, please try again. \n");
            }
        }
    }

    //REQUIRES: nothing
    //MODIFIES: ListOfToDo, todos.txt
    //EFFECTS: starts off the processing of adding a to do item
    public void handleToDo() throws IOException {
        ListOfToDo ltd = new ListOfToDo(listOfToDo);
        ltd.load("todos.txt");
        processToDo(ltd);
        ltd.save("todos.txt");
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
        processOptionsToDo(ltd);
    }

    //REQUIRES: nothing
    //MODIFIES: ListOfReminder, reminders.txt
    //EFFECTS: starts off the processing of adding a reminder item
    public void handleReminder() throws IOException {
        ListOfReminder lr = new ListOfReminder(listOfReminder);
        lr.load("reminders.txt");
        processReminder(lr);
        lr.save("reminders.txt");
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
        processOptionsReminder(lr);
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: prompts the user the option to delete or change the status of a to do
    private void processOptionsToDo(ListOfItems li) {
        while (li.getSize() > 0) {
            System.out.println("Would you like to delete an item or change an item's status? (delete/mark/no)");
            String choice = takeInput.nextLine();
            if (choice.equals("delete")) {
                System.out.println(listOfToDo);
                delete(li);
                System.out.println(listOfToDo);
            } else if (choice.equals("mark")) {
                System.out.println(listOfToDo);
                mark(li);
                System.out.println(listOfToDo);
            } else if (choice.equals("no")) {
                System.out.println(listOfToDo);
                break;
            } else {
                System.out.println("Try again");
            }
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: prompts the user the option to delete or change the status of a to do
    private void processOptionsReminder(ListOfItems li) {
        while (li.getSize() > 0) {
            System.out.println("Would you like to delete an item or change an item's status? (delete/mark/no)");
            String choice = takeInput.nextLine();
            if (choice.equals("delete")) {
                System.out.println(listOfReminder);
                delete(li);
                System.out.println(listOfReminder);
            } else if (choice.equals("mark")) {
                System.out.println(listOfReminder);
                mark(li);
                System.out.println(listOfReminder);
            } else if (choice.equals("no")) {
                System.out.println(listOfReminder);
                break;
            } else {
                System.out.println("Try again");
            }
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: prints out the appropriate list or a warning that the user made a typo
    public void processOutput() {
        System.out.println("Which list would you like to open? (to do/reminder)");
        String choice = takeInput.nextLine();
        if (choice.equals("to do")) {
            System.out.println(listOfToDo);

        } else if (choice.equals("reminder")) {
            System.out.println(listOfReminder);

        } else {
            System.out.println("Your entrance did not match any options, please try again. \n");
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: asks the user if they wish to remove an item from the list
    private void delete(ListOfItems li) {
        //System.out.println(listOfToDo);
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
        //System.out.println(listOfToDo);
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
