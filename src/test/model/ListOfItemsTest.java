package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class ListOfItemsTest {
    private Item i1;
    private Item i2;
    private Item i3;
    private ListOfItems li;
    private ArrayList<Item> temp;

    @BeforeEach
    void setup() {
        i1 = new Item("a", "2", false);
        i2 = new Item("b", "3", true);
        i3 = new Item("c", "7", false);

        temp = new ArrayList<>();
        temp.add(i1);
        temp.add(i2);
        temp.add(i3);
        li = new ListOfReminder(temp);
    }

    @Test
    void testAddItem() {
        li.addItem(i1);
        assertEquals(4, temp.size());
        assertEquals(i1, li.get(3));
    }

    @Test
    void testGet() {
        assertEquals(i1, li.get(0));
        assertEquals(i2, li.get(1));
        assertEquals(i3, li.get(2));
    }

    @Test
    void testGetSize() {
        assertEquals(3, li.getSize());
    }

    @Test
    void testRemove() {
        li.remove(2);
        assertEquals(2, li.getSize());
        li.remove(1);
        assertEquals(1, li.getSize());
        li.remove(0);
        assertEquals(0, li.getSize());
    }


    @Test
    void testSplit() {
        String testString1 = "hi";
        ArrayList<String> testArray1 = li.split(testString1);
        assertEquals(1, testArray1.size());
        assertEquals("hi", testArray1.get(0));

        String testString2 = "hi;bye;hello;nihao";
        ArrayList<String> testArray2 = li.split(testString2);
        assertEquals(4, testArray2.size());
        assertEquals("hi", testArray2.get(0));
        assertEquals("bye", testArray2.get(1));
        assertEquals("hello", testArray2.get(2));
        assertEquals("nihao", testArray2.get(3));
    }

    @Test
    void testMerge() {
        assertEquals("a;2;false", li.merge(i1));
        assertEquals("b;3;true", li.merge(i2));
        assertEquals("c;7;false", li.merge(i3));
    }

    @Test
    void testStringToBoolean() {
        String t = "true";
        String f = "false";

        assertTrue(li.stringToBoolean(t));
        assertFalse(li.stringToBoolean(f));
    }

    @Test
    void testBooleanToString() {
        assertEquals("true", li.booleanToString(true));
        assertEquals("false", li.booleanToString(false));
    }
}
