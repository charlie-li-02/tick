package ui;

import exceptions.ItemDoesNotExistException;
import exceptions.TooManyItemsUndoneException;
import model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Scanner;

public class AppRunner {

    private ListOfItems listOfToDo;
    private ListOfItems listOfReminder;
    private HomeworkList homeworkList;
    private Scanner takeInput;

    // bunch of strings for input options because too long for check style
    private static final String optionP1 = "Please enter one of:\n";
    private static final String optionP2 = "1 - add a to do\n";
    private static final String optionP3 = "2 - add a reminder\n";
    private static final String optionP4 = "3 - new homework list\n";
    private static final String optionP5 = "4 - view current lists\n";
    private static final String optionP6 = "5 - exit";

    //bunch of strings for deleting or marking items because too long for check style
    private static final String outOP1 = "Would you like to delete an item or change an item's status?\n";
    private static final String outOP2 = "1 - delete an item\n";
    private static final String outOP3 = "2 - flip the status of an item\n";
    private static final String outOP4 = "3 - no";

    //bunch of strings for homework options because too long for check style
    private static final String hwOptionP1 = "Would you like to delete or change an assignment's status?\n";
    private static final String hwOptionP2 = "1 - delete an assignment\n";
    private static final String hwOptionP3 = "2 - change the status of an assignment\n";
    private static final String hwOptionP4 = "3 - no";

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: constructor for ToDoList
    private AppRunner() throws IOException {
        takeInput = new Scanner(System.in);
        listOfToDo = new ListOfToDo();
        listOfReminder = new ListOfReminder();
        homeworkList = new HomeworkList();

        listOfToDo.load();
        listOfReminder.load();
        homeworkList.load();
        processInput();

    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: gives the user the choice of adding a to do, reminder, show current lists, or close the program
    private void processInput() throws IOException {
        String type;
        while (true) {
            System.out.println(optionP1 + optionP2 + optionP3 + optionP4 + optionP5 + optionP6);
            type = takeInput.nextLine();

            if (type.equals("5")) {
                break;

            } else if (type.equals("1")) {
                handleToDo(listOfToDo);

            } else if (type.equals("2")) {
                handleReminder(listOfReminder);

            } else if (type.equals("3")) {
                handleHomeworkList();

            } else if (type.equals("4")) {
                chooseList();

            } else {
                System.out.println("Your entrance did not match any options, please try again. \n");
            }
        }
    }

    //REQUIRES: nothing
    //MODIFIES: ListOfToDo, todos.txt
    //EFFECTS: starts off the processing of adding a to do item
    private void handleToDo(ListOfItems ltd) throws IOException {
        processItem(ltd);
        ltd.save();
    }

    //REQUIRES: nothing
    //MODIFIES: ListOfReminder, reminders.txt
    //EFFECTS: starts off the processing of adding a reminder item
    private void handleReminder(ListOfItems lr) throws IOException {
        processItem(lr);
        lr.save();
    }

    //REQUIRES: nothing
    //MODIFIES: HomeworkList
    //EFFECTS: starts off the processing of adding a homework
    private void handleHomeworkList() throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("enter a course name");
        Course course = new Course(takeInput.nextLine());
        HashSet<Homework> setOfHW = new HashSet<>();
        while (true) {
            System.out.println("enter the assignment name");
            String assignment = takeInput.nextLine();
            System.out.println("enter the due date (ex. sep 1st )");
            String dueBy = takeInput.nextLine();
            Homework homework = new Homework(course, assignment, dueBy, false);
            setOfHW.add(homework);
            course.addHomework(homework);
            System.out.println("do you want to add another assignment due? (y|n)");
            if (takeInput.nextLine().equals("n")) {
                break;
            }
        }
        homeworkList.addHomeworkList(course, setOfHW);
        processHomeworkList();
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: prompts the user choices
    private void processHomeworkList() throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("do you want to add assignments for another course? (y|n)");
        String choice = takeInput.nextLine();
        if (choice.equals("y")) {
            handleHomeworkList();
        } else if (choice.equals("n")) {
            printHomeworkList();
        }
        homeworkList.save();
    }

