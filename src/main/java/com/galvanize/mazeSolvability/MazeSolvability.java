package com.galvanize.mazeSolvability;

/**
 * Created by gschool on 6/19/16.
 */
public class MazeSolvability {
    private MazeNode start;

    MazeSolvability(String[][] mazeMatrix){
        MazeNode[] previousRowNodes = null;
        MazeNode[] currentRowNodes;
        for (int depth = 0; depth < mazeMatrix.length; depth++) {
            String[] row = mazeMatrix[depth];
            currentRowNodes = new MazeNode[mazeMatrix[0].length];
            for (int j = 0; j < row.length; j++) {


                if(row[j].equals("-")) continue;

                if(previousRowNodes == null){
                    this.start = new MazeNode(null, null, null, null, false);
                    currentRowNodes[j] = this.start;
                    continue;
                }

                currentRowNodes[j] = new MazeNode(null, null, null, null, (depth == mazeMatrix.length - 1));

                // Look left
                if(j > 0 && currentRowNodes[j - 1] != null){
                    currentRowNodes[j].setLeft(currentRowNodes[j - 1]);
                    currentRowNodes[j - 1].setRight(currentRowNodes[j]);
                }

                // Look back
                if (depth > 0 && previousRowNodes[j] != null) {
                    currentRowNodes[j].setBack(previousRowNodes[j]);
                    previousRowNodes[j].setForward(currentRowNodes[j]);
                }
            }
            previousRowNodes = currentRowNodes;
        }
    }

    public boolean isSolvable(){
        return isSolvable(this.start);
    }

    private boolean isSolvable(MazeNode current) {

        if (current == null) return false;
        current.setVisited();
        if(current.isEnd()) return true;


        MazeNode left = current.getLeft();
        MazeNode right = current.getRight();
        MazeNode forward = current.getForward();
        MazeNode back = current.getBack();


        if(left != null && !left.isVisited()) {
            if(isSolvable(left)) return true;
        }

        if(right != null && !right.isVisited()) {
            if(isSolvable(right)) return true;
        }

        if(forward != null && !forward.isVisited()) {
            if(isSolvable(forward)) return true;
        }

        if(back != null && !back.isVisited()) {
            if(isSolvable(back)) return true;
        }

        return false;
    }

    public MazeNode getStart() {
        return this.start;
    }
}
