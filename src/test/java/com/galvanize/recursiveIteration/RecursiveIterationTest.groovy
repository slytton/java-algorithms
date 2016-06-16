package com.galvanize.recursiveIteration

import spock.lang.Specification

import java.util.function.Function
import java.util.function.Predicate

/**
 * Created by gschool on 6/14/16.
 */
class recursiveIterationTest extends Specification {
    def "#max returns the maximum value of the array"(Integer[] arr, Integer maxValue) {
        expect:
        new RecursiveIteration(arr).max() == maxValue;

        where:
        arr               ||  maxValue
        [1]               ||  1
        [1,2,3]           ||  3
        [20,10,30]        ||  30
        [5,10,15,2,-1,4]  ||  15
        []                ||  null
    }

    def "#min returns the minimum value of the array"(Integer[] arr, Integer minValue) {
        expect:
        new RecursiveIteration(arr).min() == minValue;

        where:
        arr               ||  minValue
        [1]               ||  1
        [1,2,3]           ||  1
        [20,10,30]        ||  10
        [5,10,15,2,-1,4]  ||  -1
        []                ||  null
    }

    def "#filter returns an array that contains only matching items"(Integer[] input, List<Integer> result) {
        def fn = { x -> x >= 10 } as Predicate<Integer>;

        expect:
        new RecursiveIteration(input).filter(fn) == result

        where:
        input         ||  result
        [1,2,3]       ||  []
        [10, 20, 30]  ||  [10, 20, 30]
        [5, 10, 15]   ||  [10, 15]
        []            ||  []
    }

    def "#reject returns an array containing only the non-matching items"(Integer[] input, List<Integer> result) {
        def fn = { x -> x >= 10 } as Predicate<Integer>;

        expect:
        new RecursiveIteration(input).reject(fn) == result

        where:
        input         ||  result
        [1,2,3]       ||  [1,2,3]
        [10, 20, 30]  ||  []
        [5, 10, 15]   ||  [5]
        []            ||  []
    }

    def "#every returns true if every item in the array matches" (Integer[] input, Boolean result) {
        def fn = { x -> x >= 10 } as Predicate<Integer>

        expect:
        new RecursiveIteration(input).every(fn) == result;

        where:
        input         ||  result
        [1,2,3]       ||  false
        [10, 20, 30]  ||  true
        [5, 10, 15]   ||  false
        []            ||  true
    }

    def "#some returns true if at least one item in the array matches" (Integer[] input, Boolean result) {
        def fn = { x -> x >= 10 } as Predicate<Integer>

        expect:
        new RecursiveIteration(input).some(fn) == result;

        where:
        input         ||  result
        [1,2,3]       ||  false
        [10, 20, 30]  ||  true
        [5, 10, 15]   ||  true
        []            ||  false
    }

    def "#none returns true if 0 items in the array match"(Integer[] input, Boolean result) {
        def fn = { x -> x >= 10 } as Predicate<Integer>

        expect:
        new RecursiveIteration(input).none(fn) == result;

        where:
        input         ||  result
        [1,2,3]       ||  true
        [10, 20, 30]  ||  false
        [5, 10, 15]   ||  false
        []            ||  true
    }

    def "#map returns an array containing elements transformed by the function" (Integer[] input, List<Integer> result) {
        def fn = { x -> x + 1 } as Function<Integer, Integer>;

        expect:
        new RecursiveIteration(input).map(fn) == result

        where:
        input             ||  result
        [1]               ||  [2]
        [1,2,3]           ||  [2,3,4]
        [20,10,30]        ||  [21,11,31]
        [5,10,15,2,-1,4]  ||  [6,11,16,3,0,5]
        []                ||  []
    }

    def "#join returns a string with the elements of the array joined by the delimiter when called with strings" (String[] input, String delim, result) {
        expect:
        new RecursiveIteration(input).join(delim) == result

        where:
        input            |  delim  ||  result
        []               |  "."    ||  ""
        ["a"]            |  "."    ||  "a"
        ["a", "b"]       |  ","    ||  "a,b"
        ["a", "b", "c"]  |  "|"    ||  "a|b|c"
    }

