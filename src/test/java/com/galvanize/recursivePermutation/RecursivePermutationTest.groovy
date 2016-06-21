package com.galvanize.recursivePermutation
import spock.lang.Specification
/**
 * Created by gschool on 6/21/16.
 */
class RecursivePermutationTest extends Specification {

    def "#getPermutations returns all permutations for a given string" (String input, ArrayList<String> result) {
        expect:
        new RecursivePermutation(input).getPermutations().equals(result)

        where:
        input  ||  result
        ""     ||  []
        "a"    ||  ["a"]
        "ab"   ||  ["ab", "ba"]
        "abc"  ||  ["abc", "acb", "bac", "bca", "cab", "cba"]
    }
}
