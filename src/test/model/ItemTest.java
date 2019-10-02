package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ItemTest {
    private Item i1;
    private Item i2;
    private String li1ToString = "Title: water, Description: 2:00, Done?: " + false;
    private String li2ToString = "Title: eat, Description: 6:00, Done?: " + true;

    @BeforeEach
    void setup() {
        i1 = new Item("water","2:00",false);
        i2 = new Item("eat","6:00",true);
    }

    @Test
    void testGetReminder() {
        assertEquals("water", i1.getTitle());
        assertEquals("eat", i2.getTitle());
        assertFalse("sleep" == i1.getTitle());
        assertFalse("water" == i2.getTitle());
    }

    @Test
    void testGetTime() {
        assertEquals("2:00", i1.getAttribute());
        assertEquals("6:00", i2.getAttribute());
        assertFalse("5:00" == i1.getAttribute());
        assertFalse("5:00" == i2.getAttribute());
    }

    @Test
    void testGetIsDone() {
        assertFalse(i1.getIsDone());
        assertTrue(i2.getIsDone());
    }

    @Test
    void testToString() {
        assertEquals(li1ToString, i1.toString());
        assertEquals(li2ToString, i2.toString());
    }

    @Test
    void testMarkDone() {
        i1.markDone();
        assertTrue(i1.getIsDone());
    }

    @Test
    void testMarkUndone() {
        i2.markUndone();
        assertFalse(i2.getIsDone());
    }

    @Test
    void testFlipStatus() {
        i1.flipStatus();
        assertTrue(i1.getIsDone());
        i1.flipStatus();
        assertFalse(i1.getIsDone());

        i2.flipStatus();
        assertFalse(i2.getIsDone());
        i2.flipStatus();
        assertTrue(i2.getIsDone());
    }


}
