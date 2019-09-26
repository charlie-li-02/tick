package ui;

import model.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ToDoList {

    public ArrayList<Item> listOfToDo;
    public ArrayList<Item> listOfReminder;
    private Scanner takeInput;

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: constructor for ToDoList
    public ToDoList() {
        listOfToDo = new ArrayList<>();
        listOfReminder = new ArrayList<>();
        takeInput = new Scanner(System.in);
        processInput();
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: gives the user the choice of adding a to do, reminder, show current lists, or close the program
    public void processInput() {
        String type;
        while (true) {
            System.out.println("Add a new to do, reminder, show current lists, or exit (to do/reminder/list/exit):");
            type = takeInput.nextLine();

            if (type.equals("exit")) {
                break;

            } else if (type.equals("to do")) {
                new ListOfToDo(listOfToDo);

            } else if (type.equals("reminder")) {
                new ListOfReminder(listOfReminder);

            } else if (type.equals("list")) {
                processOutput();

            } else {
                System.out.println("Your entrance did not match any options, please try again. \n");
            }
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: prints out the appropriate list or a warning that the user made a typo
    public void processOutput() {
        System.out.println("Which list would you like to open? (to do/reminder)");
        String choice = takeInput.nextLine();
        if (choice.equals("to do")) {
            System.out.println(listOfToDo);

        } else if (choice.equals("reminder")) {
            System.out.println(listOfReminder);

        } else {
            System.out.println("Your entrance did not match any options, please try again. \n");
        }
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: instantiates a new ToDoList and starts the program
    public static void main(String[] args) {
        new ToDoList();
    }
}
