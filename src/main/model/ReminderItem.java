package model;

public class ReminderItem {
    private String reminder;
    private String time;
    private Boolean isDone;

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: constructor for ReminderItem
    public ReminderItem(String reminder, String time, Boolean isDone) {
        this.reminder = reminder;
        this.time = time;
        this.isDone = isDone;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the name of the reminder
    public String getReminder() {
        return this.reminder;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the time of the reminder
    public String getTime() {
        return this.time;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns whether if the reminder is done
    public Boolean getIsDone() {
        return this.isDone;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: formats the reminder list it was called on and converts it to a string
    public String toString() {
        return "Remind me to: " + reminder + " at " + time;
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: sets the this as done represented by true
    public void markDone() {
        this.isDone = true;
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: sets the this as undone represented by false
    public void markUndone() {
        this.isDone = false;
    }
}
