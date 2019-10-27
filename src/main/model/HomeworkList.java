package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class HomeworkList implements Save, Load {
    public Map<Course, HashSet<Homework>> homeWorkList;
    private static final String savePath = "homework.txt";

    public HomeworkList() {
        homeWorkList = new HashMap<>();
    }


    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the set of homework associated with the course given
    public HashSet<Homework> getHomework(Course course) {
        for (Course c: homeWorkList.keySet()) {
            if (c.equals(course)) {
                return homeWorkList.get(c);
            }
        }
        return null;
    }

    //REQUIRES: nothing
    //MODIFIES: this, setOfHomework
    //EFFECTS: deletes the assignment entered if it is in the set, and return the total amount of assignments left
    //         after deleting
    public String delete(String assignment, HashSet<Homework> setOfHomework) {
        for (Homework h: setOfHomework) {
            if (h.getAssignment().equals(assignment)) {
                setOfHomework.remove(h);
                return "By removing " + assignment + " you have " + setOfHomework.size() + " assignments left for "
                        + h.getCourse().getCourseName();
            }
        }
        return "Cannot find assignment matching that name, try again";
    }

    //REQUIRES: nothing
    //MODIFIES: this, setOfHomework, h
    //EFFECTS: changes the done status of the assignment enter if it is in the set, and returns the total amount of
    //         assignments done out of the total number of assignments
    public String changeStatus(String assignment, HashSet<Homework> setOfHomework) {
        for (Homework h: setOfHomework) {
            if (h.getAssignment().equals(assignment)) {
                h.flipStatus();
                return "Now you have " + getHWDone(setOfHomework) + " assignments done for "
                        + h.getCourse().getCourseName() + " out of " + setOfHomework.size();
            }
        }
        return "Cannot find assignment matching that name, try again";
    }

    //REQUIRES: nothing
    //MODIFIES: noting
    //EFFECTS: returns the number of homework that is done in setOfHomework
    public int getHWDone(HashSet<Homework> setOfHomework) {
        int done = 0;
        for (Homework h: setOfHomework) {
            if (h.getIsDone()) {
                done++;
            }
        }
        return done;
    }


    //REQUIRES: nothing
    //MODIFIES: HomeworkList
    //EFFECTS: puts the homework list to the course if it already exists, makes a new key if it doesn't
    public void addHomeworkList(Course course, HashSet<Homework> setOfHW) {
        if (homeWorkList.keySet().size() == 0) {
            homeWorkList.put(course, setOfHW);
        } else {
            for (Course c: homeWorkList.keySet()) {
                if (course.equals(c)) {
                    HashSet<Homework> mergedSet = new HashSet<>();
                    mergedSet.addAll(homeWorkList.get(c));
                    mergedSet.addAll(setOfHW);
                    homeWorkList.put(c, mergedSet);
                } else {
                    homeWorkList.put(course, setOfHW);
                }
            }
        }
    }

    @Override
    public void load() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(savePath));
        for (String line : lines) {
            ArrayList<String> parts = split(line);
            Course course = new Course(parts.get(0));
            Homework homework = new Homework(course, parts.get(1), parts.get(2), stringToBoolean(parts.get(3)));
            HashSet<Homework> setOfHW = new HashSet<>();
            setOfHW.add(homework);
            addHomeworkList(course, setOfHW);
        }
    }

    //REQUIRES: nothing
    //MODIFIES: homework.txt
    //EFFECTS: saves the homework list into the text file
    @Override
    public void save() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter fileClearer = new PrintWriter(savePath, "UTF-8");
        fileClearer.close();
        PrintWriter writer = new PrintWriter(savePath, "UTF-8");
        ArrayList<String> lines;
        for (Course course: homeWorkList.keySet()) {
            lines = merge(course);

            for (String line : lines) {
                writer.println(line);
            }
        }
        writer.close();
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: converts homework list into an entry for the save file
    public ArrayList<String> merge(Course course) {
        ArrayList<String> lines = new ArrayList<>();
        for (Homework h: course.getSetOfHomework(homeWorkList)) {
            lines.add(course.getCourseName() + ";" + h.getAssignment() + ";" + h.getDueBy() + ";"
                    + booleanToString(h.getIsDone()));
        }
        return lines;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: splits a single line of the save file into pieces at where there are ";"
    public static ArrayList<String> split(String line) {
        String[] splits = line.split(";");
        return new ArrayList<>(Arrays.asList(splits));
    }

    //REQUIRES: s to be either "true" or "false"
    //MODIFIES: nothing
    //EFFECTS: converts a string representation of a boolean value into a boolean
    public static Boolean stringToBoolean(String s) {
        if (s.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: converts a boolean into a string representation of that boolean
    public static String booleanToString(Boolean b) {
        if (b) {
            return "true";
        } else {
            return "false";
        }
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: sets the homeworkList
    public void setHomeWorkList(Map<Course, HashSet<Homework>> homeworkMap) {
        this.homeWorkList = homeworkMap;
    }

    public String getSavePath() {
        return savePath;
    }
}