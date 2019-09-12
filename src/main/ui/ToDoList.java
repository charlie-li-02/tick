package ui;

import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {

    private ArrayList<LogToDo> listToDo;
    private ArrayList<LogReminder> listReminder;
    private Scanner takeInput;

    public ToDoList() {
        listToDo = new ArrayList<>();
        listReminder = new ArrayList<>();
        takeInput = new Scanner(System.in);
        processInput();
    }

    private void processInput() {
        String type;
        while (true) {
            System.out.println("Add a new to do, reminder, show current lists, or exit (to do/reminder/list/exit):");
            type = takeInput.nextLine();

            if (type.equals("exit")) {
                break;

            } else if (type.equals("to do")) {
                LogToDo ltd = new LogToDo();
                processToDo(ltd);

            } else if (type.equals("reminder")) {
                LogReminder lr = new LogReminder();
                processReminder(lr);

            } else if (type.equals("list")) {
                processOutput();

            } else {
                System.out.println("Your entrance did not match any options, please try again. \n");
            }
        }
    }

    private String processToDo(LogToDo logToDo) {
        System.out.println("Enter a title for your new to do:");
        String title = takeInput.nextLine();
        System.out.println("Enter a description for your new to do:");
        String description = takeInput.nextLine();
        addToDo(logToDo, title, description);
        return ("Title: " + title + "\nDescription: " + description);
    }

    private void addToDo(LogToDo logToDo, String title, String description) {
        logToDo.toDoMaker(title, description);
        listToDo.add(logToDo);
    }

    private String processReminder(LogReminder logReminder) {
        System.out.println("Enter a new reminder:");
        String reminder = takeInput.nextLine();
        System.out.println("Enter a time for your new reminder:");
        String time = takeInput.nextLine();
        addReminder(logReminder, reminder, time);
        return ("Remind me to: " + reminder + " at " + time);
    }

    private void addReminder(LogReminder logReminder, String reminder, String time) {
        logReminder.reminderMaker(reminder, time);
        listReminder.add(logReminder);
    }

    private void processOutput() {
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
