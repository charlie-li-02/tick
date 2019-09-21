package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;



class ToDoItemTest {

    private ToDoItem tdi1;
    private ToDoItem tdi2;
    private String ltd1ToString = "To do:\n" + "Title: a\n" + "Description: b\n";
    private String ltd2ToString = "To do:\n" + "Title: c\n" + "Description: d\n";

    @BeforeEach
    void setup(){
        tdi1 = new ToDoItem("a", "b", false);
        tdi2 = new ToDoItem("c", "d", true);
    }

    @Test
    void testGetTitle() {
        assertEquals("a", tdi1.getTitle());
        assertEquals("c", tdi2.getTitle());
        assertFalse(tdi1.getTitle() == "b");
        assertFalse(tdi2.getTitle() == "d");
    }

    @Test
    void testGetDescription() {
        assertEquals("b", tdi1.getDescription());
        assertEquals("d", tdi2.getDescription());
        assertFalse(tdi1.getDescription() == "a");
        assertFalse(tdi2.getDescription() == "c");

    }

    @Test
    void testGetIsDone() {
        assertFalse(tdi1.getIsDone());
        assertTrue(tdi2.getIsDone());
    }

    @Test
    void testToString() {
        assertEquals(ltd1ToString, tdi1.toString());
        assertEquals(ltd2ToString, tdi2.toString());
    }

    @Test
    void testMarkDone() {
        tdi1.markDone();
        assertTrue(tdi1.getIsDone());
    }

    @Test
    void testMarkUndone() {
        tdi2.markUndone();
        assertFalse(tdi2.getIsDone());
    }
}
