package com.galvanize.roundRobbin
import spock.lang.*

/**
 * Created by gschool on 6/11/16.
 */
class RoundRobinTest extends Specification {
    def "#generateCompetition computing the competitions between a list of teams" (int[] teamList, int[][][] competitions){
        expect:
        Arrays.deepEquals(new RoundRobin(teamList).generateCompetition(), competitions);


        where:

        teamList           || competitions
        [] as int[]        || [] as int[][][]
        [1,2,3,4,5,6]      || [[[1,6],[2,5],[3,4]],
                               [[1,5],[6,4],[2,3]],
                               [[1,4],[5,3],[6,2]],
                               [[1,3],[4,2],[5,6]],
                               [[1,2],[3,6],[4,5]]]
        [1,2,3,4,5,6,7,8]  || [[[1,8],[2,7],[3,6],[4,5]],
                               [[1,7],[8,6],[2,5],[3,4]],
                               [[1,6],[7,5],[8,4],[2,3]],
                               [[1,5],[6,4],[7,3],[8,2]],
                               [[1,4],[5,3],[6,2],[7,8]],
                               [[1,3],[4,2],[5,8],[6,7]],
                               [[1,2],[3,8],[4,7],[5,6]]]
    }

}
