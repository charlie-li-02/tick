package model;

import java.util.Scanner;
import java.util.ArrayList;

public class ToDo {

    private ArrayList<ToDoItem> listOfTDI;
    private Scanner input;

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: the constructor for To Do
    public ToDo(ArrayList<ToDoItem> ltdi) {
        this.listOfTDI = ltdi;
        input = new Scanner(System.in);
        processToDo();
    }

    //REQUIRES: nothing
    //MODIFIES: ToDoItem
    //EFFECTS: creates a new ToDoItem based on the user's input
    private void processToDo() {
        while (true) {
            System.out.println("Enter a title for your new to do:");
            String title = input.nextLine();
            System.out.println("Enter a description for your new to do:");
            String description = input.nextLine();
            ToDoItem tdi = new ToDoItem(title, description, false);
            addToDo(tdi);
            System.out.println("Do you want to add another to do? (y/n)");
            if (input.nextLine().equals("n")) {
                break;
            }
        }
        processOptions();
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds the ToDoItem onto the list of to do items
    private void addToDo(ToDoItem tdi) {
        this.listOfTDI.add(tdi);
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: prompts the user the option to delete or change the status of a reminder
    private void processOptions() {
        System.out.println("Would you like to delete an item or change an item's status? (delete/mark/no)");
        String choice = input.nextLine();
        if (choice.equals("delete")) {
            delete();
            System.out.println(listOfTDI);
        } else if (choice.equals("mark")) {
            mark();
            System.out.println(listOfTDI);
        } else {
            System.out.println(listOfTDI);
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: asks the user if they wish to remove an item from the list
    private void delete() {
        System.out.println("Which item would you like to remove? (enter an integer)");
        int i = input.nextInt();
        if (i + 1 > this.listOfTDI.size()) {
            System.out.println("Invalid index");
        } else {
            remove(i);
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: asks the user if they wish to change the status of an item in the list
    private void mark() {
        System.out.println("Which item's status would you like to change? (enter an integer)");
        int i = input.nextInt();
        if (i + 1 > this.listOfTDI.size()) {
            System.out.println("Invalid index");
        } else {
            //flipStatus(get(i));
            get(i).flipStatus();
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the ith ToDoItem
    public ToDoItem get(int i) {
        return this.listOfTDI.get(i);
    }

    //REQUIRES: at least one item in the array
    //MODIFIES: this
    //EFFECTS: removes the ith ToDoItem
    public ToDoItem remove(int i) {
        return this.listOfTDI.remove(i);
    }

}

