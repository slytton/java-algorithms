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
        input   ||  result
        ""      ||  []
        "a"     ||  ["a"]
        "ab"    ||  ["ab", "ba"]
        "abc"   ||  ["abc", "acb", "bac", "bca", "cab", "cba"]
        "abc"   ||  ["abc", "acb", "bac", "bca", "cab", "cba"]
        "abcd"  ||  ["abcd", "abdc", "acbd", "acdb", "adbc", "adcb", "bacd", "badc", "bcad", "bcda", "bdac", "bdca", "cabd", "cadb", "cbad", "cbda", "cdab", "cdba", "dabc", "dacb", "dbac", "dbca", "dcab", "dcba"]

    }
}
