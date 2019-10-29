package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomeworkTest {

    private Homework hw1;
    private Homework hw2;
    private Course c1;
    private Course c2;
    private static String hw1String = "lab is due on oct 1st, done? false";
    private static String hw2String = "ww is due on nov 1st, done? true";


    @BeforeEach
    void setup() {
        c1 = new Course("math");
        c2 = new Course("cpsc");
        hw1 = new Homework(c1, "lab", "oct 1st", false);
        hw2 = new Homework(c2, "ww", "nov 1st", true);
    }

    @Test
    void testGetCourse() {
        assertEquals(c1, hw1.getCourse());
        assertEquals(c2, hw2.getCourse());
    }

    @Test
    void testSetCourse() {
        hw1.setCourse(c2);
        assertEquals("cpsc", hw1.getCourse().toString());
    }

    @Test
    void testRemoveCourse() {
        hw1.removeCourse(c1);
        assertEquals(null, hw1.getCourse());
        hw2.removeCourse(c1);
        assertEquals(c2, hw2.getCourse());
    }

    @Test
    void testGetAssignment() {
        assertEquals("lab", hw1.getAssignment());
        assertEquals("ww", hw2.getAssignment());
    }

    @Test
    void testGetDueBy() {
        assertEquals("oct 1st", hw1.getDueBy());
        assertEquals("nov 1st", hw2.getDueBy());
    }

    @Test
    void testGetIsDone() {
        assertFalse(hw1.getIsDone());
        assertTrue(hw2.getIsDone());
    }

    @Test
    void testToString() {
        assertEquals(hw1String, hw1.toString());
        assertEquals(hw2String, hw2.toString());
    }

    @Test
    void testFlipStatus() {
        hw1.flipStatus();
        assertTrue(hw1.getIsDone());
        hw1.flipStatus();
        assertFalse(hw1.getIsDone());

        hw2.flipStatus();
        assertFalse(hw2.getIsDone());
        hw2.flipStatus();
        assertTrue(hw2.getIsDone());
    }

}
