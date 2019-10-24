package model;

import exceptions.DeletingNoneExistentItem;
import exceptions.ItemDoesNotExistException;
import exceptions.MarkingNoneExistentItem;
import exceptions.TooManyItemsUndoneException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


public class ListOfItemsTest {
    private Item i1;
    private Item i2;
    private Item i3;
    private ListOfItems li;



    private ListOfItems addNewTestList = new ListOfToDo();

    @BeforeEach
    void setup() {
        i1 = new Item("a", "2", false);
        i2 = new Item("b", "3", true);
        i3 = new Item("c", "7", false);

        li = new ListOfReminder();
        li.addItem(i1);
        li.addItem(i2);
        li.addItem(i3);
    }

    @Test
    void testAddItem() {
        li.addItem(i1);
        assertEquals(4, li.getSize());
        assertEquals(i1, li.get(3));
    }

    @Test
    void testAddNewItemCanAdd() throws TooManyItemsUndoneException {
        addNewTestList.addNewItem(i1);
        assertEquals(1, addNewTestList.getSize());
        assertEquals(i1, addNewTestList.get(0));

        addNewTestList.addNewItem(i3);
        assertEquals(2, addNewTestList.getSize());
        assertEquals(i3, addNewTestList.get(1));

        //ADDING 9 UNDONE ITEMS
        addNewTestList.addNewItem(i3);
        assertEquals(3, addNewTestList.getSize());
        addNewTestList.addNewItem(i3);
        assertEquals(4, addNewTestList.getSize());
        addNewTestList.addNewItem(i3);
        assertEquals(5, addNewTestList.getSize());
        addNewTestList.addNewItem(i3);
        assertEquals(6, addNewTestList.getSize());
        addNewTestList.addNewItem(i3);
        assertEquals(7, addNewTestList.getSize());
        addNewTestList.addNewItem(i3);
        assertEquals(8, addNewTestList.getSize());
        addNewTestList.addNewItem(i3);
        assertEquals(9, addNewTestList.getSize());

        //ADDING MORE DONE ITEMS
        addNewTestList.addNewItem(i2);
        assertEquals(10, addNewTestList.getSize());
        assertEquals(i2, addNewTestList.get(9));
        addNewTestList.addNewItem(i2);
        assertEquals(11, addNewTestList.getSize());
        assertEquals(i2, addNewTestList.get(10));

        //ADDING ONE LAST UNDONE ITEM TO REACH maxUndone = 10
        addNewTestList.addNewItem(i1);
        assertEquals(12, addNewTestList.getSize());
        assertEquals(i1, addNewTestList.get(11));
    }

    @Test
    void testAddNewItemCannotAdd() {
        try {
            addNewTestList.addNewItem(i3);
            addNewTestList.addNewItem(i3);
            addNewTestList.addNewItem(i3);
            addNewTestList.addNewItem(i3);
            addNewTestList.addNewItem(i3);
            addNewTestList.addNewItem(i3);
            addNewTestList.addNewItem(i3);
            addNewTestList.addNewItem(i3);
            addNewTestList.addNewItem(i3);
            addNewTestList.addNewItem(i3);
            addNewTestList.addNewItem(i3);
            fail();
        } catch (TooManyItemsUndoneException e) {
            System.out.println("too many undone items");
        }
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
        try {
            li.remove(2);
            assertEquals(2, li.getSize());
            li.remove(1);
            assertEquals(1, li.getSize());
            li.remove(0);
            assertEquals(0, li.getSize());
        } catch (ItemDoesNotExistException e) {
            fail();
        }
    }

    @Test
    void testRemovingOutOfBound() {
        try {
            li.remove(3);
            fail();
        } catch (DeletingNoneExistentItem e) {
            System.out.println("out of bound");
        }
    }

    @Test
    void testChangeStatus() {
        try {
            li.changeStatus(1);
            assertFalse(li.get(1).getIsDone());
            li.changeStatus(2);
            assertTrue(li.get(2).getIsDone());
            li.changeStatus(2);
            assertFalse(li.get(2).getIsDone());
        } catch (MarkingNoneExistentItem e) {
            fail();
        }
    }

    @Test
    void testMarkingOutOfBound() {
        try{
            li.changeStatus(4);
            fail();
        } catch (MarkingNoneExistentItem e) {
            System.out.println("out of bound");
        }
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
