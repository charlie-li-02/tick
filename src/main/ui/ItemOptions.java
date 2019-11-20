package ui;

import exceptions.ItemDoesNotExistException;
import model.ListOfItems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ItemOptions implements ActionListener {


    private Window window;
    private ListOfItems listOfItems;

    public ItemOptions(Window window, ListOfItems listOfItems) {

        this.window = window;
        this.listOfItems = listOfItems;

    }

    //REQUIRES: nothing
    //MODIFIES: Window, delete button, mark button, dont delete or mark button
    //EFFECTS: adds the passed in instance of ItemOptions as the only listener for the 3 buttons modified
    public void setListener(ItemOptions itemOptions) {
        for (ActionListener ae: window.getDelete().getActionListeners()) {
            window.getDelete().removeActionListener(ae);
        }
        for (ActionListener ae: window.getMark().getActionListeners()) {
            window.getMark().removeActionListener(ae);
        }
//        for (ActionListener ae: window.getDontDeleteOrMark().getActionListeners()) {
//            window.getDontDeleteOrMark().removeActionListener(ae);
//        }
        window.getDelete().addActionListener(itemOptions);
        window.getMark().addActionListener(itemOptions);
//        window.getDontDeleteOrMark().addActionListener(itemOptions);
    }


//    REQUIRES: nothing
//    MODIFIES: nothing
//    EFFECTS: prompts the user the option to delete or change the status of an item
    public void processOptions(ListOfItems listOfItems) {
        this.listOfItems = listOfItems;
        window.layoutForItemOptions();
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: asks the user if they wish to remove an item from the list
    private void delete() throws FileNotFoundException, UnsupportedEncodingException {
        try {
            int index = Integer.parseInt(window.getIndexTextBox().getText());
            window.getWarningLabel().setVisible(false);
            listOfItems.remove(index - 1);
            window.getIndexTextBox().setText("");
        } catch (ItemDoesNotExistException | NumberFormatException e) {
            window.getWarningLabel().setText("Invalid index, try again");
            window.getWarningLabel().setVisible(true);
        } finally {
            window.display(listOfItems);
            listOfItems.save();
        }

    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: asks the user if they wish to change the status of an item in the list
    private void mark() throws FileNotFoundException, UnsupportedEncodingException {
        try {
            int index = Integer.parseInt(window.getIndexTextBox().getText());
            window.getWarningLabel().setVisible(false);
            listOfItems.changeStatus(index - 1);
            window.getIndexTextBox().setText("");
        } catch (ItemDoesNotExistException | NumberFormatException e) {
            window.getWarningLabel().setText("Invalid index, try again");
            window.getWarningLabel().setVisible(true);
        } finally {
            window.display(listOfItems);
            listOfItems.save();
        }
    }


    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: listens to the 3 buttons and called the corresponding methods
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand().equals("delete")) {
                delete();
            } else if (e.getActionCommand().equals("mark")) {
                mark();
            }
//            else if (e.getActionCommand().equals("neither")) {
//                window.display(listOfItems);
//                window.layoutInitial();
//            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
