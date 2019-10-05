package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ListOfToDo extends ListOfItems implements Save, Load {

    private ArrayList<Item> listOfTDI;
    public String askForTitle;
    public String askForAttribute;

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: the constructor for To Do
    public ListOfToDo(ArrayList<Item> ltdi) {
        super(ltdi);
        this.listOfTDI = ltdi;
    }

    //REQUIRES: nothing
    //MODIFIES: ToDoItem
    //EFFECTS: creates a new apparent type Item actual type ToDoItem
    public Item itemMaker(String title, String description) {
        Item tdi = new ToDoItem(title, description, false);
        return tdi;
    }

    //REQUIRES: todos.txt is in the right path
    //MODIFIES: this, ToDoItem
    //EFFECTS: reads the save file and adds the saved to do items into the list
    public void load(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        for (String line : lines) {
            ArrayList<String> parts = split(line);
            Item tdi = new ToDoItem(parts.get(0), parts.get(1), stringToBoolean(parts.get(2)));
            addItem(tdi);
        }
    }

    //REQUIRES: todos.txt is in the right path
    //MODIFIES: todos.txt
    //EFFECTS: adds the ToDoItems into the save file
    public void save(String path) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter fileClearer = new PrintWriter(path, "UTF-8");
        fileClearer.close();
        PrintWriter writer = new PrintWriter(path, "UTF-8");
        for (Item item: this.listOfTDI) {
            String line = merge(item);
            writer.println(line);
        }
        writer.close();
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: formats the list of items into a list of string
    public ArrayList<String> print() {
        ArrayList<String> result = new ArrayList<>();
        for (Item i: this.listOfItem) {
            String s = "Title: " + i.getTitle() + " Description: " + i.getAttribute() + " Done? " + i.getIsDone();
            result.add(s);
        }
        return result;
    }

}

