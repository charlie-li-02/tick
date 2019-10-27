package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class HomeworkListTest {
    private HomeworkList homeworkList;
    private Map<Course, HashSet<Homework>> homeworkMap;
    private Course c1;
    private Course c2;
    private Homework hw1;
    private Homework hw2;
    private HashSet<Homework> homeworkSet;

    @BeforeEach
    void setup() {
        homeworkList = new HomeworkList();
        c1 = new Course("cpsc");
        c2 = new Course("math");
        hw1 = new Homework(c1, "lab", "oct 1st", false);
        hw2 = new Homework(c1, "quiz", "nov 1st", true);
        homeworkSet = new HashSet<>();
        homeworkSet.add(hw1);
        homeworkSet.add(hw2);
        homeworkMap = new HashMap<>();
        homeworkMap.put(c1, homeworkSet);
        homeworkList.setHomeWorkList(homeworkMap);
    }

    @Test
    void testGetHomework() {
        assertEquals(null, homeworkList.getHomework(c2));
        assertFalse(homeworkList.getHomework(c1).isEmpty());
        assertTrue(homeworkList.getHomework(c1).contains(hw1));
        assertTrue(homeworkList.getHomework(c1).contains(hw2));
    }

    @Test
    void testDelete() {
        assertEquals("Cannot find assignment matching that name, try again"
                , homeworkList.delete("test", homeworkSet));
        assertEquals("By removing lab you have 1 assignments left for cpsc"
                , homeworkList.delete("lab", homeworkSet));
        assertEquals("By removing quiz you have 0 assignments left for cpsc"
                , homeworkList.delete("quiz", homeworkSet));
    }

    @Test
    void testChangeStatus() {
        assertEquals("Cannot find assignment matching that name, try again"
                , homeworkList.changeStatus("test", homeworkSet));
        assertEquals("Now you have 2 assignments done for cpsc out of 2"
                , homeworkList.changeStatus("lab", homeworkSet));
        assertEquals("Now you have 1 assignments done for cpsc out of 2"
                , homeworkList.changeStatus("quiz", homeworkSet));
        assertEquals("Now you have 2 assignments done for cpsc out of 2"
                , homeworkList.changeStatus("quiz", homeworkSet));
        assertEquals("Now you have 1 assignments done for cpsc out of 2"
                , homeworkList.changeStatus("quiz", homeworkSet));
        assertEquals("Now you have 0 assignments done for cpsc out of 2"
                , homeworkList.changeStatus("lab", homeworkSet));
    }

    @Test
    void testGetHWDone() {
        assertEquals(1, homeworkList.getHWDone(homeworkSet));
    }

    @Test
    void testLoad() throws IOException {
        homeworkList.save();

        homeworkList.delete("lab", homeworkSet);
        homeworkList.delete("quiz", homeworkSet);

        homeworkList.load();

        assertEquals(2, homeworkList.homeWorkList.get(c1).size());

        PrintWriter fileClearer = new PrintWriter(homeworkList.getSavePath(), "UTF-8");
        fileClearer.close();
    }

    @Test
    void testSave() throws IOException {
        PrintWriter fileClearer = new PrintWriter(homeworkList.getSavePath(), "UTF-8");
        fileClearer.close();

        homeworkList.save();

        List<String> saved = Files.readAllLines(Paths.get(homeworkList.getSavePath()));

        assertEquals(2, saved.size());
        assertTrue(saved.contains("cpsc;lab;oct 1st;false"));
        assertTrue(saved.contains("cpsc;quiz;nov 1st;true"));

        PrintWriter fileClearer2 = new PrintWriter(homeworkList.getSavePath(), "UTF-8");
        fileClearer2.close();
    }

    @Test
    void testMerge() {
        assertEquals(2, homeworkList.merge(c1).size());
        assertTrue(homeworkList.merge(c1).contains("cpsc;lab;oct 1st;false"));
        assertTrue(homeworkList.merge(c1).contains("cpsc;quiz;nov 1st;true"));
    }

    @Test
    void testSplit() {
        ArrayList<String> test = HomeworkList.split("1;2;3;4");
        assertEquals(4,test.size());
        assertEquals("1", test.get(0));
        assertEquals("2", test.get(1));
        assertEquals("3", test.get(2));
        assertEquals("4", test.get(3));
    }

    @Test
    void testStringToBoolean() {
        String t = "true";
        String f = "false";

        assertTrue(HomeworkList.stringToBoolean(t));
        assertFalse(HomeworkList.stringToBoolean(f));
    }

    @Test
    void testBooleanToString() {
        assertEquals("true", HomeworkList.booleanToString(true));
        assertEquals("false", HomeworkList.booleanToString(false));
    }

    @Test
    void testSetHomeWorkList() {
        homeworkList.setHomeWorkList(homeworkMap);
        assertTrue(homeworkList.homeWorkList.keySet().contains(c1));
        assertFalse(homeworkList.homeWorkList.keySet().contains(c2));
    }
}
