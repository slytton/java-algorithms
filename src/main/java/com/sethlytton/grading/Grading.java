package com.sethlytton.grading;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import java.util.Iterator;
import java.util.List;


/**
 * Created by gschool on 6/8/16.
 */

public class Grading {

    final static Logger logger = Logger.getLogger(Grading.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();
    }

    public String[] differences(List<Number> grades) {

        if (grades.size() <= 1) return new String[0];

        String[] results = new String[grades.size() - 1];

        Integer previousGrade = grades.get(0).intValue();

        String result;

        for (int i = 1; i < grades.size(); i++) {
            Integer grade = grades.get(i).intValue();
            result = "";
            if (previousGrade < grade) result = "up";
            if (previousGrade == grade) result = "even";
            if (previousGrade > grade) result = "down";
            results[i - 1] = result;
            previousGrade = grade;
        }
        return results;
    }

    public boolean inDecline(List <Number> grades){
        int declineCount = 0;
        if(grades.size() <= 1) return false;

        int previousGrade = grades.get(0).intValue();

        for (int i = 1; i < grades.size(); i++){
            int grade = grades.get(i).intValue();
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
            Boolean inDecline = new Grading().inDecline(iterator.next());
            if (inDecline) {
                results[0]++;
            } else {
                results[1]++;
            }
        }
        return results;
    }
}
