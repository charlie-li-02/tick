package model;

public interface List {

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the ith item
    Item get(int i);

    //REQUIRES: at least one item in the array
    //MODIFIES: the array
    //EFFECTS: removes the ith item
    Item remove(int i);
}
