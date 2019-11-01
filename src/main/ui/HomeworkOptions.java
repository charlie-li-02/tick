package ui;

import model.Course;
import model.HomeworkItem;
import model.HomeworkList;

import java.util.HashSet;
import java.util.Scanner;

public class HomeworkOptions {

    private Scanner takeInput;

    //bunch of strings for homework options because too long for check style
    private static final String hwOptionP1 = "Would you like to delete or change an assignment's status?\n";
    private static final String hwOptionP2 = "1 - delete an assignment\n";
    private static final String hwOptionP3 = "2 - change the status of an assignment\n";
    private static final String hwOptionP4 = "3 - no";

    public HomeworkOptions() {
        takeInput = new Scanner(System.in);
    }

    //REQUIRES: nothing
    //MODIFIES: homework
    //EFFECTS: gives the user the choice to delete or change the status of a homework item
    public void homeworkListOptions(Course course, HashSet<HomeworkItem> homework, HomeworkList homeworkList) {
        while (homework.size() > 0) {
            System.out.println(hwOptionP1 + hwOptionP2 + hwOptionP3 + hwOptionP4);
            String choice = takeInput.nextLine();
            if (choice.equals("1")) {
                deleteHomework(course, homework, homeworkList);
            } else if (choice.equals("2")) {
                markHomework(homework, homeworkList);
            } else if (choice.equals("3")) {
                break;
            } else {
                System.out.println("Try again");
            }
        }
    }

    //REQUIRES: nothing
    //MODIFIES: homework, Homework
    //EFFECTS: deletes the item that matches the user input
    private void deleteHomework(Course course, HashSet<HomeworkItem> homework, HomeworkList homeworkList) {
        String message = "enter the name of the assignment you want to delete";
        String assignment = getAssignment(homework, message);
        System.out.println(homeworkList.delete(course, assignment, homework));
        printHomeworkList(homeworkList);

    }

    //REQUIRES: nothing
    //MODIFIES: homework, Homework
    //EFFECTS: changes the status of the homework that matches the user input
    private void markHomework(HashSet<HomeworkItem> homework, HomeworkList homeworkList) {
        String message = "enter the name of the assignment you want to change the status of";
        String assignment = getAssignment(homework, message);
        System.out.println(homeworkList.changeStatus(assignment, homework));
        printHomeworkList(homeworkList);
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the name of the assignment the user wants to delete
    private String getAssignment(HashSet<HomeworkItem> homework, String message) {
        System.out.println(homework.toString());
        System.out.println(message);
        return takeInput.nextLine();
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: prints out the homework list
    public void printHomeworkList(HomeworkList homeworkList) {
        if (homeworkList.homeWorkList.keySet().size() == 0) {
            System.out.println("You don't have anything in that list!");
        } else {
            for (Course c : homeworkList.homeWorkList.keySet()) {
                String course = c.toString();
                String hwList = c.getSetOfHomework(homeworkList.homeWorkList).toString();
                System.out.println(course + " " + hwList);
            }
        }
    }
}
