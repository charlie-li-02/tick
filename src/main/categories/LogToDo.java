package categories;

public class LogToDo {
    String title;
    String description;

    public LogToDo() {
        title = "";
        description = "";

    }

    public void toDoMaker(String title, String description) {
        this.title = title;
        this.description = description;
    }


    public String toString() {
        return "To do:\n"
                + "Title: " + title + "\n"
                + "Description: " + description + "\n";
    }


}
