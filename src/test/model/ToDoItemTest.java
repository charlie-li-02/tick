package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;



class ToDoItemTest {

    private ToDoItem tdi1;
    private ToDoItem tdi2;
    private String ltd1ToString = "Title: a, Description: b, Done?: " + false;
    private String ltd2ToString = "Title: c, Description: d, Done?: " + true;

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
        assertEquals("b", tdi1.getAttribute());
        assertEquals("d", tdi2.getAttribute());
        assertFalse(tdi1.getAttribute() == "a");
        assertFalse(tdi2.getAttribute() == "c");

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

    @Test
    void testFlipStatus() {
        tdi1.flipStatus();
        assertTrue(tdi1.getIsDone());
        tdi1.flipStatus();
        assertFalse(tdi1.getIsDone());

        tdi2.flipStatus();
        assertFalse(tdi2.getIsDone());
        tdi2.flipStatus();
        assertTrue(tdi2.getIsDone());
    }
}
