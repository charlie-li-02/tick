package model;

import java.util.Scanner;
import java.util.ArrayList;

public class ListOfToDo implements List {

    private ArrayList<Item> listOfTDI;
    private Scanner input;

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: the constructor for To Do
    public ListOfToDo(ArrayList<Item> ltdi) {
        this.listOfTDI = ltdi;
        input = new Scanner(System.in);
        processInput();
    }

    //REQUIRES: nothing
    //MODIFIES: ToDoItem
    //EFFECTS: creates a new ToDoItem based on the user's input
    private void processInput() {
        while (true) {
            System.out.println("Enter a title for your new to do:");
            String title = input.nextLine();
            System.out.println("Enter a description for your new to do:");
            String description = input.nextLine();
            Item tdi = new ToDoItem(title, description, false);
            addItem(tdi);
            System.out.println("Do you want to add another to do? (y/n)");
            if (input.nextLine().equals("n")) {
                break;
            }
        }
        processOptions();
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds the Item onto the list of to do items
    private void addItem(Item tdi) {
        this.listOfTDI.add(tdi);
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: prompts the user the option to delete or change the status of a to do
    private void processOptions() {
        while (listOfTDI.size() > 0) {
            System.out.println("Would you like to delete an item or change an item's status? (delete/mark/no)");
            String choice = input.nextLine();
            if (choice.equals("delete")) {
                delete();
                System.out.println(listOfTDI);
            } else if (choice.equals("mark")) {
                mark();
                System.out.println(listOfTDI);
            } else if (choice.equals("no")) {
                System.out.println(listOfTDI);
                break;
            } else {
                System.out.println("Try again");
            }
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: asks the user if they wish to remove an item from the list
    private void delete() {
        System.out.println(listOfTDI);
        System.out.println("Which item would you like to remove? (enter an integer)");
        int i = input.nextInt();
        input.nextLine();
        if (i + 1 > this.listOfTDI.size()) {
            System.out.println("Invalid index, try again");
        } else {
            remove(i);
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: asks the user if they wish to change the status of an item in the list
    private void mark() {
        System.out.println(listOfTDI);
        System.out.println("Which item's status would you like to change? (enter an integer)");
        int i = input.nextInt();
        input.nextLine();
        if (i + 1 > this.listOfTDI.size()) {
            System.out.println("Invalid index, try again");
        } else {
            //flipStatus(get(i));
            get(i).flipStatus();
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the ith Item
    public Item get(int i) {
        return this.listOfTDI.get(i);
    }

    //REQUIRES: at least one item in the array
    //MODIFIES: this
    //EFFECTS: removes the ith Item
    public Item remove(int i) {
        return this.listOfTDI.remove(i);
    }

}

