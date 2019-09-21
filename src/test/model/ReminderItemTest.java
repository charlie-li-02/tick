package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class ReminderItemTest {

    private ReminderItem ri1;
    private ReminderItem ri2;
    private String lr1ToString = "Remind me to: water at 2:00";
    private String lr2ToString = "Remind me to: eat at 6:00";

    @BeforeEach
    void setup() {
        ri1 = new ReminderItem("water","2:00",false);
        ri2 = new ReminderItem("eat","6:00",true);
    }

    @Test
    void testGetReminder() {
        assertEquals("water", ri1.getReminder());
        assertEquals("eat", ri2.getReminder());
        assertFalse("sleep" == ri1.getReminder());
        assertFalse("water" == ri2.getReminder());
    }

    @Test
    void testGetTime() {
        assertEquals("2:00", ri1.getTime());
        assertEquals("6:00", ri2.getTime());
        assertFalse("5:00" == ri1.getTime());
        assertFalse("5:00" == ri2.getTime());
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

}
