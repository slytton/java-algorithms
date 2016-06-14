package com.galvanize.grading

import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser

import java.nio.file.Files
import java.nio.file.Paths

/**
 * Created by gschool on 6/9/16.
 */
class GradesTest extends GroovyTestCase {

    void testDifferences() {
        assertTrue "differences should return an array with up, down and even.",
                Arrays.equals( new Grades([6, 3, 5, 4, 3, 4, 4, 5] as Number[]).differences(), ["down", "up", "down", "down", "up", "even", "up"] as String[])
        assertTrue "differences returns an empty array when given an empty array",
                Arrays.equals(new Grades([] as Number[]).differences(), [] as String[]);
        assertTrue "differences returns an empty array when given an array with 1 element",
                Arrays.equals(new Grades([8] as Number[]).differences(), [] as String[]);
    }

    void testInDecline(){
        assertFalse "returns false for empty array: " + [],
                new Grades([] as Number[]).inDecline()
        assertFalse "returns false for an array with one element: " + [8],
                new Grades([8] as Number[]).inDecline()

        assertTrue "returns true if the last three grades are downs: " + [5,4,3,2],
                new Grades([5,4,3,2] as Number[]).inDecline()
        assertTrue "returns true if three downs separated by evens: " + [5,5,4,4,3,3,2,2],
                new Grades([5,5,4,4,3,3,2,2] as Number[]).inDecline()
        assertTrue "returns true if three downs separated by evens: " + [5,4,4,3,2,2],
                new Grades([5,4,4,3,2,2] as Number[]).inDecline()
        assertTrue "returns true if three downs separated by evens: " + [5,5,4,3,3,3,3,2,2,1],
                new Grades([5,5,4,3,3,3,3,2,2,1] as Number[]).inDecline()
        assertTrue "returns true if three downs separated by evens: " + [10,10,10,9,9,8,8,8,8,7],
                new Grades([10,10,10,9,9,8,8,8,8,7] as Number[]).inDecline()
        assertTrue "returns true if three downs separated by evens: " + [76,62,7,7,1],
                new Grades([76,62,7,7,1] as Number[]).inDecline()

        assertFalse "returns false if there are fewer than 3 downs: " + [5,4,3],
                new Grades([5,4,3] as Number[]).inDecline()
        assertFalse "returns false if there are fewer than 3 downs: " + [5,5,4,4,3,3],
                new Grades([5,5,4,4,3,3] as Number[]).inDecline()
        assertFalse "returns false if there are fewer than 3 downs: " + [5,5,4,4,5,3,2],
                new Grades([5,5,4,4,5,3,2] as Number[]).inDecline()
        assertFalse "returns false if there are fewer than 3 downs: " + [71,4,27,21,69,62,65,64,38,35,41,19],
                new Grades([71,4,27,21,69,62,65,64,38,35,41,19] as Number[]).inDecline()
    }

    void testNumInDecline(){
        JSONParser parser = new JSONParser();
        URL url = Thread.currentThread().getContextClassLoader().getResource("grades.json");
        println url;
        String file = Files.readAllLines(Paths.get(url.getPath()));
        try {
            Object obj = parser.parse(file);
            obj = (JSONObject) obj
            assertTrue "returns an array with the number of people in decline and the number of people not in decline",
                    Arrays.equals( new Grades().numInDecline((obj)), [27, 73] as int[])
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
