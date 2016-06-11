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

        int[] teams = this.teams;
        if(this.teams.length == 0) return new int[0][0][0];
        int[][][] toFill = new int[teams.length - 1][this.halfTeamsLength][2];
        int j = 0;
        int k = 0;
        for (int i = 0; i < toFill.length; i++){
            int[][] round = toFill[i];
            for(j = 0; j < round.length; j++){
                int[] pairing = round[j];
                pairing[0] = teams[j];
                pairing[1] = teams[teams.length - (1+j)];
            }
            teams = roundRobinShift(teams);
        }
        return toFill;
    }

    private int[] roundRobinShift(int[] teams){

        int[] shiftedTeams = new int[teams.length];
        shiftedTeams[0] = teams[0];
        shiftedTeams[1] = teams[teams.length - 1];
        for(int i = 2; i < teams.length; i++){
            shiftedTeams[i] = teams[i - 1];
        }
        return shiftedTeams;
    }
}
