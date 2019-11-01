package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseTest {

    private Course course;
    private HomeworkItem hw1;
    private HomeworkItem hw2;
    private Map<Course, HashSet<HomeworkItem>> homeworkMap;
    private HashSet<HomeworkItem> homeworkSet;

    @BeforeEach
    void setup() {
        course = new Course("cpsc");
        hw1 = new HomeworkItem(course, "lab", "oct 1st", false);
        hw2 = new HomeworkItem(course, "quiz", "nov 1st", true);
        homeworkSet = new HashSet<>();
        homeworkSet.add(hw1);
        homeworkSet.add(hw2);
        homeworkMap = new HashMap<>();
        homeworkMap.put(course, homeworkSet);
    }

    @Test
    void testGetCourseName() {
        assertEquals("cpsc", course.getCourseName());
    }

    @Test
    void testGetSetOfHomework() {
        HashSet<HomeworkItem> test = course.getSetOfHomework(homeworkMap);
        assertEquals(2, test.size());
        assertTrue(test.contains(hw1));
        assertTrue(test.contains(hw2));

        Course newCourse = new Course("math");
        HomeworkItem newHW = new HomeworkItem(newCourse, "new", "new", false);

        assertFalse(test.contains(newHW));
        assertEquals(null, newCourse.getSetOfHomework(homeworkMap));
    }

    @Test
    void testAddHomework() {
        HomeworkItem hw3 = new HomeworkItem(course, "test","dec 15th", false);
        course.getSetOfHomework(homeworkMap);
        course.addHomework(hw3);
        assertEquals(3, course.getSetOfHomework().size());
        assertEquals(course, hw3.getCourse());
    }

    @Test
    void testRemoveHomework() {
        course.getSetOfHomework(homeworkMap);
        course.removeHomework(hw1);
        assertFalse(course.getSetOfHomework().contains(hw1));
        assertEquals(1, course.getSetOfHomework().size());
    }

    @Test
    void testToString() {
        assertEquals("cpsc", course.toString());
    }

    @Test
    void testEquals() {
        assertTrue(course.equals(course));

        Course newCourse = new Course("not the same");
        assertFalse(course.equals(newCourse));

        String notCourse = "cpsc";
        assertFalse(course.equals(notCourse));
    }
}
