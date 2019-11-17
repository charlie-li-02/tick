package ui;

import exceptions.TooManyItemsUndoneException;
import model.ListOfItems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class ItemHandler implements ActionListener {
    private ItemOptions itemOptions;
    private Scanner takeInput;
    private Window window;
    private ListOfItems listOfItems;

    public ItemHandler(Window window, ListOfItems listOfItems) {
        takeInput = new Scanner(System.in);
        itemOptions = new ItemOptions(window, listOfItems);
        this.window = window;
        this.listOfItems = listOfItems;
    }

    public void addListeners(ItemHandler itemHandler) {
        for (ActionListener ae: window.getYes().getActionListeners()) {
            window.getYes().removeActionListener(ae);
        }
        for (ActionListener ae: window.getNo().getActionListeners()) {
            window.getNo().removeActionListener(ae);
        }
        for (ActionListener ae: window.getEnter().getActionListeners()) {
            window.getEnter().removeActionListener(ae);
        }
        for (ActionListener ae: window.getBack().getActionListeners()) {
            if (ae.equals(itemHandler)) {
                window.getBack().removeActionListener(itemHandler);
            }
        }
        window.getYes().addActionListener(itemHandler);
        window.getNo().addActionListener(itemHandler);
        window.getEnter().addActionListener(itemHandler);
        window.getBack().addActionListener(itemHandler);
    }

    //REQUIRES: nothing
    //MODIFIES: listOfItems, save file of the list passed in
    //EFFECTS: starts off the processing of adding a item
    public void startItem() {
        window.layoutForAddItem();
        String promptTitle = listOfItems.getPromptTitle();
        window.getMainLabel().setText(promptTitle);

    }

    //REQUIRES: nothing
    //MODIFIES: RemainderItem
    //EFFECTS: creates a new Item based on the user's input
    private void makeNewItem(String title, String attribute) throws IOException {
        try {
            listOfItems.addNewItem(listOfItems.itemMaker(title, attribute));
            itemOptions.printList();
            String addAnother = listOfItems.getPromptAnother();
            window.getMainLabel().setText(addAnother);
            window.layoutForAddAnotherItem();
        } catch (TooManyItemsUndoneException e) {
            tooManyItems();
        } finally {
            listOfItems.save();
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: gives the user the choice to delete or mark an item as done after being prompted that they have
    //         too many items that aren't done
    private void tooManyItems() {
        System.out.println("You have too many items undone, delete or mark an item? (y|n)");
        String choice = takeInput.nextLine();
        if (choice.equals("y")) {
            itemOptions.processOptions();
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: prints out the list of items
    public void printItemList(ListOfItems listOfItems) {
        if (listOfItems.getSize() == 0) {
            window.getDisplayLabel().setText("You don't have anything in that list!");
            window.getDisplayLabel().setVisible(true);
            window.getBack().setVisible(true);
        } else {
            ArrayList<String> log = listOfItems.print();
            window.getDisplayLabel().setText(log.toString());
            window.getDisplayLabel().setVisible(true);
            itemOptions.processOptions();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand().equals("enter")) {
                enterEvent();
            }

            if (e.getActionCommand().equals("yes")) {
                startItem();
            }

            if (e.getActionCommand().equals("no")) {
                itemOptions.processOptions();
            }

            if (e.getActionCommand().equals("return")) {
                window.layoutInitial();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void enterEvent() throws IOException {
        String title = window.getTitleTextBox().getText();
        String attribute = window.getAttributeTextBox().getText();
        makeNewItem(title, attribute);
    }
}
