package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class ReminderItemTest {

    private ReminderItem ri1;
    private ReminderItem ri2;
    private String lr1ToString = "Remind me to: water at 2:00, Done?: " + false;
    private String lr2ToString = "Remind me to: eat at 6:00, Done?: " + true;

    @BeforeEach
    void setup() {
        ri1 = new ReminderItem("water","2:00",false);
        ri2 = new ReminderItem("eat","6:00",true);
    }

    @Test
    void testGetReminder() {
        assertEquals("water", ri1.getTitle());
        assertEquals("eat", ri2.getTitle());
        assertFalse("sleep" == ri1.getTitle());
        assertFalse("water" == ri2.getTitle());
    }

    @Test
    void testGetTime() {
        assertEquals("2:00", ri1.getAttribute());
        assertEquals("6:00", ri2.getAttribute());
        assertFalse("5:00" == ri1.getAttribute());
        assertFalse("5:00" == ri2.getAttribute());
    }

    @Test
    void testGetIsDone() {
        assertFalse(ri1.getIsDone());
        assertTrue(ri2.getIsDone());
    }

    @Test
    void testToString() {
        assertEquals(lr1ToString, ri1.toString());
        assertEquals(lr2ToString, ri2.toString());
    }

    @Test
    void testMarkDone() {
        ri1.markDone();
        assertTrue(ri1.getIsDone());
    }

    @Test
    void testMarkUndone() {
        ri2.markUndone();
        assertFalse(ri2.getIsDone());
    }

    @Test
    void testFlipStatus() {
        ri1.flipStatus();
        assertTrue(ri1.getIsDone());
        ri1.flipStatus();
        assertFalse(ri1.getIsDone());

        ri2.flipStatus();
        assertFalse(ri2.getIsDone());
        ri2.flipStatus();
        assertTrue(ri2.getIsDone());
    }

}