    //REQUIRES: nothing
    //MODIFIES: RemainderItem
    //EFFECTS: creates a new ReminderItem based on the user's input
    private void processItem(ListOfItems li) throws FileNotFoundException, UnsupportedEncodingException {
        while (true) {
            System.out.println(li.getPromptTitle());
            String reminder = takeInput.nextLine();
            System.out.println(li.getPromptAttribute());
            String time = takeInput.nextLine();
            try {
                li.addNewItem(li.itemMaker(reminder, time));
                System.out.println(li.getPromptAnother());
                String choice = takeInput.nextLine();
                if (choice.equals("n")) {
                    processOptions(li);
                    break;
                }
            } catch (TooManyItemsUndoneException e) {
                handleTooManyItems(li);
                break;
            }
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: gives the user the choice to delete or mark an item as done after being prompted that they have
    //         too many items that aren't done
    private void handleTooManyItems(ListOfItems li) throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("You have too many items undone, delete or mark an item? (y|n)");
        String choice = takeInput.nextLine();
        if (choice.equals("y")) {
            processOptions(li);
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: lets the user choose which list to print out
    private void chooseList() throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("Which list would you like to open?\n" + "1 - to do list\n"
                + "2 - reminders list\n" + "3 - homework list\n");
        String choice = takeInput.nextLine();
        if (choice.equals("1")) {
            handlePrintList(listOfToDo);
        } else if (choice.equals("2")) {
            handlePrintList(listOfReminder);
        } else if (choice.equals("3")) {
            printHomeworkList();
            pickHomeworkList();
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: prints out the list of items
    private void handlePrintList(ListOfItems li) throws FileNotFoundException, UnsupportedEncodingException {
        if (li.getSize() == 0) {
            System.out.println("You don't have anything in that list!");
        } else {
            System.out.println(li.print());
            processOptions(li);
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: prints out the homework list
    private void printHomeworkList() {
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

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: prompts the user the option to delete or change the status of an item
    private void processOptions(ListOfItems li) throws FileNotFoundException, UnsupportedEncodingException {
        while (li.getSize() > 0) {
            System.out.println(outOP1 + outOP2 + outOP3 + outOP4);
            String choice = takeInput.nextLine();
            if (choice.equals("1")) {
                delete(li);
            } else if (choice.equals("2")) {
                mark(li);
            } else if (choice.equals("3")) {
                System.out.println(li.print());
                break;
            } else {
                System.out.println("Try again");
            }
        }

    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: lets the user pick which homework list they want to modify
    private void pickHomeworkList() throws FileNotFoundException, UnsupportedEncodingException {
        while (homeworkList.homeWorkList.keySet().size() > 0) {
            System.out.println("do you wish to edit any homework lists? (y|n)");
            String choice = takeInput.nextLine();
            if (choice.equals("n")) {
                break;
            } else {
                System.out.println("enter a course name to edit its assignments");
                try {
                    getHomeworkList();
                } catch (NullPointerException e) {
                    System.out.println("No list matching that course name, try again");
                }
            }
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: gets the right homework list according to the course inputted
    private void getHomeworkList() throws FileNotFoundException, UnsupportedEncodingException {
        Course course = new Course(takeInput.nextLine());
        HashSet<Homework> homework = course.getSetOfHomework(homeworkList.homeWorkList);
        if (homework.isEmpty()) {
            System.out.println("there is no assignments for that course");
        } else {
            homeworkListOptions(course, homework);
        }
    }

    //REQUIRES: nothing
    //MODIFIES: homework
    //EFFECTS: gives the user the choice to delete or change the status of a homework item
    private void homeworkListOptions(
            Course course, HashSet<Homework> homework) throws FileNotFoundException, UnsupportedEncodingException {
        while (homework.size() > 0) {
            System.out.println(hwOptionP1 + hwOptionP2 + hwOptionP3 + hwOptionP4);
            String choice = takeInput.nextLine();
            if (choice.equals("1")) {
                deleteHomework(course, homework);
            } else if (choice.equals("2")) {
                markHomework(homework);
            } else if (choice.equals("3")) {
                break;
            } else {
                System.out.println("Try again");
            }
        }
        homeworkList.save();

    }


    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: asks the user if they wish to remove an item from the list
    private void delete(ListOfItems li) throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println(li.print());
        System.out.println("Which item would you like to remove? (enter an integer)");
        int i = takeInput.nextInt();
        takeInput.nextLine();
        try {
            li.remove(i);
        } catch (ItemDoesNotExistException e) {
            System.out.println("Invalid index, try again");
        } finally {
            System.out.println(li.print());
            li.save();
        }

    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: asks the user if they wish to change the status of an item in the list
    private void mark(ListOfItems li) throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println(li.print());
        System.out.println("Which item's status would you like to change? (enter an integer)");
        int i = takeInput.nextInt();
        takeInput.nextLine();
        try {
            li.changeStatus(i);
        } catch (ItemDoesNotExistException e) {
            System.out.println("Invalid index, try again");
        } finally {
            System.out.println(li.print());
            li.save();
        }
    }

    //REQUIRES: nothing
    //MODIFIES: homework, Homework
    //EFFECTS: deletes the item that matches the user input
    private void deleteHomework(Course course, HashSet<Homework> homework) {
        System.out.println(homework.toString());
        System.out.println("enter the name of the assignment you want to delete");
        String assignment = takeInput.nextLine();
        System.out.println(homeworkList.delete(course, assignment, homework));
        printHomeworkList();

    }

    //REQUIRES: nothing
    //MODIFIES: homework, Homework
    //EFFECTS: changes the status of the homework that matches the user input
    private void markHomework(HashSet<Homework> homework) {
        System.out.println(homework.toString());
        System.out.println("enter the name of the assignment you want to change the status of");
        String assignment = takeInput.nextLine();
        System.out.println(homeworkList.changeStatus(assignment, homework));
        printHomeworkList();
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: instantiates a new ToDoList and starts the program
    public static void main(String[] args) throws IOException {
        new AppRunner();
    }
}
