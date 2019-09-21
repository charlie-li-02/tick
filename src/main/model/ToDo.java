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
        System.out.println("Enter a title for your new to do:");
        String title = input.nextLine();
        System.out.println("Enter a description for your new to do:");
        String description = input.nextLine();
        ToDoItem tdi = new ToDoItem(title, description, false);
        addToDo(tdi);
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds the ToDoItem onto the list of to do items
    private void addToDo(ToDoItem tdi) {
        this.listOfTDI.add(tdi);
    }
}
