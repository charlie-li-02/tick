package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
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
    void testItemMaker() {
        Item testItem1 = lri.itemMaker("a", "b");
        assertFalse(testItem1.getIsDone());
        assertEquals("a", testItem1.getTitle());
        assertEquals("b", testItem1.getAttribute());

        Item testItem2 = lri.itemMaker("c", "d");
        assertFalse(testItem2.getIsDone());
        assertEquals("c", testItem2.getTitle());
        assertEquals("d", testItem2.getAttribute());
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
        assertEquals("a;2;false", savedItems.get(0));
        assertEquals("b;3;true", savedItems.get(1));
        assertEquals("c;7;false", savedItems.get(2));}

}
