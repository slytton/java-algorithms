package com.galvanize.recursivePermutation;

import java.util.ArrayList;

/**
 * Created by gschool on 6/21/16.
 */
public class RecursivePermutation {
    public String string;
    public RecursivePermutation(String input) {
        this.string = input;
    }

    public ArrayList<String> getPermutations(){
        return getPermutations("", this.string);
    }

    private ArrayList<String> getPermutations(String current, String toComplete){
        ArrayList<String> toReturn = new ArrayList<String>();

        if(toComplete.equals("") && !current.equals("")) toReturn.add(current);

        for(int i = 0; i < toComplete.length(); i++){
            String nextCurrent = current + toComplete.substring(i, i + 1);
            String nextToComplete = toComplete.substring(0, i) + toComplete.substring(i + 1);
            toReturn.addAll(getPermutations(nextCurrent, nextToComplete));
        }
        return toReturn;
    }
}
