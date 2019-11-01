package model;

public class HomeworkItem extends Item {

    private Course course;
    private String assignment;
    private String dueBy;
    private Boolean isDone;

    public HomeworkItem(Course course, String assignment, String dueBy, Boolean isDone) {
        super(assignment, dueBy, isDone);

        this.course = course;
        this.assignment = assignment;
        this.dueBy = dueBy;
        this.isDone = isDone;
    }

    public Course getCourse() {
        return course;
    }

    //REQUIRES: nothing
    //MODIFIES: this, course
    //EFFECTS: sets the course
    public void setCourse(Course course) {
        if (!course.equals(this.course)) {
            this.course = course;
            course.addHomework(this);
        }
    }

    //REQUIRES: nothing
    //MODIFIES: this, course
    //EFFECTS: removes the course
    public void removeCourse(Course course) {
        if (course.equals(this.course)) {
            this.course = null;
            course.removeHomework(this);
        }
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
