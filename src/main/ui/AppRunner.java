package ui;

import model.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class AppRunner implements ActionListener {

    private ListOfItems listOfToDo;
    private ListOfItems listOfReminder;
    private HomeworkList homeworkList;
    private Scanner takeInput;
    private ItemHandler itemHandler;
    private ItemOptions itemOptions;
    private HomeworkHandler homeworkHandler;
    private HomeworkOptions homeworkOptions;
    private WeatherHandler weatherHandler;

    private Window window;
    private String action = "";


    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: constructor for ToDoList
    private AppRunner() throws IOException {
        initializeComponents();

        loadFiles();
    }

    private void initializeComponents() {
        takeInput = new Scanner(System.in);
        listOfToDo = new ListOfToDo();
        listOfReminder = new ListOfReminder();
        homeworkList = new HomeworkList();
        //itemHandler = new ItemHandler(window);
        itemOptions = new ItemOptions();
        homeworkHandler = new HomeworkHandler();
        homeworkOptions = new HomeworkOptions();
        weatherHandler = new WeatherHandler();
        window = new Window(this);
        window.initializeGraphics();
    }

    private void loadFiles() throws IOException {
        listOfToDo.load();
        listOfReminder.load();
        homeworkList.load();
    }


//    //REQUIRES: nothing
//    //MODIFIES: nothing
//    //EFFECTS: gives the user the choice of adding a to do, reminder, show current lists, or close the program
//    private void processInput() throws IOException {
//        String type;
//        while (true) {
//            System.out.println(optionP1 + optionP2 + optionP3 + optionP4 + optionP5 + optionP6 + optionP7);
//            type = takeInput.nextLine();
//            if (type.equals("6")) {
//                break;
//            }
//            optionLoop(type);
//        }
//    }

    private void optionLoop() throws IOException {
        if (action.equals("5")) {
            weatherHandler.displayWeather();

        } else if (action.equals("1")) {
            itemHandler = new ItemHandler(window, listOfToDo);

        } else if (action.equals("2")) {
            itemHandler = new ItemHandler(window, listOfReminder);

        } else if (action.equals("3")) {
            homeworkHandler.makeNewHomework(homeworkList);

        } else if (action.equals("4")) {
            chooseList();

        } else if (action.equals("")) {
            System.out.println("Try again");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        action = e.getActionCommand();
        try {
            optionLoop();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
