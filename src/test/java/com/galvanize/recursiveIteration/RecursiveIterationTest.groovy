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

    def "#filter returns an array that contains only matching items"(int[] input, List<Integer> result) {
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

    def "#reject returns an array containing only the non-matching items"(int[] input, List<Integer> result) {
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

    def "#every returns true if every item in the array matches" (int[] input, Boolean result) {
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

    def "#some returns true if at least one item in the array matches" (int[] input, Boolean result) {
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

    def "#none returns true if 0 items in the array match"(int[] input, Boolean result) {
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

    def "#map returns an array containing elements transformed by the function" (int[] input, List<Integer> result) {
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
}



//    describe("map", () => {
//        it("returns an array containing elements transformed by the function", () => {
//            const fn = (x) => x + 1;
//
//            expect(lib.map([1], fn)).to.deep.equal([2])
//            expect(lib.map([1,2,3], fn)).to.deep.equal([2,3,4])
//            expect(lib.map([20, 10 ,30], fn)).to.deep.equal([21,11,31])
//            expect(lib.map([5, 10, 15, 2, -1, 4], fn)).to.deep.equal([6,11,16,3,0,5])
//            expect(lib.map([], fn)).to.deep.equal([])
//        })
//
//        it("does not use loops", checkForLoops('map'))
//    })
//
//    describe("join", () => {
//        it("returns a string with the elements of the array joined by the delimiter", () => {
//            expect(lib.join([], ",")).to.eq("")
//            expect(lib.join(["a"], ",")).to.eq("a")
//            expect(lib.join(["a", "b"], ",")).to.eq("a,b")
//            expect(lib.join(["a", "b", "c"], "|")).to.eq("a|b|c")
//            expect(lib.join([1,2,3], "|")).to.eq("1|2|3")
//        })
//
//        it("does not use loops", checkForLoops('join'))
//    })
//
//    describe("split", () => {
//        it("returns an array of strings split on the delimiter", () => {
//            expect(lib.split("", '.')).to.deep.equal([])
//            expect(lib.split("a", '.')).to.deep.equal(['a'])
//            expect(lib.split("a.b", '.')).to.deep.equal(['a', 'b'])
//        })
//
//        it("does not use loops", checkForLoops('split'))
//    })
//
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
