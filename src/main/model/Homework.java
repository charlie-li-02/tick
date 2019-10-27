package model;

public class Homework extends Item {

    private Course course;
    private String assignment;
    private String dueBy;
    private Boolean isDone;

    public Homework(Course course, String assignment, String dueBy, Boolean isDone) {
        super(assignment, dueBy, isDone);

        this.course = course;
        this.assignment = assignment;
        this.dueBy = dueBy;
        this.isDone = isDone;
    }

    public Course getCourse() {
        return course;
    }


    public String getAssignment() {
        return assignment;
    }

    public String getDueBy() {
        return dueBy;
    }

    @Override
    public Boolean getIsDone() {
        return isDone;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: formats the homework
    @Override
    public String toString() {
        return assignment + " is due on " + dueBy + ", done? " + isDone;
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: toggles isDone
    @Override
    public void flipStatus() {
        isDone = !isDone;
    }

}
