package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ListOfToDo extends ListOfItems implements Save, Load {

    private ArrayList<Item> listOfTDI;
    private String toDoSavePath = "todos.txt";

    public String promptTitle = "Enter a title and description for your new to do:";
    public String promptAnother = "Do you want to add another to do?";

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: the constructor for To Do
    public ListOfToDo() {
        super();
        this.listOfTDI = new ArrayList<>();
    }

    //REQUIRES: nothing
    //MODIFIES: ToDoItem
    //EFFECTS: creates a new apparent type Item actual type ToDoItem
    public Item itemMaker(String title, String description) {
        Item tdi = new ToDoItem(title, description, false);
        return tdi;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: formats the list of items into a list of string
    public ArrayList<String> print() {
        ArrayList<String> result = new ArrayList<>();
        for (Item i: listOfItems) {
            String s = "Title: " + i.getTitle() + " Description: " + i.getAttribute() + " Done? " + i.getIsDone();
            result.add(s);
        }
        return result;
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: loads the items in the save file into the list
    public void load() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(toDoSavePath));
        for (String line : lines) {
            ArrayList<String> parts = split(line);
            Item i = new ToDoItem(parts.get(0), parts.get(1), stringToBoolean(parts.get(2)));
            addItem(i);
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the PromptTitle
    public String getPromptTitle() {
        return promptTitle;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the PromptAnother
    public String getPromptAnother() {
        return promptAnother;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the SavePath
    public String getSavePath() {
        return toDoSavePath;
    }
}

