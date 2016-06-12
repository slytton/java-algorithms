package com.galvanize.roundRobbin;

import org.apache.log4j.Logger;

/**
 * Created by gschool on 6/10/16.
 */
public class RoundRobin {

    final static Logger logger = Logger.getLogger(RoundRobin.class);
    private int[] teams;
    private int halfTeamsLength;

    public RoundRobin(int[] teams){
        this.teams = teams;
        this.halfTeamsLength = (int)Math.ceil(this.teams.length / 2);
    }

    public int[][][] generateCompetition(){
        if(this.teams.length == 0) return new int[0][0][0];
        return fillCompetition(this.teams, new int[this.teams.length - 1][this.halfTeamsLength][2], 0);
    }

    private int[][][] fillCompetition(int[] teams, int[][][] toFill, int index) {
        fillRound(teams, toFill[index]);
        return (index < toFill.length - 1) ? fillCompetition(roundRobinShift(teams), toFill, ++index) : toFill;
    }

    private void fillRound(int[] teams, int[][]round){
        createPairs(teams, round, 0);
    }

    private void createPairs(int[] teams, int[][]round, int index) {
        round[index][0] = teams[index];
        round[index][1] = teams[teams.length - (1+index)];
        if(index < round.length - 1) createPairs(teams, round, ++index);
    }

    private int[] roundRobinShift( int[] teams){
        int[] result  = new int[teams.length];
        result[0] = teams[0];
        result[1] = teams[teams.length - 1];
        shift(teams, 2, result);
        return result;
    }

    private int[] shift(int[] teams, int index, int[] result){
        result[index] = teams[index - 1];
        if(index + 1 < teams.length) shift(teams, ++index, result);
        return result;
    }
}
