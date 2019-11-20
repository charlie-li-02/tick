package ui;

import model.Item;
import model.ListOfItems;
import model.ListOfReminder;
import model.ListOfToDo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Window extends JFrame {

    //LABELS
    private JTextArea mainLabel = new JTextArea("");
    private JLabel vancouver = new JLabel("Vancouver");
    private JLabel weatherLabel = new JLabel("");
    private JLabel temperatureLabel = new JLabel("");
    private JLabel maxTempLabel = new JLabel("");
    private JLabel minTempLabel = new JLabel("");
    private JLabel enterTitleLabel = new JLabel("Title:");
    private JLabel enterDescriptionLabel = new JLabel("Description/Time:");
    private JLabel toDoTitleLabel = new JLabel("To Dos:");
    private JLabel reminderTitleLabel = new JLabel("Reminders:");
    private JLabel nothingInToDoLabel = new JLabel("You have no to dos");
    private JLabel nothingInReminderLabel = new JLabel("You have no reminders");
    private JLabel warningLabel = new JLabel("");


    //TEXT BOXES
    private JTextField titleTextBox;
    private JTextField attributeTextBox;
    private JTextField indexTextBox;

    //BUTTONS
    private JButton addToDoButton = new JButton("Add a to do");
    private JButton addReminderButton = new JButton("Add a reminder");
    //private JButton button3 = new JButton("New homework list");
    private JButton editListsButton = new JButton("Edit current lists");
    private JButton weatherButton = new JButton("Refresh");
    private JButton exitButton = new JButton("Exit");
    private JButton enter = new JButton("Enter");
    private JButton delete = new JButton("Delete an item");
    private JButton mark = new JButton("Mark done/undone");
    private JButton toDo = new JButton("To Do List");
    private JButton reminder = new JButton("Reminder List");
    private JButton back = new JButton("Return");


    //TEXT FOR BUTTONS
    private static final String pleaseSelect = "Please select one of:";

    //IMAGES
    private BufferedImage backgroundImage = ImageIO.read(new File("background.jpg"));
    private JLabel background = new JLabel(new ImageIcon(backgroundImage));
    private BufferedImage done = ImageIO.read((new File("done.jpg")));
    private BufferedImage undone = ImageIO.read(new File("undone.jpg"));
    private JLabel weatherIcon = new JLabel(new ImageIcon());

    private ArrayList<JLabel> listOfToDoLabels = new ArrayList<>();
    private ArrayList<JLabel> listOfReminderLabels = new ArrayList<>();
    private ArrayList<JLabel> listOfToDoChecks = new ArrayList<>();
    private ArrayList<JLabel> listOfReminderChecks = new ArrayList<>();
    private ArrayList<JButton> listOfButtons = new ArrayList<>();


    public Window() throws IOException {
        super("Tick");

        titleTextBox = new JTextField();
        attributeTextBox = new JTextField();
        indexTextBox = new JTextField();
    }



    public void initializeGraphics() {
        setLayout(null);
        setMinimumSize(new Dimension(1200, 829));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        add(background);
        background.setBounds(0,0, 1200, 800);

        initializeComponents();
        layoutInitial();

        setVisible(true);
    }

    private void initializeComponents() {
        mainLabel.setText(pleaseSelect);
        mainLabel.setEditable(false);
        mainLabel.setLineWrap(true);
        mainLabel.setWrapStyleWord(true);
        mainLabel.setBackground(Color.white);
        mainLabel.setFont(new Font(null, Font.PLAIN, 22));
        setLabelBounds();
        setWeatherLabelsBounds();
        setTextBoxBounds();
        setButtonBounds();
        buttonActions();
        addButtons();
    }

    private void setLabelBounds() {
        mainLabel.setBounds(30, 70, 380, 200);
        background.add(mainLabel);


        enterTitleLabel.setBounds(50, 360, 300, 20);
        enterTitleLabel.setFont(new Font(null, Font.PLAIN, 12));
        background.add(enterTitleLabel);

        enterDescriptionLabel.setBounds(50, 410, 300, 20);
        enterDescriptionLabel.setFont(new Font(null, Font.PLAIN, 12));
        background.add(enterDescriptionLabel);

        warningLabel.setBounds(50, 280, 400, 20);
        warningLabel.setFont(new Font(null, Font.PLAIN, 15));
        background.add(warningLabel);
        warningLabel.setVisible(false);

        setTodoAndReminderBounds();
    }

    private void setTodoAndReminderBounds() {
        toDoTitleLabel.setBounds(430, 70, 300, 20);
        toDoTitleLabel.setFont(new Font(null, Font.PLAIN, 15));
        background.add(toDoTitleLabel);
        toDoTitleLabel.setVisible(true);

        reminderTitleLabel.setBounds(810, 70, 300, 20);
        reminderTitleLabel.setFont(new Font(null, Font.PLAIN, 15));
        background.add(reminderTitleLabel);
        reminderTitleLabel.setVisible(true);

        nothingInToDoLabel.setBounds(450, 100, 300, 20);
        nothingInToDoLabel.setFont(new Font(null, Font.PLAIN, 12));
        background.add(nothingInToDoLabel);
        nothingInToDoLabel.setVisible(false);

        nothingInReminderLabel.setBounds(830, 100, 300, 20);
        nothingInReminderLabel.setFont(new Font(null, Font.PLAIN, 12));
        background.add(nothingInReminderLabel);
        nothingInReminderLabel.setVisible(false);
    }

    private void setWeatherLabelsBounds() {
        vancouver.setBounds(90, 725, 100, 20);
        background.add(vancouver);

        weatherLabel.setBounds(90, 740, 380, 40);
        weatherLabel.setFont(new Font(null, Font.PLAIN, 30));
        background.add(weatherLabel);

        temperatureLabel.setBounds(250, 730, 100, 40);
        temperatureLabel.setFont(new Font(null, Font.PLAIN, 40));
        background.add(temperatureLabel);

        maxTempLabel.setBounds(330, 730, 100, 20);
        maxTempLabel.setFont(new Font(null, Font.PLAIN, 15));
        background.add(maxTempLabel);

        minTempLabel.setBounds(330, 750, 100, 20);
        minTempLabel.setFont(new Font(null, Font.PLAIN, 15));
        background.add(minTempLabel);

        weatherIcon.setBounds(0, 703, 100,100);
        background.add(weatherIcon);
    }

    private void setTextBoxBounds() {
        titleTextBox.setBounds(50, 380, 300, 20);
        background.add(titleTextBox);
        attributeTextBox.setBounds(50, 430, 300, 20);
        background.add(attributeTextBox);
        indexTextBox.setBounds(50, 300, 300, 20);
        background.add(indexTextBox);
    }

    private void setButtonBounds() {
        addToDoButton.setBounds(20, 500, 150, 30);
        addReminderButton.setBounds(20, 550, 150, 30);
        //button3.setBounds(20, 550, 150, 30);
        editListsButton.setBounds(20, 600, 150, 30);
        weatherButton.setBounds(425, 740, 80, 30);
        exitButton.setBounds(1085, 740, 80, 30);
        enter.setBounds(270, 600, 150, 30);
        delete.setBounds(20, 500, 150, 30);
        mark.setBounds(20, 550, 150, 30);
        toDo.setBounds(20, 500, 150, 30);
        reminder.setBounds(20, 550, 150, 30);
        back.setBounds(20, 600, 150, 30);
    }

    private void addButtons() {
        listOfButtons.add(addToDoButton);
        listOfButtons.add(addReminderButton);
        listOfButtons.add(editListsButton);
        listOfButtons.add(weatherButton);
        listOfButtons.add(exitButton);
        listOfButtons.add(enter);
        listOfButtons.add(delete);
        listOfButtons.add(mark);
        listOfButtons.add(toDo);
        listOfButtons.add(reminder);
        listOfButtons.add(back);

        formatButtons();
    }

    private void formatButtons() {
        for (JButton button: listOfButtons) {
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            background.add(button);
        }
    }

    private void buttonActions() {
        addToDoButton.setActionCommand("add to do");
        addReminderButton.setActionCommand("add reminder");
        editListsButton.setActionCommand("edit lists");
        weatherButton.setActionCommand("weather");
        exitButton.setActionCommand("exit");
        enter.setActionCommand("enter");
        delete.setActionCommand("delete");
        mark.setActionCommand("mark");
        toDo.setActionCommand("to do");
        reminder.setActionCommand("reminder");
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
        setVisible(true);
    }

    private void only6OptionButtons() {
        addToDoButton.setVisible(true);
        addReminderButton.setVisible(true);
        editListsButton.setVisible(true);
        weatherButton.setVisible(true);
        exitButton.setVisible(true);
        enter.setVisible(false);
        delete.setVisible(false);
        mark.setVisible(false);
        toDo.setVisible(false);
        reminder.setVisible(false);
        back.setVisible(false);

        setVisible(true);
    }

    //REQUIRES: all the components already added to the frame
    //MODIFIES: this
    //EFFECTS: displays the lay out for adding an item screen
    public void layoutForAddItem() {
        onlyEnterButton();
        enterTitleLabel.setVisible(true);
        enterDescriptionLabel.setVisible(true);
        background.add(titleTextBox);
        background.add(attributeTextBox);
        titleTextBox.setVisible(true);
        attributeTextBox.setVisible(true);
        setVisible(true);
    }

    private void onlyEnterButton() {
        addToDoButton.setVisible(false);
        addReminderButton.setVisible(false);
        editListsButton.setVisible(false);
        enter.setVisible(true);
        toDo.setVisible(false);
        reminder.setVisible(false);
        back.setVisible(true);
        setVisible(true);
    }

    //REQUIRES: all the components already added to the frame
    //MODIFIES: this
    //EFFECTS: displays the lay out for item options
    public void layoutForItemOptions() {
        only3OptionButtons();

        enterTitleLabel.setVisible(false);
        enterDescriptionLabel.setVisible(false);
        indexTextBox.setVisible(true);
        setVisible(true);
    }

    private void only3OptionButtons() {
        mainLabel.setText("Enter the item's index and choose an option");
        addToDoButton.setVisible(false);
        addReminderButton.setVisible(false);
        editListsButton.setVisible(false);
        enter.setVisible(false);
        delete.setVisible(true);
        mark.setVisible(true);
        setVisible(true);
        toDo.setVisible(false);
        reminder.setVisible(false);
        back.setVisible(true);
        setVisible(true);
    }

    //REQUIRES: all the components already added to the frame
    //MODIFIES: this
    //EFFECTS: displays the lay out for editing lists
    public void layoutForEditLists() {
        mainLabel.setText("Which list would you like to edit?");
        addToDoButton.setVisible(false);
        addReminderButton.setVisible(false);
        editListsButton.setVisible(false);
        enter.setVisible(false);
        delete.setVisible(false);
        mark.setVisible(false);
        toDo.setVisible(true);
        reminder.setVisible(true);
        back.setVisible(true);
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
            generateToDoChecks(listOfItems);
            generateTodoLabels(listOfItems);
            displayNothingInList(nothingInToDoLabel);
        } else {
            nothingInToDoLabel.setVisible(false);
            generateToDoChecks(listOfItems);
            generateTodoLabels(listOfItems);
        }
    }

    private void displayReminder(ListOfItems listOfItems) {
        if (listOfItems.getSize() == 0) {
            generateReminderChecks(listOfItems);
            generateReminderLabels(listOfItems);
            displayNothingInList(nothingInReminderLabel);
        } else {
            nothingInReminderLabel.setVisible(false);
            generateReminderChecks(listOfItems);
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
            background.add(label);
            label.setBounds(830, startY, 350, 20);
            label.setFont(new Font(null, Font.PLAIN, 12));
            startY += 25;
            label.setVisible(true);
            listOfReminderLabels.add(label);
        }
    }

    private void generateReminderChecks(ListOfItems listOfItems) {
        for (JLabel check: listOfReminderChecks) {
            check.setVisible(false);
            remove(check);
        }
        listOfReminderChecks.clear();
        int startY = 100;
        for (Item item: listOfItems.listOfItems) {
            JLabel check = new JLabel();
            getCheck(item, check);
            background.add(check);
            check.setBounds(810, startY, 20, 20);
            check.setVisible(true);
            startY += 25;
            listOfReminderChecks.add(check);
        }
    }

    private void generateToDoChecks(ListOfItems listOfItems) {
        for (JLabel check: listOfToDoChecks) {
            check.setVisible(false);
            remove(check);
        }
        listOfToDoChecks.clear();
        int startY = 100;
        for (Item item: listOfItems.listOfItems) {
            JLabel check = new JLabel();
            getCheck(item, check);
            check.setBounds(430, startY, 20, 20);
            background.add(check);
            check.setVisible(true);
            startY += 25;
            listOfToDoChecks.add(check);
        }
    }

    private void getCheck(Item item, JLabel check) {
        if (item.getIsDone()) {
            check.setIcon(new ImageIcon(done));
        } else if (!item.getIsDone()) {
            check.setIcon(new ImageIcon(undone));
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
            background.add(label);
            label.setBounds(450, startY, 350, 20);
            label.setFont(new Font(null, Font.PLAIN, 12));
            startY += 25;
            label.setVisible(true);
            listOfToDoLabels.add(label);
        }
    }

    private void displayNothingInList(JLabel label) {
        label.setVisible(true);
    }


    public JTextArea getMainLabel() {
        return mainLabel;
    }


    public JLabel getWeatherLabel() {
        return weatherLabel;
    }

    public JLabel getTemperatureLabel() {
        return temperatureLabel;
    }

    public JLabel getMaxTempLabel() {
        return maxTempLabel;
    }

    public JLabel getMinTempLabel() {
        return minTempLabel;
    }

    public JLabel getWeatherIcon() {
        return weatherIcon;
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

    public JButton getDelete() {
        return delete;
    }

    public JButton getMark() {
        return mark;
    }

    public JButton getToDo() {
        return toDo;
    }

    public JButton getReminder() {
        return reminder;
    }

    public JButton getBack() {
        return back;
    }
}
