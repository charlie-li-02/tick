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
    private ItemHandler itemHandler;
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
        setListeners();
        setPrintListListeners();
        loadFiles();
        displayWeather();
    }

    private void initializeComponents() {
        listOfToDo = new ListOfToDo();
        listOfReminder = new ListOfReminder();
        homeworkList = new HomeworkList();
        homeworkHandler = new HomeworkHandler();
        homeworkOptions = new HomeworkOptions();
        window = new Window();
        window.initializeGraphics();
    }

    private void setListeners() {
        if (window.getButton1().getActionListeners().length == 0) {
            window.getButton1().addActionListener(this);
        }
        if (window.getButton2().getActionListeners().length == 0) {
            window.getButton2().addActionListener(this);
        }
        if (window.getButton3().getActionListeners().length == 0) {
            window.getButton3().addActionListener(this);
        }
        if (window.getButton4().getActionListeners().length == 0) {
            window.getButton4().addActionListener(this);
        }
        if (window.getButton5().getActionListeners().length == 0) {
            window.getButton5().addActionListener(this);
        }
        if (window.getButton6().getActionListeners().length == 0) {
            window.getButton6().addActionListener(this);
        }
    }

    private void setPrintListListeners() {
        if (window.getToDo().getActionListeners().length == 0) {
            window.getToDo().addActionListener(this);
        }
        if (window.getReminder().getActionListeners().length == 0) {
            window.getReminder().addActionListener(this);
        }
        if (window.getHomework().getActionListeners().length == 0) {
            window.getHomework().addActionListener(this);
        }
        for (ActionListener ae: window.getBack().getActionListeners()) {
            if (ae.equals(this)) {
                window.getBack().removeActionListener(this);
            }
        }
        window.getBack().addActionListener(this);
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
            //chooseList();
            window.layoutForShowLists();

        } else if (action.equals("")) {
            System.out.println("Try again");
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: lets the user choose which list to print out
    private void chooseList() throws IOException {
//        System.out.println("Which list would you like to open?\n" + "1 - to do list\n"
//                + "2 - reminders list\n" + "3 - homework list\n");
//        String choice = takeInput.nextLine();
        try {
            if (action.equals("to do")) {
                itemHandler.printItemList(listOfToDo);
            } else if (action.equals("reminder")) {
                itemHandler.printItemList(listOfReminder);
            } else if (action.equals("homework")) {
                homeworkOptions.printHomeworkList(homeworkList);
                homeworkHandler.pickHomeworkList(homeworkList);
            }
        } catch (NullPointerException e) {
            window.getDisplayLabel().setText("You don't have anything in that list!");
            window.getDisplayLabel().setVisible(true);
            window.getBack().setVisible(true);
        }
    }

    private void displayWeather() throws IOException {
        weatherHandler = new WeatherHandler(window);
        weatherHandler.displayWeather();
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: instantiates a new ToDoList and starts the program
    public static void main(String[] args) throws IOException {
        new AppRunner();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        action = e.getActionCommand();
        try {
            if (action.equals("1") | action.equals("2") | action.equals("3")
                    | action.equals("4") | action.equals("5") | action.equals("")) {
                optionLoop();
            } else if (action.equals("to do") | action.equals("reminder") | action.equals("homework")) {
                chooseList();
            }
            if (action.equals("return")) {
                window.layoutInitial();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
