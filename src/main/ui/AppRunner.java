package ui;

import model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class AppRunner {

    private ListOfItems listOfToDo;
    private ListOfItems listOfReminder;
    private HomeworkList homeworkList;
    private Scanner takeInput;
    private ItemHandler itemHandler;
    private ItemOptions itemOptions;
    private HomeworkHandler homeworkHandler;
    private HomeworkOptions homeworkOptions;
    private WeatherHandler weatherHandler;

    // bunch of strings for input options because too long for check style
    private static final String optionP1 = "Please enter one of:\n";
    private static final String optionP2 = "1 - add a to do\n";
    private static final String optionP3 = "2 - add a reminder\n";
    private static final String optionP4 = "3 - new homework list\n";
    private static final String optionP5 = "4 - view current lists\n";
    private static final String optionP6 = "5 - show me the weather";


    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: constructor for ToDoList
    private AppRunner() throws IOException {
        takeInput = new Scanner(System.in);
        listOfToDo = new ListOfToDo();
        listOfReminder = new ListOfReminder();
        homeworkList = new HomeworkList();
        itemHandler = new ItemHandler();
        itemOptions = new ItemOptions();
        homeworkHandler = new HomeworkHandler();
        homeworkOptions = new HomeworkOptions();
        weatherHandler = new WeatherHandler();

        listOfToDo.load();
        listOfReminder.load();
        homeworkList.load();
        processInput();

    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: gives the user the choice of adding a to do, reminder, show current lists, or close the program
    private void processInput() throws IOException {
        String type;
        while (true) {
            System.out.println(optionP1 + optionP2 + optionP3 + optionP4 + optionP5 + optionP6);
            type = takeInput.nextLine();

            if (type.equals("5")) {
                //break;
                weatherHandler.displayWeather();

            } else if (type.equals("1")) {
                itemHandler.startItem(listOfToDo);

            } else if (type.equals("2")) {
                itemHandler.startItem(listOfReminder);

            } else if (type.equals("3")) {
                homeworkHandler.makeNewHomework(homeworkList);

            } else if (type.equals("4")) {
                chooseList();

            } else {
                System.out.println("Your entrance did not match any options, please try again. \n");
            }
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: lets the user choose which list to print out
    private void chooseList() throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("Which list would you like to open?\n" + "1 - to do list\n"
                + "2 - reminders list\n" + "3 - homework list\n");
        String choice = takeInput.nextLine();
        if (choice.equals("1")) {
            itemHandler.printItemList(listOfToDo);
        } else if (choice.equals("2")) {
            itemHandler.printItemList(listOfReminder);
        } else if (choice.equals("3")) {
            homeworkOptions.printHomeworkList(homeworkList);
            homeworkHandler.pickHomeworkList(homeworkList);
        }
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: instantiates a new ToDoList and starts the program
    public static void main(String[] args) throws IOException {
        WeatherHandler initialWeatherHandler = new WeatherHandler();
        initialWeatherHandler.displayWeather();

        new AppRunner();
    }
}
