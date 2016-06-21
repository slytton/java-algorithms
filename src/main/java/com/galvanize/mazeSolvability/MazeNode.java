package com.galvanize.mazeSolvability;

/**
 * Created by gschool on 6/19/16.
 */
public class MazeNode {
    private MazeNode left;
    private MazeNode right;
    private MazeNode forward;
    private MazeNode back;
    private boolean visited;
    private boolean end;

    MazeNode(MazeNode left, MazeNode right, MazeNode forward, MazeNode back, boolean end){
        this.left = left;
        this.right = right;
        this.forward = forward;
        this.back = back;
        this.visited = false;
        this.end = end;
    }

    public String toString() {
        String leftString = (this.left == null) ? null : "{ node }";
        String rightString = (this.right == null) ? null : "{ node }";
        String forwardString = (this.forward == null) ? null : "{ node }";
        String backString = (this.back == null) ? null : "{ node }";

        return  "{ left: " + leftString + " ," +
                " right: " + rightString + "," +
                " forward: " + forwardString + "," +
                " back: " + backString + "," +
                " visited: " + this.visited + "," +
                " end: " + this.end + "  }";
    }

    public MazeNode getLeft() {
        return left;
    }

    public void setLeft(MazeNode left) {
        this.left = left;
    }

    public MazeNode getRight() {
        return right;
    }

    public void setRight(MazeNode right) {
        this.right = right;
    }

    public MazeNode getForward() {
        return forward;
    }

    public void setForward(MazeNode forward) {
        this.forward = forward;
    }

    public MazeNode getBack() {
        return back;
    }

    public void setBack(MazeNode back) {
        this.back = back;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited() {
        this.visited = true;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}
