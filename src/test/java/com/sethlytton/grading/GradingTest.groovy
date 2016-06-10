package com.sethlytton.grading

/**
 * Created by gschool on 6/9/16.
 */
class GradingTest extends groovy.util.GroovyTestCase {
//    void setUp() {
//        super.setUp()
//
//    }
//
//    void tearDown() {
//
//    }
//
//    void testMain() {
//
//    }

    void testDifferences() {
        assertTrue "differences should return an array with up, down and even.",
                Grading.differences([6, 3, 5, 4, 3, 4, 4, 5]).equals(["down", "up", "down", "down", "up", "even", "up"])
        assertTrue "differences returns an empty array when given an empty array",
                Grading.differences([]).equals([]);
        assertTrue "differences returns an empty array when given an array with 1 element",
                Grading.differences([8]).equals([]);
    }

    void testInDecline(){
        assertFalse "returns false for empty array",
                Grading.inDecline([])
        assertFalse "returns false for an array with one element",
                Grading.inDecline([8])
        assertTrue "returns true if the last three grades are downs.",
                Grading.inDecline([5,4,3,2])
        assertTrue "returns true if three downs separated by evens",
                Grading.inDecline([5,5,4,4,3,3,2,2])
        assertTrue "returns true if three downs separated by evens",
                Grading.inDecline([5,4,4,3,2,2])
        assertTrue "returns true if three downs separated by evens",
                Grading.inDecline([5,5,4,3,3,3,3,2,2,1])
        assertTrue "returns true if three downs separated by evens",
                Grading.inDecline([10,10,10,9,9,8,8,8,8,7])
        assertTrue "returns true if three downs separated by evens",
                Grading.inDecline([76,62,7,7,1])

        assertFalse "returns false if there are fewer than 3 downs",
                Grading.inDecline([5,4,3])
        assertFalse "returns false if there are fewer than 3 downs",
                Grading.inDecline([5,5,4,4,3,3])
        assertFalse "returns false if there are fewer than 3 downs",
                Grading.inDecline([5,5,4,4,5,3,2])
        assertFalse "returns false if there are fewer than 3 downs",
                Grading.inDecline([ 71, 4, 27, 21, 69, 62, 65, 64, 38, 35, 41, 19 ])
    }

}
