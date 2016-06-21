package com.galvanize.mazeSolvability

/**
 * Created by gschool on 6/19/16.
 */
class MazeSolvabilityTest extends Specification {

    def setup(){

    }

    def "Create a tree when given a matrix maze"(){

        def maze1 = [["-", "-", "0"],
                     ["-", "-", "0"],
                     ["-", "-", "0"],
                     ["-", "-", "0"]] as String[][]

        def maze2 = [["0", "-", "-"],
                     ["0", "0", "-"],
                     ["-", "0", "0"],
                     ["-", "-", "0"]] as String[][]

        def maze3 = [["-", "0", "-"],
                     ["-", "0", "-"],
                     ["-", "0", "0"],
                     ["-", "-", "0"]] as String[][]

        def maze4 = [["-", "-", "0"],
                     ["-", "0", "0"],
                     ["0", "0", "-"],
                     ["0", "-", "-"]] as String[][]

        expect:
        new MazeSolvability(maze1).getStart().getForward().getForward().getForward().isEnd();
        ! new MazeSolvability(maze1).getStart().getForward().getForward().isEnd();
        new MazeSolvability(maze2).getStart().getForward().getRight().getForward().getRight().getForward().isEnd();
        new MazeSolvability(maze3).getStart().getForward().getForward().getRight().getForward().isEnd();
        new MazeSolvability(maze4).getStart().getForward().getLeft().getForward().getLeft().getForward().isEnd();

    }

    def "Should determine if a maze is solvable"(String[][] input, boolean result) {
        expect:
        new MazeSolvability(input).isSolvable() == result;

        where:
        input << [[["-", "-", "0"],
                   ["-", "-", "0"],
                   ["-", "-", "0"],
                   ["-", "-", "0"]],

                  [["0", "-", "-"],
                   ["0", "0", "-"],
                   ["-", "0", "0"],
                   ["-", "-", "0"]],

                  [["-", "-", "0"],
                   ["-", "0", "0"],
                   ["0", "0", "-"],
                   ["0", "-", "-"]],

                  [["-", "0", "-"],
                   ["-", "0", "-"],
                   ["-", "0", "0"],
                   ["-", "-", "-"]],

                  [["-", "0", "-"],
                   ["-", "0", "-"],
                   ["-", "0", "0"],
                   ["0", "-", "-"]],

                  [["-", "0", "-", "-", "-", "-"],
                   ["-", "0", "-", "0", "-", "0"],
                   ["-", "0", "-", "0", "0", "0"],
                   ["0", "0", "0", "0", "-", "0"],
                   ["-", "-", "0", "-", "0", "0"],
                   ["-", "-", "-", "-", "0", "-"]],

                  [["-", "0", "-", "-", "-", "-"],
                   ["-", "0", "-", "0", "-", "0"],
                   ["-", "0", "-", "0", "0", "0"],
                   ["0", "0", "0", "0", "-", "0"],
                   ["-", "-", "0", "-", "0", "0"],
                   ["0", "-", "-", "-", "-", "-"],
                   ["0", "-", "-", "-", "-", "-"]],

                  [['-', 'A', '-', '-', '-'],
                   ['-', 'B', '-', 'F', '-'],
                   ['-', 'C', 'D', 'E', '-'],
                   ['-', '-', '-', 'G', '-'],
                   ['-', 'K', 'J', 'H', '-'],
                   ['-', 'L', '-', 'I', '-'],
                   ['-', 'M', '-', '-', '-']],

                  [['-', 'A', '-', '-', '-'],
                   ['-', 'B', '-', 'F', '-'],
                   ['-', 'C', 'D', 'E', '-'],
                   ['-', '-', '-', 'G', '-'],
                   ['-', '-', 'J', 'H', '-'],
                   ['-', '-', '-', 'I', '-'],
                   ['-', '-', '-', '-', '-']]
        ]

        result << [true, true, true, false, false, true, false, true, false]
    }
}

import spock.lang.Specification
