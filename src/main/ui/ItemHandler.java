package ui;

import exceptions.TooManyItemsUndoneException;
import model.ListOfItems;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;


public class ItemHandler {
    private ItemOptions itemOptions;
    private Scanner takeInput;

    public ItemHandler() {
        takeInput = new Scanner(System.in);
        itemOptions = new ItemOptions();
    }

    //REQUIRES: nothing
    //MODIFIES: ListOfReminder, reminders.txt
    //EFFECTS: starts off the processing of adding a reminder item
    public void startItem(ListOfItems listOfItems) throws IOException {
        makeNewItem(listOfItems);
        listOfItems.save();
    }

    //REQUIRES: nothing
    //MODIFIES: RemainderItem
    //EFFECTS: creates a new ReminderItem based on the user's input
    private void makeNewItem(ListOfItems listOfItems) throws FileNotFoundException, UnsupportedEncodingException {
        while (true) {
            System.out.println(listOfItems.getPromptTitle());
            String reminder = takeInput.nextLine();
            System.out.println(listOfItems.getPromptAttribute());
            String time = takeInput.nextLine();
            try {
                listOfItems.addNewItem(listOfItems.itemMaker(reminder, time));
                System.out.println(listOfItems.getPromptAnother());
                String choice = takeInput.nextLine();
                if (choice.equals("n")) {
                    itemOptions.processOptions(listOfItems);
                    break;
                }
            } catch (TooManyItemsUndoneException e) {
                tooManyItems(listOfItems);
                break;
            }
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: gives the user the choice to delete or mark an item as done after being prompted that they have
    //         too many items that aren't done
    private void tooManyItems(ListOfItems listOfItems) throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("You have too many items undone, delete or mark an item? (y|n)");
        String choice = takeInput.nextLine();
        if (choice.equals("y")) {
            itemOptions.processOptions(listOfItems);
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: prints out the list of items
    public void printItemList(ListOfItems listOfItems) throws FileNotFoundException, UnsupportedEncodingException {
        if (listOfItems.getSize() == 0) {
            System.out.println("You don't have anything in that list!");
        } else {
            System.out.println(listOfItems.print());
            itemOptions.processOptions(listOfItems);
        }
    }
}
