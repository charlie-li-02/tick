package ui;

import model.Course;
import model.HomeworkItem;
import model.HomeworkList;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Scanner;

public class HomeworkHandler {

    private HomeworkOptions homeworkOptions;
    private Scanner takeInput;

    public HomeworkHandler() {
        homeworkOptions = new HomeworkOptions();
        takeInput = new Scanner(System.in);
    }

    //REQUIRES: nothing
    //MODIFIES: HomeworkList
    //EFFECTS: starts off the processing of adding a homework
    public void makeNewHomework(HomeworkList homeworkList) throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("enter a course name");
        Course course = new Course(takeInput.nextLine());
        HashSet<HomeworkItem> setOfHW = new HashSet<>();
        while (true) {
            System.out.println("enter the assignment name");
            String assignment = takeInput.nextLine();
            System.out.println("enter the due date (ex. sep 1st )");
            String dueBy = takeInput.nextLine();
            HomeworkItem homework = new HomeworkItem(course, assignment, dueBy, false);
            setOfHW.add(homework);
            course.addHomework(homework);
            System.out.println("do you want to add another assignment due? (y|n)");
            if (takeInput.nextLine().equals("n")) {
                break;
            }
        }
        homeworkList.addHomeworkList(course, setOfHW);
        processHomeworkList(homeworkList);
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: prompts the user choices
    private void processHomeworkList(HomeworkList hwl) throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("do you want to add assignments for another course? (y|n)");
        String choice = takeInput.nextLine();
        if (choice.equals("y")) {
            makeNewHomework(hwl);
        } else if (choice.equals("n")) {
            homeworkOptions.printHomeworkList(hwl);
        }
        hwl.save();
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: lets the user pick which homework list they want to modify
    public void pickHomeworkList(HomeworkList homeworkList) throws FileNotFoundException, UnsupportedEncodingException {
        while (homeworkList.homeWorkList.keySet().size() > 0) {
            System.out.println("do you wish to edit any homework lists? (y|n)");
            String choice = takeInput.nextLine();
            if (choice.equals("n")) {
                break;
            } else {
                System.out.println("enter a course name to edit its assignments");
                try {
                    getHomeworkList(homeworkList);
                } catch (NullPointerException e) {
                    System.out.println("No list matching that course name, try again");
                }
            }
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: gets the right homework list according to the course inputted
    private void getHomeworkList(HomeworkList homeworkList) throws FileNotFoundException, UnsupportedEncodingException {
        Course course = new Course(takeInput.nextLine());
        HashSet<HomeworkItem> homework = course.getSetOfHomework(homeworkList.homeWorkList);
        if (homework.isEmpty()) {
            System.out.println("there is no assignments for that course");
        } else {
            homeworkOptions.homeworkListOptions(course, homework, homeworkList);
        }
        homeworkList.save();
    }
}
