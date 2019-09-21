package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class ReminderTest {

    private ReminderItem ri1;
    private ReminderItem ri2;
    private ReminderItem ri3;
    private ArrayList<ReminderItem> lri;

    @BeforeEach
    void setup() {
        ri1 = new ReminderItem("a", "2", false);
        ri2 = new ReminderItem("b", "3", true);
        ri3 = new ReminderItem("c", "7", false);

        lri = new ArrayList<>();
        lri.add(ri1);
        lri.add(ri2);
        lri.add(ri3);
    }

    @Test
    void testGet() {
        assertEquals(ri1, lri.get(0));
        assertEquals(ri2, lri.get(1));
        assertEquals(ri3, lri.get(2));
    }

    @Test
    void testRemove() {
        lri.remove(2);
        assertEquals(2, lri.size());
        lri.remove(1);
        assertEquals(1, lri.size());
        lri.remove(0);
        assertEquals(0, lri.size());
    }
}
