package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
    void testItemMaker() {
        Item testItem1 = ltdi.itemMaker("a", "b");
        assertFalse(testItem1.getIsDone());
        assertEquals("a", testItem1.getTitle());
        assertEquals("b", testItem1.getAttribute());

        Item testItem2 = ltdi.itemMaker("c", "d");
        assertFalse(testItem2.getIsDone());
        assertEquals("c", testItem2.getTitle());
        assertEquals("d", testItem2.getAttribute());
    }

    @Test
    void testLoad() throws IOException {
        ltdi.save("TDLoadTest.txt");

        ltdi.remove(2);
        ltdi.remove(1);
        ltdi.remove(0);

        ltdi.load("TDLoadTest.txt");

        assertEquals(3, ltdi.getSize());

        assertEquals("a", ltdi.get(0).getTitle());
        assertEquals("b", ltdi.get(0).getAttribute());
        assertFalse(ltdi.get(0).getIsDone());

        assertEquals("c", ltdi.get(1).getTitle());
        assertEquals("d", ltdi.get(1).getAttribute());
        assertTrue(ltdi.get(1).getIsDone());

        assertEquals("e", ltdi.get(2).getTitle());
        assertEquals("f", ltdi.get(2).getAttribute());
        assertFalse(ltdi.get(2).getIsDone());
    }

    @Test
    void testSave() throws IOException {
        String path = "TDSaveTest";
        PrintWriter fileClearer = new PrintWriter(path, "UTF-8");
        fileClearer.close();

        ltdi.save(path);

        List<String> savedItems = Files.readAllLines(Paths.get(path));

        assertEquals(3, savedItems.size());
        assertEquals("a;b;false", savedItems.get(0));
        assertEquals("c;d;true", savedItems.get(1));
        assertEquals("e;f;false", savedItems.get(2));
    }

}
