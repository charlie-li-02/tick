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
        lri.save("RLoadTest.txt");

        lri.remove(2);
        lri.remove(1);
        lri.remove(0);

        lri.load("RLoadTest.txt");

        assertEquals(3, lri.getSize());

        assertEquals("a", lri.get(0).getTitle());
        assertEquals("2", lri.get(0).getAttribute());
        assertFalse(lri.get(0).getIsDone());

        assertEquals("b", lri.get(1).getTitle());
        assertEquals("3", lri.get(1).getAttribute());
        assertTrue(lri.get(1).getIsDone());

        assertEquals("c", lri.get(2).getTitle());
        assertEquals("7", lri.get(2).getAttribute());
        assertFalse(lri.get(2).getIsDone());
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
        assertEquals("c;7;false", savedItems.get(2));
    }

    @Test
    void testPrint() {
        ArrayList<String> printed = new ArrayList<>();
        printed = lri.print();
        assertEquals("Remind me to a at 2 Done? false", printed.get(0));
        assertEquals("Remind me to b at 3 Done? true", printed.get(1));
        assertEquals("Remind me to c at 7 Done? false", printed.get(2));
    }

}
