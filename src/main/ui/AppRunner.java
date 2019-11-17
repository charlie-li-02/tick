package ui;

import model.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AppRunner implements ActionListener {

    private ListOfItems listOfToDo;
    private ListOfItems listOfReminder;
    private HomeworkList homeworkList;
    private ItemHandler toDoHandler;
    private ItemHandler reminderHandler;
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
        toDoHandler = new ItemHandler(window, listOfToDo);
        reminderHandler = new ItemHandler(window, listOfReminder);
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
        for (ActionListener ae : window.getBack().getActionListeners()) {
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

    private void optionLoop() throws IOException {
        if (action.equals("5")) {
            weatherHandler.displayWeather();

        } else if (action.equals("1")) {
            //toDoHandler = new ItemHandler(window, listOfToDo);
            toDoHandler.startItem();
            toDoHandler.addListeners(toDoHandler);

        } else if (action.equals("2")) {
            reminderHandler.startItem();
            reminderHandler.addListeners(reminderHandler);

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
        if (action.equals("to do")) {
            toDoHandler.printItemList(listOfToDo);
        } else if (action.equals("reminder")) {
            reminderHandler.printItemList(listOfReminder);
        } else if (action.equals("homework")) {
            homeworkOptions.printHomeworkList(homeworkList);
            homeworkHandler.pickHomeworkList(homeworkList);
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
