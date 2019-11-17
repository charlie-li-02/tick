package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame {

//    private JLabel label;
//    private JTextField field;
//
//    public UI() {
//        super("To Do List");
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setPreferredSize(new Dimension(400, 800));
//        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));
//        setLayout(null);
//        JButton btn = new JButton("Enter");
//        btn.setActionCommand("entered");
//        btn.addActionListener(this); //sets "this" class as an action listener for btn.
//        //that means that when the btn is clicked,
//        //this.actionPerformed(ActionEvent e) will be called.
//        //You could also set a different class, if you wanted
//        //to capture the response behaviour elsewhere
//        label = new JLabel("text");
//        field = new JTextField(10);
//        btn.setBounds(160,400, 80, 30);
//        field.setBounds(120, 300, 160, 20);
//        label.setBounds(20,30,400,20);
//        add(field);
//        add(label);
//        add(btn);
//        pack();
//        setLocationRelativeTo(null);
//        setVisible(true);
//        setResizable(false);
//    }
//
//    //this is the method that runs when Swing registers an action on an element
//    //for which this class is an ActionListener
//    public void actionPerformed(ActionEvent e) {
//        if (e.getActionCommand().equals("entered")) {
//            label.setText(field.getText());
//        }
//    }
    public static void main(String[] args) {

        final JFrame frame = new JFrame("Test");
        frame.setLayout(new GridLayout(0, 1));

        frame.add(new JButton(new AbstractAction("Click to add") {
            @Override
            public void actionPerformed(ActionEvent e) {

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        frame.add(new JLabel("Bla"));
                        frame.validate();
                        frame.repaint();
                    }
                });
            }
        }));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        SwingUtilities.invokeLater(new Runnable() {
            @Override public void run() {
                frame.setVisible(true);
            }
        });
    }
}