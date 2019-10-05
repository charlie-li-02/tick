package model;

public class ReminderItem extends Item {
    private String reminder;
    private String time;
    private Boolean isDone;

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: constructor for ReminderItem
    public ReminderItem(String reminder, String time, Boolean isDone) {
        super(reminder, time, isDone);

        this.reminder = reminder;
        this.time = time;
        this.isDone = isDone;
    }

    @Override
    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: formats the item it was called on and coverts it to a string
    public String toString() {
        return "Remind me to: " + this.reminder + " at " + this.time + ", Done?: " + this.isDone;
    }
}
