package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class ToDoTest {

    private ToDoItem tdi1;
    private ToDoItem tdi2;
    private ToDoItem tdi3;
    private ArrayList<ToDoItem> ltdi;

    @BeforeEach
    void setup() {
        tdi1 = new ToDoItem("a", "b", false);
        tdi2 = new ToDoItem("c", "d", true);
        tdi3 = new ToDoItem("e", "f", false);

        ltdi = new ArrayList<>();
        ltdi.add(tdi1);
        ltdi.add(tdi2);
        ltdi.add(tdi3);
    }

    @Test
    void testGet() {
        assertEquals(tdi1, ltdi.get(0));
        assertEquals(tdi2, ltdi.get(1));
        assertEquals(tdi3, ltdi.get(2));
    }

    @Test
    void testRemove() {
        ltdi.remove(2);
        assertEquals(2, ltdi.size());
        ltdi.remove(1);
        assertEquals(1, ltdi.size());
        ltdi.remove(0);
        assertEquals(0, ltdi.size());
    }
}
