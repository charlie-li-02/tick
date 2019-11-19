package ui;

import model.ListOfItems;
import model.ListOfReminder;
import model.ListOfToDo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Window extends JFrame {

    //LABELS
    private JLabel mainLabel;
    private JLabel weatherLabel;
    private JLabel temperatureLabel;
    private JLabel enterTitleLabel;
    private JLabel enterDescriptionLabel;
    private JLabel toDoTitleLabel;
    private JLabel reminderTitleLabel;
    private JLabel nothingInToDoLabel;
    private JLabel nothingInReminderLabel;
    private JLabel warningLabel;

    //TEXT BOXES
    private JTextField titleTextBox;
    private JTextField attributeTextBox;
    private JTextField indexTextBox;

    //BUTTONS
    private JButton addToDoButton;
    private JButton addReminderButton;
    //private JButton button3;
    private JButton editListsButton;
    private JButton weatherButton;
    private JButton exitButton;
    private JButton enter;
    private JButton yes;
    private JButton no;
    private JButton delete;
    private JButton mark;
    private JButton dontDeleteOrMark;
    private JButton toDo;
    private JButton reminder;
    //private JButton homework;
    private JButton back;

    //TEXT FOR BUTTONS
    private static final String pleaseSelect = "Please select one of:";


    private ArrayList<JLabel> listOfToDoLabels = new ArrayList<>();
    private ArrayList<JLabel> listOfReminderLabels = new ArrayList<>();


    public Window() {
        super("Tick");

        mainLabel = new JLabel("");
        weatherLabel = new JLabel("");
        temperatureLabel = new JLabel("");
        enterTitleLabel = new JLabel("Title:");
        enterDescriptionLabel = new JLabel("Description/Time:");
        toDoTitleLabel = new JLabel("To Dos:");
        reminderTitleLabel = new JLabel("Reminders:");
        nothingInToDoLabel = new JLabel("You have no to dos");
        nothingInReminderLabel = new JLabel("You have no reminders");
        warningLabel = new JLabel("");

        titleTextBox = new JTextField();
        attributeTextBox = new JTextField();
        indexTextBox = new JTextField();

        setButtonNames();
    }

    private void setButtonNames() {
        addToDoButton = new JButton("Add a to do");
        addReminderButton = new JButton("Add a reminder");
        //button3 = new JButton("New homework list");
        editListsButton = new JButton("Edit current lists");
        weatherButton = new JButton("Refresh weather");
        exitButton = new JButton("Exit");
        enter = new JButton("Enter");
        yes = new JButton("Yes");
        no = new JButton("No");
        delete = new JButton("Delete an item");
        mark = new JButton("Change the status of an item");
        dontDeleteOrMark = new JButton("Return to main menu");
        toDo = new JButton("To Do List");
        reminder = new JButton("Reminder List");
        //homework = new JButton("Homework List");
        back = new JButton("Return");
    }


    public void initializeGraphics() {
        setLayout(null);
        setMinimumSize(new Dimension(1200, 800));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        initializeComponents();
        layoutInitial();

        setVisible(true);
    }

    private void initializeComponents() {
        mainLabel.setText(pleaseSelect);
        setLabelBounds();
        setTextBoxBounds();
        setButtonBounds();
        buttonActions();
        addButtons();
    }

    private void setLabelBounds() {
        mainLabel.setBounds(30, 50, 300, 30);
        add(mainLabel);

        weatherLabel.setBounds(20, 720, 380, 30);
        add(weatherLabel);

        temperatureLabel.setBounds(20, 740, 380, 30);
        add(temperatureLabel);

        enterTitleLabel.setBounds(50, 360, 300, 20);
        add(enterTitleLabel);

        enterDescriptionLabel.setBounds(50, 410, 300, 20);
        add(enterDescriptionLabel);

        warningLabel.setBounds(30, 100, 400, 20);
        add(warningLabel);
        warningLabel.setVisible(false);

        setTodoAndReminderBounds();
    }

    private void setTodoAndReminderBounds() {
        toDoTitleLabel.setBounds(450, 70, 300, 20);
        add(toDoTitleLabel);
        toDoTitleLabel.setVisible(true);

        reminderTitleLabel.setBounds(820, 70, 300, 20);
        add(reminderTitleLabel);
        reminderTitleLabel.setVisible(true);

        nothingInToDoLabel.setBounds(450, 100, 300, 20);
        add(nothingInToDoLabel);
        nothingInToDoLabel.setVisible(false);

        nothingInReminderLabel.setBounds(820, 100, 300, 20);
        add(nothingInReminderLabel);
        nothingInReminderLabel.setVisible(false);
    }

    private void setTextBoxBounds() {
        titleTextBox.setBounds(50, 380, 300, 20);
        add(titleTextBox);
        attributeTextBox.setBounds(50, 430, 300, 20);
        add(attributeTextBox);
        indexTextBox.setBounds(50, 300, 300, 20);
        add(indexTextBox);
    }

    private void setButtonBounds() {
        addToDoButton.setBounds(20, 500, 150, 30);
        addReminderButton.setBounds(20, 550, 150, 30);
        //button3.setBounds(20, 550, 150, 30);
        editListsButton.setBounds(20, 600, 150, 30);
        weatherButton.setBounds(430, 730, 150, 30);
        exitButton.setBounds(1100, 730, 80, 30);
        enter.setBounds(270, 600, 80, 30);
        yes.setBounds(50, 600, 80, 30);
        no.setBounds(270, 600, 80, 30);
        delete.setBounds(150, 500, 200, 30);
        mark.setBounds(150, 550, 200, 30);
        dontDeleteOrMark.setBounds(150, 600, 200, 30);
        toDo.setBounds(20, 500, 150, 30);
        reminder.setBounds(230, 500, 150, 30);
        //homework.setBounds(20, 550, 150, 30);
        back.setBounds(230, 550, 150, 30);
    }

    private void addButtons() {
        add(addToDoButton);
        add(addReminderButton);
        //add(button3);
        add(editListsButton);
        add(weatherButton);
        add(exitButton);
        add(enter);
        add(yes);
        add(no);
        add(delete);
        add(mark);
        add(toDo);
        add(reminder);
        //add(homework);
        add(back);
    }

    private void buttonActions() {
        addToDoButton.setActionCommand("add to do");
        addReminderButton.setActionCommand("add reminder");
        //button3.setActionCommand("3");
        editListsButton.setActionCommand("edit lists");
        weatherButton.setActionCommand("weather");
        exitButton.setActionCommand("exit");
        enter.setActionCommand("enter");
        yes.setActionCommand("yes");
        no.setActionCommand("no");
        delete.setActionCommand("delete");
        mark.setActionCommand("mark");
        dontDeleteOrMark.setActionCommand("neither");
        toDo.setActionCommand("to do");
        reminder.setActionCommand("reminder");
        //homework.setActionCommand("homework");
        back.setActionCommand("return");
    }

    //REQUIRES: all the components already added to the frame
    //MODIFIES: this
    //EFFECTS: displays the initial lay out
    public void layoutInitial() {
        mainLabel.setText(pleaseSelect);
        only6OptionButtons();
        enterTitleLabel.setVisible(false);
        enterDescriptionLabel.setVisible(false);
        titleTextBox.setVisible(false);
        attributeTextBox.setVisible(false);
        indexTextBox.setVisible(false);
        warningLabel.setVisible(false);
        setVisible(true);
    }

    private void only6OptionButtons() {
        addToDoButton.setVisible(true);
        addReminderButton.setVisible(true);
        //button3.setVisible(true);
        editListsButton.setVisible(true);
        weatherButton.setVisible(true);
        exitButton.setVisible(true);
        enter.setVisible(false);
        yes.setVisible(false);
        no.setVisible(false);
        delete.setVisible(false);
        mark.setVisible(false);
        dontDeleteOrMark.setVisible(false);
        toDo.setVisible(false);
        reminder.setVisible(false);
        //homework.setVisible(false);
        back.setVisible(false);
    }

    //REQUIRES: all the components already added to the frame
    //MODIFIES: this
    //EFFECTS: displays the lay out for adding an item screen
    public void layoutForAddItem() {
        onlyEnterButton();
        enterTitleLabel.setVisible(true);
        enterDescriptionLabel.setVisible(true);
        add(titleTextBox);
        add(attributeTextBox);
        titleTextBox.setVisible(true);
        attributeTextBox.setVisible(true);
        setVisible(true);
    }

    private void onlyEnterButton() {
        addToDoButton.setVisible(false);
        addReminderButton.setVisible(false);
        //button3.setVisible(false);
        editListsButton.setVisible(false);
        weatherButton.setVisible(false);
        exitButton.setVisible(false);
        enter.setVisible(true);
        yes.setVisible(false);
        no.setVisible(false);
        toDo.setVisible(false);
        reminder.setVisible(false);
        //homework.setVisible(false);
    }

    //REQUIRES: all the components already added to the frame
    //MODIFIES: this
    //EFFECTS: displays the lay out for adding another item
    public void layoutForAddAnotherItem() {
        enter.setVisible(false);
        yes.setVisible(true);
        no.setVisible(true);
        enterTitleLabel.setVisible(false);
        enterDescriptionLabel.setVisible(false);
        titleTextBox.setVisible(false);
        attributeTextBox.setVisible(false);
        setVisible(true);
    }

    //REQUIRES: all the components already added to the frame
    //MODIFIES: this
    //EFFECTS: displays the lay out for item options
    public void layoutForItemOptions() {
        only3OptionButtons();

        enterTitleLabel.setVisible(false);
        enterDescriptionLabel.setVisible(false);
        add(dontDeleteOrMark);
        indexTextBox.setVisible(true);
        setVisible(true);
    }

    private void only3OptionButtons() {
        mainLabel.setText("Enter the item's index and choose an option");
        addToDoButton.setVisible(false);
        addReminderButton.setVisible(false);
        //button3.setVisible(false);
        editListsButton.setVisible(false);
        weatherButton.setVisible(false);
        exitButton.setVisible(false);
        yes.setVisible(false);
        no.setVisible(false);
        enter.setVisible(false);
        delete.setVisible(true);
        mark.setVisible(true);
        dontDeleteOrMark.setVisible(true);
        setVisible(true);
        toDo.setVisible(false);
        reminder.setVisible(false);
        //homework.setVisible(false);
    }

    //REQUIRES: all the components already added to the frame
    //MODIFIES: this
    //EFFECTS: displays the lay out for editing lists
    public void layoutForShowLists() {
        mainLabel.setText("Which list would you like to edit?");
        addToDoButton.setVisible(false);
        addReminderButton.setVisible(false);
        //button3.setVisible(false);
        editListsButton.setVisible(false);
        weatherButton.setVisible(false);
        exitButton.setVisible(false);
        yes.setVisible(false);
        no.setVisible(false);
        enter.setVisible(false);
        delete.setVisible(false);
        mark.setVisible(false);
        dontDeleteOrMark.setVisible(false);
        toDo.setVisible(true);
        reminder.setVisible(true);
        //homework.setVisible(true);
        setVisible(true);
    }

    //REQUIRES: all the components already added to the frame
    //MODIFIES: this
    //EFFECTS: determines what kind of listOfItems was passed in and displays them
    public void display(ListOfItems listOfItems) {
        if (listOfItems instanceof ListOfToDo) {
            displayToDo(listOfItems);
        }
        if (listOfItems instanceof ListOfReminder) {
            displayReminder(listOfItems);
        }
    }

    private void displayToDo(ListOfItems listOfItems) {
        if (listOfItems.getSize() == 0) {
            generateTodoLabels(listOfItems);
            displayNothingInList(nothingInToDoLabel);
        } else {
            nothingInToDoLabel.setVisible(false);
            generateTodoLabels(listOfItems);
        }
    }

    private void displayReminder(ListOfItems listOfItems) {
        if (listOfItems.getSize() == 0) {
            generateReminderLabels(listOfItems);
            displayNothingInList(nothingInReminderLabel);
        } else {
            nothingInReminderLabel.setVisible(false);
            generateReminderLabels(listOfItems);
        }
    }

    private void generateReminderLabels(ListOfItems listOfItems) {
        for (JLabel label : listOfReminderLabels) {
            label.setVisible(false);
            remove(label);
        }
        listOfReminderLabels.clear();
        int startY = 100;
        for (String entry : listOfItems.print()) {
            JLabel label = new JLabel(entry);
            add(label);
            label.setBounds(820, startY, 350, 20);
            startY += 25;
            label.setVisible(true);
            listOfReminderLabels.add(label);
        }
    }

    private void generateTodoLabels(ListOfItems listOfItems) {
        for (JLabel label : listOfToDoLabels) {
            label.setVisible(false);
            remove(label);
        }
        listOfToDoLabels.clear();
        int startY = 100;
        for (String entry : listOfItems.print()) {
            JLabel label = new JLabel(entry);
            add(label);
            label.setBounds(450, startY, 350, 20);
            startY += 25;
            label.setVisible(true);
            listOfToDoLabels.add(label);
        }
    }

    private void displayNothingInList(JLabel label) {
        label.setVisible(true);
    }


    public JLabel getMainLabel() {
        return mainLabel;
    }


    public JLabel getWeatherLabel() {
        return weatherLabel;
    }

    public JLabel getTemperatureLabel() {
        return temperatureLabel;
    }

    public JLabel getWarningLabel() {
        return warningLabel;
    }

    public JTextField getTitleTextBox() {
        return titleTextBox;
    }

    public JTextField getAttributeTextBox() {
        return attributeTextBox;
    }

    public JTextField getIndexTextBox() {
        return indexTextBox;
    }


    public JButton getAddToDoButton() {
        return addToDoButton;
    }

    public JButton getAddReminderButton() {
        return addReminderButton;
    }

//    public JButton getButton3() {
//        return button3;
//    }

    public JButton getEditListsButton() {
        return editListsButton;
    }

    public JButton getWeatherButton() {
        return weatherButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JButton getEnter() {
        return enter;
    }

    public JButton getYes() {
        return yes;
    }

    public JButton getNo() {
        return no;
    }

    public JButton getDelete() {
        return delete;
    }

    public JButton getMark() {
        return mark;
    }

    public JButton getDontDeleteOrMark() {
        return dontDeleteOrMark;
    }

    public JButton getToDo() {
        return toDo;
    }

    public JButton getReminder() {
        return reminder;
    }

//    public JButton getHomework() {
//        return homework;
//    }

    public JButton getBack() {
        return back;
    }
}
