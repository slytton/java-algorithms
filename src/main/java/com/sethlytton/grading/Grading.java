package com.sethlytton.grading;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by gschool on 6/8/16.
 */

public class Grading {

    final static Logger logger = Logger.getLogger(Grading.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();
        ArrayList<Integer> grades =  new ArrayList<Integer>(Arrays.asList(6, 3, 5, 4, 3, 4, 4, 5));
    }

    public static ArrayList<String> differences(ArrayList<Integer> grades) {

        ArrayList<String> results = new ArrayList<>();

        if(grades.size() <= 1) return results;

        int previousGrade = grades.get(0);

        String result;

        for (int i = 1; i < grades.size(); i++){
            Integer grade = grades.get(i);
            result = "";
            if(previousGrade < grade) result = "up";
            if(previousGrade == grade) result = "even";
            if(previousGrade > grade) result = "down";
            results.add(result);
            previousGrade = grade;
        }
        return results;
    }

    public static boolean inDecline(ArrayList<Integer> grades){
        int declineCount = 0;
        if(grades.size() <= 1) return false;

        int previousGrade = grades.get(0);

        for (int i = 1; i < grades.size(); i++){
            int grade = grades.get(i);
            if(previousGrade < grade) declineCount = 0;
            if(previousGrade > grade) declineCount++;
            previousGrade = grade;
        }
        return declineCount >= 3;
    }
}
