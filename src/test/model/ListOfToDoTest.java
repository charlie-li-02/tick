package model;

import exceptions.ItemDoesNotExistException;

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

    @BeforeEach
    void setup() {
        tdi1 = new ToDoItem("a", "b", false);
        tdi2 = new ToDoItem("c", "d", true);
        tdi3 = new ToDoItem("e", "f", false);

        ltdi = new ListOfToDo();
        ltdi.addItem(tdi1);
        ltdi.addItem(tdi2);
        ltdi.addItem(tdi3);
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
    void testLoad() throws IOException, ItemDoesNotExistException {
        ltdi.save();

        ltdi.remove(2);
        ltdi.remove(1);
        ltdi.remove(0);

        ltdi.load();

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

        PrintWriter fileClearer = new PrintWriter(ltdi.getSavePath(), "UTF-8");
        fileClearer.close();
    }

    @Test
    void testSave() throws IOException {

        PrintWriter fileClearer = new PrintWriter(ltdi.getSavePath(), "UTF-8");
        fileClearer.close();

        ltdi.save();

        List<String> savedItems = Files.readAllLines(Paths.get(ltdi.getSavePath()));

        assertEquals(3, savedItems.size());
        assertEquals("a;b;false", savedItems.get(0));
        assertEquals("c;d;true", savedItems.get(1));
        assertEquals("e;f;false", savedItems.get(2));

        PrintWriter fileClearer2 = new PrintWriter(ltdi.getSavePath(), "UTF-8");
        fileClearer2.close();
    }

    @Test
    void testPrint() {
        ArrayList<String> printed;
        printed = ltdi.print();
        assertEquals("Title: a Description: b Done? false", printed.get(0));
        assertEquals("Title: c Description: d Done? true", printed.get(1));
        assertEquals("Title: e Description: f Done? false", printed.get(2));

    }

    @Test
    void testGetPromptTitle() {
        String temporary = ltdi.getPromptTitle();
        assertEquals("Enter a title for your new to do:", temporary);
    }

    @Test
    void testGetPromptAnother() {
        String temporary = ltdi.getPromptAnother();
        assertEquals("Do you want to add another to do? (y|n)", temporary);
    }

}
