package ui;

import model.ReminderItem;
import model.ToDoItem;

import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {

    public ArrayList<ToDoItem> listToDo;
    public ArrayList<ReminderItem> listReminder;
    private Scanner takeInput;

    public ToDoList() {
        listToDo = new ArrayList<>();
        listReminder = new ArrayList<>();
        takeInput = new Scanner(System.in);
        processInput();
    }

    public void processInput() {
        String type;
        while (true) {
            System.out.println("Add a new to do, reminder, show current lists, or exit (to do/reminder/list/exit):");
            type = takeInput.nextLine();

            if (type.equals("exit")) {
                break;

            } else if (type.equals("to do")) {
                new model.ToDo(listToDo);

            } else if (type.equals("reminder")) {
                new model.Reminder(listReminder);

            } else if (type.equals("list")) {
                processOutput();

            } else {
                System.out.println("Your entrance did not match any options, please try again. \n");
            }
        }
    }

    public void processOutput() {
        System.out.println("Which list would you like to open? (to do/reminder)");
        String choice = takeInput.nextLine();
        if (choice.equals("to do")) {
            System.out.println(listToDo);

        } else if (choice.equals("reminder")) {
            System.out.println(listReminder);

        } else {
            System.out.println("Your entrance did not match any options, please try again. \n");
        }
    }


    public static void main(String[] args) {
        new ToDoList();
    }
}
