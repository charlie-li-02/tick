package ui;

import exceptions.TooManyItemsUndoneException;
import model.ListOfItems;
import model.ListOfReminder;
import model.ListOfToDo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class ItemHandler implements ActionListener {
    private ItemOptions toDoOptions;
    private ItemOptions reminderOptions;
    private Window window;
    private ListOfItems listOfItems;
    private Boolean isToDo;

    public ItemHandler(Window window, ListOfItems listOfItems) {
        this.window = window;
        this.listOfItems = listOfItems;
        if (listOfItems instanceof ListOfToDo) {
            toDoOptions = new ItemOptions(window, listOfItems);
            isToDo = true;
        }
        if (listOfItems instanceof ListOfReminder) {
            reminderOptions = new ItemOptions(window, listOfItems);
            isToDo = false;
        }
    }

    //REQUIRES: nothing
    //MODIFIES: Window, yes button, no button, enter button, back button
    //EFFECTS: adds the passed in instance of ItemHandler as the only listener for the 4 buttons modified
    public void addListeners(ItemHandler itemHandler) {
        for (ActionListener ae: window.getEnter().getActionListeners()) {
            window.getEnter().removeActionListener(ae);
        }
        for (ActionListener ae: window.getBack().getActionListeners()) {
            if (ae.equals(itemHandler)) {
                window.getBack().removeActionListener(itemHandler);
            }
        }
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
            window.display(listOfItems);
            String addAnother = listOfItems.getPromptAnother();
            window.getMainLabel().setText(addAnother);
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
        window.getWarningLabel().setText("You have too many items undone, delete or mark an item?");
        window.getWarningLabel().setVisible(true);
        window.getTitleTextBox().setVisible(false);
        window.getAttributeTextBox().setVisible(false);
        whichList();
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: prints out the list of items
    public void printItemList(ListOfItems listOfItems) {
        if (listOfItems.getSize() == 0) {
            window.getWarningLabel().setText("There is nothing in that list");
            window.getWarningLabel().setVisible(true);
            window.display(listOfItems);
            window.layoutInitial();
        } else {
            window.getWarningLabel().setVisible(false);
            window.display(listOfItems);
            whichList();
        }
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: listens to the 4 buttons and calls the corresponding methods when buttons are pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand().equals("enter")) {
                enterEvent();
            }

            if (e.getActionCommand().equals("return")) {
                window.layoutInitial();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void whichList() {
        if (isToDo) {
            toDoOptions.processOptions(listOfItems);
            toDoOptions.setListener(toDoOptions);
        } else {
            reminderOptions.processOptions(listOfItems);
            reminderOptions.setListener(reminderOptions);
        }
    }

    private void enterEvent() throws IOException {
        String title = window.getTitleTextBox().getText();
        String attribute = window.getAttributeTextBox().getText();
        window.getTitleTextBox().setText("");
        window.getAttributeTextBox().setText("");
        makeNewItem(title, attribute);
    }
}
