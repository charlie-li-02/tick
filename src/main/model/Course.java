package model;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

public class Course {

    private String courseName;
    private HashSet<Homework> setOfHomework;

    public Course(String courseName) {
        this.courseName = courseName;
        this.setOfHomework = new HashSet<>();
    }

    public String getCourseName() {
        return courseName;
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: returns the the set mapped to this, also updates the set mapped to this
    //         CALL THIS METHOD BEFORE CALLING THE OVERLOADED getSetOfHomeWork BELOW
    public HashSet<Homework> getSetOfHomework(Map<Course, HashSet<Homework>> homeworkList) {
        if (homeworkList.keySet().contains(this)) {
            this.setOfHomework = homeworkList.get(this);
            return homeworkList.get(this);
        }
        return null;
    }

    //REQUIRES: getSetOfHomework(Map) was called before this
    //MODIFIES: nothing
    //EFFECTS: returns the setOfHomework
    public HashSet<Homework> getSetOfHomework() {
        return this.setOfHomework;
    }

    //REQUIRES: nothing
    //MODIFIES: this, homework
    //EFFECTS: adds homework to the setOfHomework if not already in the set
    public void addHomework(Homework homework) {
        if (!setOfHomework.contains(homework)) {
            setOfHomework.add(homework);
            homework.setCourse(this);
        }
    }

    //REQUIRES: nothing
    //MODIFIES: this, homework
    //EFFECTS: removes the homework if it's in the set
    public void removeHomework(Homework homework) {
        if (setOfHomework.contains(homework)) {
            setOfHomework.remove(homework);
            homework.removeCourse(this);
        }
    }

    @Override
    public String toString() {
        return courseName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Course)) {
            return false;
        }
        Course course = (Course) o;
        return courseName.equals(course.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName);
    }
}
