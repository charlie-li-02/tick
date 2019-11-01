package ui;

import exceptions.ItemDoesNotExistException;
import model.ListOfItems;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class ItemOptions {

    private Scanner takeInput;


    //bunch of strings for deleting or marking items because too long for check style
    private static final String outOP1 = "Would you like to delete an item or change an item's status?\n";
    private static final String outOP2 = "1 - delete an item\n";
    private static final String outOP3 = "2 - flip the status of an item\n";
    private static final String outOP4 = "3 - no";

    public ItemOptions() {
        takeInput = new Scanner(System.in);
    }


    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: prompts the user the option to delete or change the status of an item
    public void processOptions(ListOfItems listOfItems) throws FileNotFoundException, UnsupportedEncodingException {
        while (listOfItems.getSize() > 0) {
            System.out.println(outOP1 + outOP2 + outOP3 + outOP4);
            String choice = takeInput.nextLine();
            if (choice.equals("1")) {
                delete(listOfItems);
            } else if (choice.equals("2")) {
                mark(listOfItems);
            } else if (choice.equals("3")) {
                System.out.println(listOfItems.print());
                break;
            } else {
                System.out.println("Try again");
            }
        }

    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: asks the user if they wish to remove an item from the list
    private void delete(ListOfItems listOfItems) throws FileNotFoundException, UnsupportedEncodingException {
        int i = getIndex(listOfItems, "Which item would you like to remove? (enter an integer)");
        try {
            listOfItems.remove(i);
        } catch (ItemDoesNotExistException e) {
            System.out.println("Invalid index, try again");
        } finally {
            System.out.println(listOfItems.print());
            listOfItems.save();
        }

    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: asks the user if they wish to change the status of an item in the list
    private void mark(ListOfItems listOfItems) throws FileNotFoundException, UnsupportedEncodingException {
        int i = getIndex(listOfItems, "Which item's status would you like to change? (enter an integer)");
        try {
            listOfItems.changeStatus(i);
        } catch (ItemDoesNotExistException e) {
            System.out.println("Invalid index, try again");
        } finally {
            System.out.println(listOfItems.print());
            listOfItems.save();
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the index inputted by te user
    private int getIndex(ListOfItems listOfItems, String message) {
        System.out.println(listOfItems.print());
        System.out.println(message);
        int i = takeInput.nextInt();
        takeInput.nextLine();
        return i;
    }
}
