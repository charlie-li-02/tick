package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ListOfToDo extends ListOfItems implements Save, Load {

    private ArrayList<Item> listOfTDI;

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: the constructor for To Do
    public ListOfToDo(ArrayList<Item> ltdi) {
        super(ltdi);
        this.listOfTDI = ltdi;
    }

    @Override
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

}

