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
    private JLabel toDoTitleLabel;
    private JLabel reminderTitleLabel;
    private JLabel nothingInToDoLabel;
    private JLabel nothingInReminderLabel;
    private JLabel tooManyItemsLabel;

    //TEXT BOXES
    private JTextField titleTextBox;
    private JTextField attributeTextBox;
    private JTextField indexTextBox;

    //BUTTONS
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton enter;
    private JButton yes;
    private JButton no;
    private JButton delete;
    private JButton mark;
    private JButton dontDeleteOrMark;
    private JButton toDo;
    private JButton reminder;
    private JButton homework;
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
        toDoTitleLabel = new JLabel("To Dos:");
        reminderTitleLabel = new JLabel("Reminders:");
        nothingInToDoLabel = new JLabel("You have no to dos");
        nothingInReminderLabel = new JLabel("You have no reminders");
        tooManyItemsLabel = new JLabel("You have too many items undone, delete or mark an item? ");

        titleTextBox = new JTextField();
        attributeTextBox = new JTextField();
        indexTextBox = new JTextField();

        setButtonNames();
    }

    private void setButtonNames() {
        button1 = new JButton("Add a to do");
        button2 = new JButton("Add a reminder");
        button3 = new JButton("New homework list");
        button4 = new JButton("Edit current lists");
        button5 = new JButton("Show me the weather");
        button6 = new JButton("Exit");
        enter = new JButton("Enter");
        yes = new JButton("Yes");
        no = new JButton("No");
        delete = new JButton("Delete an item");
        mark = new JButton("Change the status of an item");
        dontDeleteOrMark = new JButton("Return to main menu");
        toDo = new JButton("To Do List");
        reminder = new JButton("Reminder List");
        homework = new JButton("Homework List");
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

        tooManyItemsLabel.setBounds(30, 100, 400, 20);
        add(tooManyItemsLabel);
        tooManyItemsLabel.setVisible(false);

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
        titleTextBox.setBounds(50, 400, 300, 20);
        add(titleTextBox);
        attributeTextBox.setBounds(50, 430, 300, 20);
        add(attributeTextBox);
        indexTextBox.setBounds(50, 300, 300, 20);
        add(indexTextBox);
    }

    private void setButtonBounds() {
        button1.setBounds(20, 500, 150, 30);
        button2.setBounds(230, 500, 150, 30);
        button3.setBounds(20, 550, 150, 30);
        button4.setBounds(230, 550, 150, 30);
        button5.setBounds(20, 600, 150, 30);
        button6.setBounds(230, 600, 150, 30);
        enter.setBounds(270, 600, 80, 30);
        yes.setBounds(50, 600, 80, 30);
        no.setBounds(270, 600, 80, 30);
        delete.setBounds(150, 500, 200, 30);
        mark.setBounds(150, 550, 200, 30);
        dontDeleteOrMark.setBounds(150, 600, 200, 30);
        toDo.setBounds(20, 500, 150, 30);
        reminder.setBounds(230, 500, 150, 30);
        homework.setBounds(20, 550, 150, 30);
        back.setBounds(230, 550, 150, 30);
    }

    private void addButtons() {
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
        add(button6);
        add(enter);
        add(yes);
        add(no);
        add(delete);
        add(mark);
        add(toDo);
        add(reminder);
        add(homework);
        add(back);
    }

    private void buttonActions() {
        button1.setActionCommand("1");
        button2.setActionCommand("2");
        button3.setActionCommand("3");
        button4.setActionCommand("4");
        button5.setActionCommand("5");
        button6.setActionCommand("6");
        enter.setActionCommand("enter");
        yes.setActionCommand("yes");
        no.setActionCommand("no");
        delete.setActionCommand("delete");
        mark.setActionCommand("mark");
        dontDeleteOrMark.setActionCommand("neither");
        toDo.setActionCommand("to do");
        reminder.setActionCommand("reminder");
        homework.setActionCommand("homework");
        back.setActionCommand("return");
    }


    public void layoutInitial() {
        mainLabel.setText(pleaseSelect);
        only6OptionButtons();

        titleTextBox.setVisible(false);
        attributeTextBox.setVisible(false);
        indexTextBox.setVisible(false);
        tooManyItemsLabel.setVisible(false);
        setVisible(true);
    }

    private void only6OptionButtons() {
        button1.setVisible(true);
        button2.setVisible(true);
        button3.setVisible(true);
        button4.setVisible(true);
        button5.setVisible(true);
        button6.setVisible(true);
        enter.setVisible(false);
        yes.setVisible(false);
        no.setVisible(false);
        delete.setVisible(false);
        mark.setVisible(false);
        dontDeleteOrMark.setVisible(false);
        toDo.setVisible(false);
        reminder.setVisible(false);
        homework.setVisible(false);
        back.setVisible(false);
    }

    public void layoutForAddItem() {
        button1.setVisible(false);
        button2.setVisible(false);
        button3.setVisible(false);
        button4.setVisible(false);
        button5.setVisible(false);
        button6.setVisible(false);
        enter.setVisible(true);
        yes.setVisible(false);
        no.setVisible(false);
        toDo.setVisible(false);
        reminder.setVisible(false);
        homework.setVisible(false);
        add(titleTextBox);
        add(attributeTextBox);
        titleTextBox.setVisible(true);
        attributeTextBox.setVisible(true);
        setVisible(true);
    }

    public void layoutForAddAnotherItem() {
        enter.setVisible(false);
        yes.setVisible(true);
        no.setVisible(true);
        titleTextBox.setVisible(false);
        attributeTextBox.setVisible(false);
        setVisible(true);
    }

    public void layoutForItemOptions() {
        only3OptionButtons();

        add(dontDeleteOrMark);
        indexTextBox.setVisible(true);
        setVisible(true);
    }

    private void only3OptionButtons() {
        mainLabel.setText("Enter the item's index and choose an option");
        button1.setVisible(false);
        button2.setVisible(false);
        button3.setVisible(false);
        button4.setVisible(false);
        button5.setVisible(false);
        button6.setVisible(false);
        yes.setVisible(false);
        no.setVisible(false);
        enter.setVisible(false);
        delete.setVisible(true);
        mark.setVisible(true);
        dontDeleteOrMark.setVisible(true);
        setVisible(true);
        toDo.setVisible(false);
        reminder.setVisible(false);
        homework.setVisible(false);
    }

    public void layoutForShowLists() {
        mainLabel.setText("Which list would you like to edit?");
        button1.setVisible(false);
        button2.setVisible(false);
        button3.setVisible(false);
        button4.setVisible(false);
        button5.setVisible(false);
        button6.setVisible(false);
        yes.setVisible(false);
        no.setVisible(false);
        enter.setVisible(false);
        delete.setVisible(false);
        mark.setVisible(false);
        dontDeleteOrMark.setVisible(false);
        toDo.setVisible(true);
        reminder.setVisible(true);
        homework.setVisible(true);
        setVisible(true);
    }


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

    public JLabel getTooManyItemsLabel() {
        return tooManyItemsLabel;
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


    public JButton getButton1() {
        return button1;
    }

    public JButton getButton2() {
        return button2;
    }

    public JButton getButton3() {
        return button3;
    }

    public JButton getButton4() {
        return button4;
    }

    public JButton getButton5() {
        return button5;
    }

    public JButton getButton6() {
        return button6;
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

    public JButton getHomework() {
        return homework;
    }

    public JButton getBack() {
        return back;
    }
}
