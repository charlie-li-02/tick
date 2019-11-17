package ui;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private JLabel mainLabel;
    private JTextArea displayLabel;
    private JLabel weatherLabel;
    private JLabel temperatureLabel;
    private JTextField titleTextBox;
    private JTextField attributeTextBox;
    private JTextField indexTextBox;
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

    private static final String pleaseSelect = "Please select one of:";
    private static final String addToDo = "Add a to do";
    private static final String addReminder = "Add a reminder";
    private static final String addHW = "New homework list";
    private static final String viewLists = "View current lists";
    private static final String showWeather = "Show me the weather";
    private static final String exit = "Exit";
    private static final String deleteOrMark = "Enter the item's index and choose an option";
    private static final String deleteString = "Delete an item";
    private static final String markString = "Change the status of an item";
    private static final String noString = "Return to main menu";
    private static final String whichList = "Which list would you like to open?";
    private static final String toDoButtonString = "To Do List";
    private static final String reminderButtonString = "Reminder List";
    private static final String homeworkButtonString = "Homework List";
    private static final String returnButtonString = "Return";


    public Window() {
        super("To Do List");

        mainLabel = new JLabel("");
        displayLabel = new JTextArea("");
        weatherLabel = new JLabel("");
        temperatureLabel = new JLabel("");
        titleTextBox = new JTextField();
        attributeTextBox = new JTextField();
        indexTextBox = new JTextField();

        button1 = new JButton(addToDo);
        button2 = new JButton(addReminder);
        button3 = new JButton(addHW);
        button4 = new JButton(viewLists);
        button5 = new JButton(showWeather);
        button6 = new JButton(exit);
        enter = new JButton("Enter");
        yes = new JButton("Yes");
        no = new JButton("No");
        delete = new JButton(deleteString);
        mark = new JButton(markString);
        dontDeleteOrMark = new JButton(noString);
        toDo = new JButton(toDoButtonString);
        reminder = new JButton(reminderButtonString);
        homework = new JButton(homeworkButtonString);
        back = new JButton(returnButtonString);
    }


    public void initializeGraphics() {
        setLayout(null);
        setMinimumSize(new Dimension(400, 800));
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

        displayLabel.setEditable(false);
        displayLabel.setCursor(null);
        displayLabel.setOpaque(false);
        displayLabel.setFocusable(false);
        displayLabel.setLineWrap(true);
        displayLabel.setWrapStyleWord(true);
        displayLabel.setBounds(50, 100,300, 200);
        add(displayLabel);

        weatherLabel.setBounds(20, 720, 380, 30);
        add(weatherLabel);

        temperatureLabel.setBounds(20, 740, 380, 30);
        add(temperatureLabel);
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
        displayLabel.setVisible(false);
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
    }

    public void layoutForAddAnotherItem() {
        enter.setVisible(false);
        yes.setVisible(true);
        no.setVisible(true);
        titleTextBox.setVisible(false);
        attributeTextBox.setVisible(false);
        displayLabel.setVisible(true);
    }

    public void layoutForItemOptions() {
        only3OptionButtons();

        add(dontDeleteOrMark);
        indexTextBox.setVisible(true);
        displayLabel.setVisible(true);
    }

    private void only3OptionButtons() {
        mainLabel.setText(deleteOrMark);
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
        toDo.setVisible(false);
        reminder.setVisible(false);
        homework.setVisible(false);
    }

    public void layoutForShowLists() {
        mainLabel.setText(whichList);
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
    }


    public JLabel getMainLabel() {
        return mainLabel;
    }

    public JTextArea getDisplayLabel() {
        return displayLabel;
    }

    public JLabel getWeatherLabel() {
        return weatherLabel;
    }

    public JLabel getTemperatureLabel() {
        return temperatureLabel;
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