    def "#join returns a string with the elements of the arry joined by the delimeter when called with integers" (Integer[] input, String delim, result) {
        expect:
        new RecursiveIteration(input).join(delim) == result

        where:
        input    |  delim  ||  result
        []       |  ","    ||  ""
        [1]      |  ","    ||  "1"
        [1,2]    |  ","    ||  "1,2"
        [1,2,3]  |  "|"    ||  "1|2|3"
    }

    def "#split returns an array of strings split on the delimiter" (String input, String delim, ArrayList<String> result) {
        expect:
        RecursiveIteration.split(input, delim).equals(result);

        where:
        input  |  delim  ||  result
        ""     |  "."    ||  []
        "a"    |  "."    ||  ["a"]
        "a.b"  |  "."    ||  ["a", "b"]
    }

    def "#reduce returns the reduced value" (Integer[] input, Integer start, Integer result){
        def fn = {previous, current -> previous + current} as Reducable<Integer, Integer>

        expect:
        new RecursiveIteration(input).reduce(fn, start) == result

        where:
        input       |  start  ||  result
        []          |  5     ||  5
        [1]         |  5     ||  6
        [1,2]       |  5     ||  8
        [1,2,4]     |  5     ||  12
        [1,2,4,10]  |  5     ||  22
    }
}

//    describe("reduce", () => {
//        it("returns the reduced value", () => {
//            const fn = (previous, current) => previous + current;
//
//            expect(lib.reduce([], fn, 5)).to.deep.equal(5)
//            expect(lib.reduce([1], fn, 5)).to.deep.equal(6)
//            expect(lib.reduce([1,2], fn, 5)).to.deep.equal(8)
//            expect(lib.reduce([1,2,4], fn, 5)).to.deep.equal(12)
//            expect(lib.reduce([1,2,4,10], fn, 5)).to.deep.equal(22)
//        })
//
//        it("does not use loops", checkForLoops('reduce'))
//    })
//
//    describe("indexOf", () => {
//        it("returns the index of the given value", () => {
//            expect(lib.indexOf([1,2,3], 1)).to.equal(0)
//            expect(lib.indexOf([1,2,3], 2)).to.equal(1)
//            expect(lib.indexOf([1,2,3], 3)).to.equal(2)
//            expect(lib.indexOf([1,2,3], 4)).to.equal(-1)
//        })
//
//        it("does not use loops", checkForLoops('indexOf'))
//    })
//
//    describe("leftPad", () => {
//        it("returns a string padded by the given delimiter, the given number of times", () => {
//            expect(lib.leftPad('',      5, '-')).to.eq("-----")
//            expect(lib.leftPad('a',     4, '|')).to.eq("|||a")
//            expect(lib.leftPad('ab',    4, '|')).to.eq("||ab")
//            expect(lib.leftPad('abc',   4, '|')).to.eq("|abc")
//            expect(lib.leftPad('abcd',  4, '|')).to.eq("abcd")
//            expect(lib.leftPad('abcde', 4, '|')).to.eq("abcde")
//        })
//
//        it("does not use loops", checkForLoops('leftPad'))
//    })
//
//    describe("flatten", () => {
//        it("returns a single-dimensional array of all of the values", () => {
//            expect(lib.flatten([])).to.deep.equal([])
//            expect(lib.flatten([1])).to.deep.equal([1])
//            expect(lib.flatten([[1]])).to.deep.equal([1])
//            expect(lib.flatten([[[1]]])).to.deep.equal([1])
//            expect(lib.flatten([[[[1]]]])).to.deep.equal([1])
//            expect(lib.flatten([1, [2], [3,4], [ 5, [6, [7]]]])).to.deep.equal([1, 2, 3, 4, 5, 6, 7])
//        })
//
//        it("does not use loops", checkForLoops('flatten'))
//    })
//})
