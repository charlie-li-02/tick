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
        displayLists();
    }

    private void initializeComponents() throws IOException {
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
        if (window.getAddToDoButton().getActionListeners().length == 0) {
            window.getAddToDoButton().addActionListener(this);
        }
        if (window.getAddReminderButton().getActionListeners().length == 0) {
            window.getAddReminderButton().addActionListener(this);
        }
//        if (window.getButton3().getActionListeners().length == 0) {
//            window.getButton3().addActionListener(this);
//        }
        if (window.getEditListsButton().getActionListeners().length == 0) {
            window.getEditListsButton().addActionListener(this);
        }
        if (window.getWeatherButton().getActionListeners().length == 0) {
            window.getWeatherButton().addActionListener(this);
        }
        if (window.getExitButton().getActionListeners().length == 0) {
            window.getExitButton().addActionListener(this);
        }
    }

    private void setPrintListListeners() {
        if (window.getToDo().getActionListeners().length == 0) {
            window.getToDo().addActionListener(this);
        }
        if (window.getReminder().getActionListeners().length == 0) {
            window.getReminder().addActionListener(this);
        }
//        if (window.getHomework().getActionListeners().length == 0) {
//            window.getHomework().addActionListener(this);
//        }
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

    private void displayLists() {
        window.display(listOfToDo);
        window.display(listOfReminder);
    }

    private void optionLoop() throws IOException {
        if (action.equals("weather")) {
            weatherHandler.displayWeather();

        } else if (action.equals("add to do")) {
            toDoHandler.startItem();
            toDoHandler.addListeners(toDoHandler);

        } else if (action.equals("add reminder")) {
            reminderHandler.startItem();
            reminderHandler.addListeners(reminderHandler);

        } else if (action.equals("edit lists")) {
            window.layoutForEditLists();

//            else if (action.equals("3")) {
//            homeworkHandler.makeNewHomework(homeworkList);
//        }

        } else if (action.equals("exit")) {
            listOfToDo.save();
            listOfReminder.save();
            System.exit(1);
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

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS; listens to 9 different buttons and calls the corresponding methods
    @Override
    public void actionPerformed(ActionEvent e) {
        action = e.getActionCommand();
        try {
            if (action.equals("add to do") | action.equals("add reminder") | action.equals("edit lists")
                    | action.equals("weather") | action.equals("exit")) {
                window.getWarningLabel().setVisible(false);
                optionLoop();
            } else if (action.equals("to do") | action.equals("reminder") | action.equals("homework")) {
                window.getWarningLabel().setVisible(false);
                chooseList();
            }
            if (action.equals("return")) {
                window.display(listOfToDo);
                window.display(listOfReminder);
                window.layoutInitial();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
