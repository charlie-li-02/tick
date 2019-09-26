package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfReminderTest {

    private ReminderItem ri1;
    private ReminderItem ri2;
    private ReminderItem ri3;
    private ListOfReminder lri;
    private ArrayList<Item> temp;

    @BeforeEach
    void setup() {
        ri1 = new ReminderItem("a", "2", false);
        ri2 = new ReminderItem("b", "3", true);
        ri3 = new ReminderItem("c", "7", false);

        temp = new ArrayList<>();
        temp.add(ri1);
        temp.add(ri2);
        temp.add(ri3);
        lri = new ListOfReminder(temp);
    }

    @Test
    void testReminderMaker() {
        Item testItem1 = lri.reminderMaker("a", "b");
        assertFalse(testItem1.getIsDone());
        assertEquals("a", testItem1.getTitle());
        assertEquals("b", testItem1.getAttribute());

        Item testItem2 = lri.reminderMaker("c", "d");
        assertFalse(testItem2.getIsDone());
        assertEquals("c", testItem2.getTitle());
        assertEquals("d", testItem2.getAttribute());
    }

    @Test
    void testAddItem() {
        lri.addItem(ri1);
        assertEquals(4, temp.size());
        assertEquals(ri1, lri.get(3));
    }

    @Test
    void testGet() {
        assertEquals(ri1, lri.get(0));
        assertEquals(ri2, lri.get(1));
        assertEquals(ri3, lri.get(2));
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

        loadList.load("RLoadTest.txt");

        assertEquals("hi", loadList.get(0).getTitle());
        assertEquals("2", loadList.get(0).getAttribute());
        assertTrue(loadList.get(0).getIsDone());

        assertEquals("yo", loadList.get(1).getTitle());
        assertEquals("7", loadList.get(1).getAttribute());
        assertFalse(loadList.get(1).getIsDone());
    }

    @Test
    void testSave() throws IOException {
        String path = "RSaveTest";
        PrintWriter fileClearer = new PrintWriter(path, "UTF-8");
        fileClearer.close();

        lri.save(path);

        List<String> savedItems = Files.readAllLines(Paths.get(path));

        assertEquals(3, savedItems.size());
        assertEquals("a 2 false", savedItems.get(0));
        assertEquals("b 3 true", savedItems.get(1));
        assertEquals("c 7 false", savedItems.get(2));}

    @Test
    void testSplit() {
        String testString1 = "hi";
        ArrayList<String> testArray1 = lri.split(testString1);
        assertEquals(1, testArray1.size());
        assertEquals("hi", testArray1.get(0));

        String testString2 = "hi bye hello nihao";
        ArrayList<String> testArray2 = lri.split(testString2);
        assertEquals(4, testArray2.size());
        assertEquals("hi", testArray2.get(0));
        assertEquals("bye", testArray2.get(1));
        assertEquals("hello", testArray2.get(2));
        assertEquals("nihao", testArray2.get(3));
    }

    @Test
    void testMerge() {
        assertEquals("a 2 false", lri.merge(ri1));
        assertEquals("b 3 true", lri.merge(ri2));
        assertEquals("c 7 false", lri.merge(ri3));
    }

    @Test
    void testStringToBoolean() {
        String t = "true";
        String f = "false";

        assertTrue(lri.stringToBoolean(t));
        assertFalse(lri.stringToBoolean(f));
    }

    @Test
    void testBooleanToString() {
        assertEquals("true", lri.booleanToString(true));
        assertEquals("false", lri.booleanToString(false));
    }

}
