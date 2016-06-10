package com.sethlytton.grading

import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser

import java.nio.file.Files
import java.nio.file.Paths

/**
 * Created by gschool on 6/9/16.
 */
class GradingTest extends groovy.util.GroovyTestCase {

    void testDifferences() {
        println new Grading().differences([6, 3, 5, 4, 3, 4, 4, 5])
        assertTrue "differences should return an array with up, down and even.",
                Arrays.equals( new Grading().differences([6, 3, 5, 4, 3, 4, 4, 5]), ["down", "up", "down", "down", "up", "even", "up"] as String[])
        assertTrue "differences returns an empty array when given an empty array",
                Arrays.equals(new Grading().differences([]), [] as String[]);
        assertTrue "differences returns an empty array when given an array with 1 element",
                Arrays.equals(new Grading().differences([8]), [] as String[]);
    }

    void testInDecline(){
        assertFalse "returns false for empty array: " + [],
                new Grading().inDecline([])
        assertFalse "returns false for an array with one element: " + [8],
                new Grading().inDecline([8])

        assertTrue "returns true if the last three grades are downs: " + [5,4,3,2],
                new Grading().inDecline([5,4,3,2])
        assertTrue "returns true if three downs separated by evens: " + [5,5,4,4,3,3,2,2],
                new Grading().inDecline([5,5,4,4,3,3,2,2])
        assertTrue "returns true if three downs separated by evens: " + [5,4,4,3,2,2],
                new Grading().inDecline([5,4,4,3,2,2])
        assertTrue "returns true if three downs separated by evens: " + [5,5,4,3,3,3,3,2,2,1],
                new Grading().inDecline([5,5,4,3,3,3,3,2,2,1])
        assertTrue "returns true if three downs separated by evens: " + [10,10,10,9,9,8,8,8,8,7],
                new Grading().inDecline([10,10,10,9,9,8,8,8,8,7])
        assertTrue "returns true if three downs separated by evens: " + [76,62,7,7,1],
                new Grading().inDecline([76,62,7,7,1])

        assertFalse "returns false if there are fewer than 3 downs: " + [5,4,3],
                new Grading().inDecline([5,4,3])
        assertFalse "returns false if there are fewer than 3 downs: " + [5,5,4,4,3,3],
                new Grading().inDecline([5,5,4,4,3,3])
        assertFalse "returns false if there are fewer than 3 downs: " + [5,5,4,4,5,3,2],
                new Grading().inDecline([5,5,4,4,5,3,2])
        assertFalse "returns false if there are fewer than 3 downs: " + [71,4,27,21,69,62,65,64,38,35,41,19],
                new Grading().inDecline([71,4,27,21,69,62,65,64,38,35,41,19])
    }

    void testNumInDecline(){
        JSONParser parser = new JSONParser();
        URL url = Thread.currentThread().getContextClassLoader().getResource("grades.json");
        String file = Files.readAllLines(Paths.get(url.getPath()));
        try {
            Object obj = parser.parse(file);
            obj = (JSONObject) obj
            println "hello"
            println new Grading().numInDecline((obj))
            assertTrue "returns an array with the number of people in decline and the number of people not in decline",
                    Arrays.equals( new Grading().numInDecline((obj)), [27, 73] as int[])
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
