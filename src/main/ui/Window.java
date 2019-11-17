package ui;

import model.Item;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private JLabel mainLabel;
    private JTextField titleTextBox;
    private JTextField attributeTextBox;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton enter;
    private JButton yes;
    private JButton no;

    private AppRunner appRunner;

    private static final String pleaseSelect = "Please select one of:\n";
    private static final String addToDo = "1 - add a to do\n";
    private static final String addReminder = "2 - add a reminder\n";
    private static final String addHW = "3 - new homework list\n";
    private static final String viewLists = "4 - view current lists\n";
    private static final String showWeather = "5 - show me the weather\n";
    private static final String exit = "6 - exit";
    private ItemOptions itemOptions;
    private ItemHandler itemHandler;

    public Window(AppRunner appRunner) {
        super("To Do List");

        this.appRunner = appRunner;
        mainLabel = new JLabel("");
        titleTextBox = new JTextField();
        attributeTextBox = new JTextField();

        button1 = new JButton("");
        button2 = new JButton("");
        button3 = new JButton("");
        button4 = new JButton("");
        button5 = new JButton("");
        button6 = new JButton("");
        enter = new JButton("Enter");
        yes = new JButton("Yes");
        no = new JButton("No");
    }


    public void initializeGraphics() {
        setLayout(null);
        setMinimumSize(new Dimension(400, 800));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        initializeComponents();
        drawOptions();

        setVisible(true);
    }

    private void initializeComponents() {
        mainLabel.setText(pleaseSelect);
        mainLabel.setBounds(30, 50, 300, 30);
        add(mainLabel);

        titleTextBox.setBounds(50, 400, 300, 20);
        attributeTextBox.setBounds(50, 430, 300, 20);

        button1.setBounds(30, 90, 200, 30);
        button2.setBounds(30, 130, 200, 30);
        button3.setBounds(30, 170, 200, 30);
        button4.setBounds(30, 210, 200, 30);
        button5.setBounds(30, 250, 200, 30);
        button6.setBounds(30, 290, 200, 30);
        enter.setBounds(270, 600, 80, 30);
    }

    private void drawOptions() {
        button1.setText(this.addToDo);
        button2.setText(this.addReminder);
        button3.setText(this.addHW);
        button4.setText(this.viewLists);
        button5.setText(this.showWeather);
        button6.setText(this.exit);

        buttonActions();

        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
        add(button6);
        add(enter);
        enter.setVisible(false);
    }

    private void buttonActions() {
        button1.setActionCommand("1");
        button2.setActionCommand("2");
        button3.setActionCommand("3");
        button4.setActionCommand("4");
        button5.setActionCommand("5");
        button6.setActionCommand("6");
        enter.setActionCommand("enter");

        button1.addActionListener(appRunner);
        button2.addActionListener(appRunner);
        button3.addActionListener(appRunner);
        button4.addActionListener(appRunner);
        button5.addActionListener(appRunner);
        button6.addActionListener(appRunner);
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

        yes.setBounds(50, 600, 80, 30);
        no.setBounds(270, 600, 80, 30);

        add(yes);
        add(no);

        yes.setActionCommand("yes");
        no.setActionCommand("no");
    }

    public JLabel getMainLabel() {
        return mainLabel;
    }

    public JTextField getTitleTextBox() {
        return titleTextBox;
    }

    public JTextField getAttributeTextBox() {
        return attributeTextBox;
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
}
