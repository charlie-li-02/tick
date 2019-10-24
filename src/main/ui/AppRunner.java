package ui;

import exceptions.ItemDoesNotExistException;
import exceptions.TooManyItemsUndoneException;
import model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class AppRunner {

    private ListOfItems listOfToDo;
    private ListOfItems listOfReminder;
    private Scanner takeInput;

    // bunch of strings for input options because too long for check style
    private String optionP1 = "Please enter one of:\n";
    private String optionP2 = "1 - add a to do\n";
    private String optionP3 = "2 - add a reminder\n";
    private String optionP4 = "3 - current to do list\n";
    private String optionP5 = "4 - current reminder list\n";
    private String optionP6 = "5 - exit";

    //bunch of strings for deleting or marking items because too long for check style
    private String outOP1 = "Would you like to delete an item or change an item's status?\n";
    private String outOP2 = "1 - delete an item\n";
    private String outOP3 = "2 - flip the status of an item\n";
    private String outOP4 = "3 - no";

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: constructor for ToDoList
    private AppRunner() throws IOException {
        takeInput = new Scanner(System.in);
        listOfToDo = new ListOfToDo();
        listOfReminder = new ListOfReminder();

        listOfToDo.load();
        listOfReminder.load();
        processInput();

    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: gives the user the choice of adding a to do, reminder, show current lists, or close the program
    private void processInput() throws IOException {
        String type;
        while (true) {
            System.out.println(optionP1 + optionP2 + optionP3 + optionP4 + optionP5 + optionP6);
            type = takeInput.nextLine();

            if (type.equals("5")) {
                break;

            } else if (type.equals("1")) {
                handleToDo(listOfToDo);

            } else if (type.equals("2")) {
                handleReminder(listOfReminder);

            } else if (type.equals("3")) {
                handlePrintList(listOfToDo);

            } else if (type.equals("4")) {
                handlePrintList(listOfReminder);

            } else {
                System.out.println("Your entrance did not match any options, please try again. \n");
            }
        }
    }

    //REQUIRES: nothing
    //MODIFIES: ListOfToDo, todos.txt
    //EFFECTS: starts off the processing of adding a to do item
    private void handleToDo(ListOfItems ltd) throws IOException {
        processItem(ltd);
        ltd.save();
    }

    //REQUIRES: nothing
    //MODIFIES: ListOfReminder, reminders.txt
    //EFFECTS: starts off the processing of adding a reminder item
    private void handleReminder(ListOfItems lr) throws IOException {
        processItem(lr);
        lr.save();
    }

    //REQUIRES: nothing
    //MODIFIES: RemainderItem
    //EFFECTS: creates a new ReminderItem based on the user's input
    private void processItem(ListOfItems li) throws FileNotFoundException, UnsupportedEncodingException {
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
    private void handleTooManyItems(ListOfItems li) throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("You have too many items undone, delete or mark an item? (y|n)");
        String choice = takeInput.nextLine();
        if (choice.equals("y")) {
            processOptions(li);
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: prints out the list of items
    private void handlePrintList(ListOfItems li) throws FileNotFoundException, UnsupportedEncodingException {
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
    private void processOptions(ListOfItems li) throws FileNotFoundException, UnsupportedEncodingException {
        while (li.getSize() > 0) {
            System.out.println(outOP1 + outOP2 + outOP3 + outOP4);
            String choice = takeInput.nextLine();
            if (choice.equals("1")) {
                delete(li);
            } else if (choice.equals("2")) {
                mark(li);
            } else if (choice.equals("3")) {
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
    private void delete(ListOfItems li) throws FileNotFoundException, UnsupportedEncodingException {
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
            li.save();
        }

    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: asks the user if they wish to change the status of an item in the list
    private void mark(ListOfItems li) throws FileNotFoundException, UnsupportedEncodingException {
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
            li.save();
        }
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: instantiates a new ToDoList and starts the program
    public static void main(String[] args) throws IOException {
        new AppRunner();
    }
}
