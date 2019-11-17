package ui;

import exceptions.ItemDoesNotExistException;
import model.ListOfItems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class ItemOptions implements ActionListener {


    private Window window;
    private ListOfItems listOfItems;

    public ItemOptions(Window window, ListOfItems listOfItems) {

        this.window = window;
        this.listOfItems = listOfItems;

        addListeners();
    }

    private void addListeners() {
        if (window.getDelete().getActionListeners().length == 0) {
            window.getDelete().addActionListener(this);
        }
        if (window.getMark().getActionListeners().length == 0) {
            window.getMark().addActionListener(this);
        }
        if (window.getDontDeleteOrMark().getActionListeners().length == 0) {
            window.getDontDeleteOrMark().addActionListener(this);
        }
    }


    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: prompts the user the option to delete or change the status of an item
    public void processOptions() {
        window.layoutForItemOptions();
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: asks the user if they wish to remove an item from the list
    private void delete() throws FileNotFoundException, UnsupportedEncodingException {
        int index = Integer.parseInt(window.getIndexTextBox().getText());
        try {
            listOfItems.remove(index);
        } catch (ItemDoesNotExistException e) {
            System.out.println("Invalid index, try again");
        } finally {
            System.out.println(listOfItems.print());
            printList();
            listOfItems.save();
        }

    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: asks the user if they wish to change the status of an item in the list
    private void mark() throws FileNotFoundException, UnsupportedEncodingException {
        int index = Integer.parseInt(window.getIndexTextBox().getText());
        try {
            listOfItems.changeStatus(index);
        } catch (ItemDoesNotExistException e) {
            System.out.println("Invalid index, try again");
        } finally {
            System.out.println(listOfItems.print());
            printList();
            listOfItems.save();
        }
    }

    public void printList() {
        ArrayList<String> log = listOfItems.print();
        window.getDisplayLabel().setText(log.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand().equals("delete")) {
                delete();
//                System.out.println("Count of listeners: " + ((JButton) e.getSource()).getActionListeners().length);
            } else if (e.getActionCommand().equals("mark")) {
                mark();
            } else if (e.getActionCommand().equals("neither")) {
                printList();
                window.layoutInitial();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
