package com.galvanize.grading;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import java.util.Iterator;
import java.util.List;


/**
 * Created by gschool on 6/8/16.
 */

public class Grades {

    final static Logger logger = Logger.getLogger(Grades.class);
    private Number[] grades;

    public Grades(Number[] grades){
        this.grades = grades;
    }

    public String[] differences() {
        Number[] grades = this.grades;

        if (grades.length <= 1) return new String[0];

        String[] results = new String[grades.length - 1];

        int previousGrade = grades[0].intValue();

        String result;

        for (int i = 1; i < grades.length; i++) {
            int grade = grades[i].intValue();
            result = "";
            if (previousGrade < grade) result = "up";
            if (previousGrade == grade) result = "even";
            if (previousGrade > grade) result = "down";
            results[i - 1] = result;
            previousGrade = grade;
        }
        return results;
    }

    public boolean inDecline(){
        Number[] grades = this.grades;
        int declineCount = 0;
        if(grades.length <= 1) return false;

        int previousGrade = grades[0].intValue();

        for (int i = 1; i < grades.length; i++){
            int grade = grades[i].intValue();
            if(previousGrade < grade) declineCount = 0;
            if(previousGrade > grade) declineCount++;
            previousGrade = grade;
        }
        return declineCount >= 3;
    }

    public int[] numInDecline(JSONObject data) {
        Iterator<List<Number>> iterator  = data.values().iterator();
        int[] results = {0,0};

        while(iterator.hasNext()) {
            Number[] grades = iterator.next().toArray(new Number[0]);
            Boolean inDecline = new Grades(grades).inDecline();
            if (inDecline) {
                results[0]++;
            } else {
                results[1]++;
            }
        }
        return results;
    }
}
