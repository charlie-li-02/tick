package ui;

import exceptions.ItemDoesNotExistException;
import exceptions.TooManyItemsUndoneException;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {

    private ArrayList<Item> listOfToDo;
    private ArrayList<Item> listOfReminder;
    private Scanner takeInput;
    private String toDoSavePath = "todos.txt";
    private String reminderSavePath = "reminders.txt";

    // bunch of strings for input options because too long for check style
    private String optionP1 = "Please enter one of:\n";
    private String optionP2 = "t - add a to do\n";
    private String optionP3 = "r - add a reminder\n";
    private String optionP4 = "tlist - current to do list\n";
    private String optionP5 = "rlist - current reminder list\n";
    private String optionP6 = "e - exit";

    //bunch of strings for deleting or marking items because too long for check style
    private String outOP1 = "Would you like to delete an item or change an item's status?\n";
    private String outOP2 = "d - delete an item\n";
    private String outOP3 = "f - flip the status of an item\n";
    private String outOP4 = "n - no";

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: constructor for ToDoList
    private ToDoList() throws IOException {
        listOfToDo = new ArrayList<>();
        listOfReminder = new ArrayList<>();
        takeInput = new Scanner(System.in);
        startUp();
    }

    //REQUIRES: nothing
    //MODIFIES: makes a new ListOfToDo and a new ListOfReminder
    //EFFECTS: loads the saved items into the right lists
    private void startUp() throws IOException {
        ListOfToDo ltd = new ListOfToDo(listOfToDo);
        ListOfReminder lr = new ListOfReminder(listOfReminder);
        ltd.load(toDoSavePath);
        lr.load(reminderSavePath);
        processInput(ltd, lr);
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: gives the user the choice of adding a to do, reminder, show current lists, or close the program
    private void processInput(ListOfToDo ltd, ListOfReminder lr) throws IOException {
        String type;
        while (true) {
            System.out.println(optionP1 + optionP2 + optionP3 + optionP4 + optionP5 + optionP6);
            type = takeInput.nextLine();

            if (type.equals("e")) {
                break;

            } else if (type.equals("t")) {
                handleToDo(ltd);

            } else if (type.equals("r")) {
                handleReminder(lr);

            } else if (type.equals("tlist")) {
                handlePrintList(ltd);

            } else if (type.equals("rlist")) {
                handlePrintList(lr);

            } else {
                System.out.println("Your entrance did not match any options, please try again. \n");
            }
        }
    }

    //REQUIRES: nothing
    //MODIFIES: ListOfToDo, todos.txt
    //EFFECTS: starts off the processing of adding a to do item
    private void handleToDo(ListOfToDo ltd) throws IOException {
        processItem(ltd);
        ltd.save(toDoSavePath);
    }

    //REQUIRES: nothing
    //MODIFIES: ListOfReminder, reminders.txt
    //EFFECTS: starts off the processing of adding a reminder item
    private void handleReminder(ListOfReminder lr) throws IOException {
        processItem(lr);
        lr.save(reminderSavePath);
    }

    //REQUIRES: nothing
    //MODIFIES: RemainderItem
    //EFFECTS: creates a new ReminderItem based on the user's input
    private void processItem(ListOfItems li) {
        while (true) {
            System.out.println(li.getPromptTitle());
            String reminder = takeInput.nextLine();
            System.out.println(li.getPromptAttribute());
            String time = takeInput.nextLine();
            try {
                li.addNewItem(li.itemMaker(reminder, time));
                System.out.println(li.getPromptAnother());
                String choice = takeInput.nextLine();
                if (choice.equals("n")) {
                    processOptions(li);
                    break;
                }
            } catch (TooManyItemsUndoneException e) {
                handleTooManyItems(li);
                break;
            }
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: gives the user the choice to delete or mark an item as done after being prompted that they have
    //         too many items that aren't done
    private void handleTooManyItems(ListOfItems li) {
        System.out.println("You have too many items undone, delete or mark an item? (y|n)");
        String choice = takeInput.nextLine();
        if (choice.equals("y")) {
            processOptions(li);
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: prints out the list of items
    private void handlePrintList(ListOfItems li) {
        if (li.getSize() == 0) {
            System.out.println("You don't have anything in that list!");
        } else {
            System.out.println(li.print());
            processOptions(li);
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: prompts the user the option to delete or change the status of an item
    private void processOptions(ListOfItems li) {
        while (li.getSize() > 0) {
            System.out.println(outOP1 + outOP2 + outOP3 + outOP4);
            String choice = takeInput.nextLine();
            if (choice.equals("d")) {
                delete(li);
            } else if (choice.equals("f")) {
                mark(li);
            } else if (choice.equals("n")) {
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
        System.out.println(li.print());
        System.out.println("Which item would you like to remove? (enter an integer)");
        int i = takeInput.nextInt();
        takeInput.nextLine();
        try {
            li.remove(i);
        } catch (ItemDoesNotExistException e) {
            System.out.println("Invalid index, try again");
        } finally {
            System.out.println(li.print());
        }

    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: asks the user if they wish to change the status of an item in the list
    private void mark(ListOfItems li) {
        System.out.println(li.print());
        System.out.println("Which item's status would you like to change? (enter an integer)");
        int i = takeInput.nextInt();
        takeInput.nextLine();
        try {
            li.changeStatus(i);
        } catch (ItemDoesNotExistException e) {
            System.out.println("Invalid index, try again");
        } finally {
            System.out.println(li.print());
        }
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: instantiates a new ToDoList and starts the program
    public static void main(String[] args) throws IOException {
        new ToDoList();
    }
}
