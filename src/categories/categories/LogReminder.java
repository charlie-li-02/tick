package categories;

public class LogReminder {
    String reminder;
    String time;

    public LogReminder() {
        reminder = "";
        time = "";
    }

    public void reminderMaker(String reminder, String time) {
        this.reminder = reminder;
        this.time = time;
    }

    public String toString() {
        return "Remind me to: " + reminder + " at " + time;
    }
}
