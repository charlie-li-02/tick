package ui;

import exceptions.TooManyItemsUndoneException;
import model.ListOfItems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;


public class ItemHandler implements ActionListener {
    private ItemOptions itemOptions;
    private Scanner takeInput;
    private Window window;
    private ListOfItems listOfItems;

    public ItemHandler(Window window, ListOfItems listOfItems) throws IOException {
        takeInput = new Scanner(System.in);
        itemOptions = new ItemOptions(window, listOfItems);
        this.window = window;
        addListeners();
        this.listOfItems = listOfItems;
        startItem();
    }

    private void addListeners() {
        if (window.getYes().getActionListeners().length == 0) {
            window.getYes().addActionListener(this);
        }
        if (window.getNo().getActionListeners().length == 0) {
            window.getNo().addActionListener(this);
        }
        if (window.getEnter().getActionListeners().length == 0) {
            window.getEnter().addActionListener(this);
        }
    }

    //REQUIRES: nothing
    //MODIFIES: listOfItems, save file of the list passed in
    //EFFECTS: starts off the processing of adding a item
    public void startItem() throws IOException {
        window.layoutForAddItem();
        String promptTitle = listOfItems.getPromptTitle();
        window.getMainLabel().setText(promptTitle);
        listOfItems.save();
    }

    //REQUIRES: nothing
    //MODIFIES: RemainderItem
    //EFFECTS: creates a new Item based on the user's input
    private void makeNewItem(
            ListOfItems listOfItems, String title, String attribute
    ) throws FileNotFoundException, UnsupportedEncodingException {
        try {
            listOfItems.addNewItem(listOfItems.itemMaker(title, attribute));
            String addAnother = listOfItems.getPromptAnother();
            window.getMainLabel().setText(addAnother);
            window.layoutForAddAnotherItem();
            itemOptions.printList();
//                String choice = takeInput.nextLine();
//                if (choice.equals("n")) {
//                    itemOptions.processOptions(listOfItems);
//                    //break;
//                }
        } catch (TooManyItemsUndoneException e) {
            tooManyItems(listOfItems);
            //break;
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
            itemOptions.processOptions();
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
            itemOptions.processOptions();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand().equals("enter")) {
                String title = window.getTitleTextBox().getText();
                String attribute = window.getAttributeTextBox().getText();
                makeNewItem(listOfItems, title, attribute);
                //itemOptions.printList();
            }

            if (e.getActionCommand().equals("yes")) {
                startItem();
            }

            if (e.getActionCommand().equals("no")) {
                itemOptions.processOptions();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
