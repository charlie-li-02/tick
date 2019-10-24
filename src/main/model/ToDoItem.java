package model;

public class ToDoItem extends Item {
    private String title;
    private String description;
    private Boolean isDone;

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: constructor for ToDoItem
    public ToDoItem(String title, String description, Boolean isDone) {
        super(title, description, isDone);

        this.title = title;
        this.description = description;
        this.isDone = isDone;
    }

    @Override
    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: formats the item it was called on and coverts it to a string
    public String toString() {
        return "Title: " + title + ", Description: " + description + ", Done?: " + isDone;
    }

}
