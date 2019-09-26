package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListOfToDoTest {

    private Item tdi1;
    private Item tdi2;
    private Item tdi3;
    private ListOfToDo ltdi;
    private ArrayList<Item> temp;

    @BeforeEach
    void setup() {
        tdi1 = new ToDoItem("a", "b", false);
        tdi2 = new ToDoItem("c", "d", true);
        tdi3 = new ToDoItem("e", "f", false);

        temp = new ArrayList<>();
        temp.add(tdi1);
        temp.add(tdi2);
        temp.add(tdi3);
        ltdi = new ListOfToDo(temp);
    }

    @Test
    void testToDoMaker() {
        Item testItem1 = ltdi.toDoMaker("a", "b");
        assertFalse(testItem1.getIsDone());
        assertEquals("a", testItem1.getTitle());
        assertEquals("b", testItem1.getAttribute());

        Item testItem2 = ltdi.toDoMaker("c", "d");
        assertFalse(testItem2.getIsDone());
        assertEquals("c", testItem2.getTitle());
        assertEquals("d", testItem2.getAttribute());
    }

    @Test
    void testAddItem() {
        ltdi.addItem(tdi1);
        assertEquals(4, temp.size());
        assertEquals(tdi1, ltdi.get(3));
    }

    @Test
    void testGet() {
        assertEquals(tdi1, ltdi.get(0));
        assertEquals(tdi2, ltdi.get(1));
        assertEquals(tdi3, ltdi.get(2));
    }

    @Test
    void testRemove() {
        temp.remove(2);
        assertEquals(2, temp.size());
        temp.remove(1);
        assertEquals(1, temp.size());
        temp.remove(0);
        assertEquals(0, temp.size());
    }

    @Test
    void testLoad() throws IOException {
        ArrayList<Item> empty = new ArrayList<>();
        ListOfToDo loadList = new ListOfToDo(empty);

        loadList.load("TDLoadTest.txt");

        assertEquals("hi", loadList.get(0).getTitle());
        assertEquals("bye", loadList.get(0).getAttribute());
        assertTrue(loadList.get(0).getIsDone());

        assertEquals("yo", loadList.get(1).getTitle());
        assertEquals("hello", loadList.get(1).getAttribute());
        assertFalse(loadList.get(1).getIsDone());

    }

    @Test
    void testSave() throws IOException {
        String path = "TDSaveTest";
        PrintWriter fileClearer = new PrintWriter(path, "UTF-8");
        fileClearer.close();

        ltdi.save(path);

        List<String> savedItems = Files.readAllLines(Paths.get(path));

        assertEquals(3, savedItems.size());
        assertEquals("a b false", savedItems.get(0));
        assertEquals("c d true", savedItems.get(1));
        assertEquals("e f false", savedItems.get(2));
    }

    @Test
    void testSplit() {
        String testString1 = "hi";
        ArrayList<String> testArray1 = ltdi.split(testString1);
        assertEquals(1, testArray1.size());
        assertEquals("hi", testArray1.get(0));

        String testString2 = "hi bye hello nihao";
        ArrayList<String> testArray2 = ltdi.split(testString2);
        assertEquals(4, testArray2.size());
        assertEquals("hi", testArray2.get(0));
        assertEquals("bye", testArray2.get(1));
        assertEquals("hello", testArray2.get(2));
        assertEquals("nihao", testArray2.get(3));
    }

    @Test
    void testMerge() {
        assertEquals("a b false", ltdi.merge(tdi1));
        assertEquals("c d true", ltdi.merge(tdi2));
        assertEquals("e f false", ltdi.merge(tdi3));
    }

    @Test
    void testStringToBoolean() {
        String t = "true";
        String f = "false";

        assertTrue(ltdi.stringToBoolean(t));
        assertFalse(ltdi.stringToBoolean(f));
    }

    @Test
    void testBooleanToString() {
        assertEquals("true", ltdi.booleanToString(true));
        assertEquals("false", ltdi.booleanToString(false));
    }

}
