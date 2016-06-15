package com.galvanize.recursiveIteration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by gschool on 6/14/16.
 */

public class RecursiveIteration {
    private int[] arr;
    public RecursiveIteration(int[] arr){
        this.arr = arr;
    }

//    **** #max() ****
    public Integer max(){
        if(this.arr.length == 0) return null;
        return max(0);
    }

    public Integer max(Integer index){
        if(index < this.arr.length - 1){
            int value = this.arr[index];
            int max = max(++index);
            return ( max >= value ) ? max : value;
        }else{
            return this.arr[index];
        }
    }

//    **** #min() ****
    public Integer min(){
        if(this.arr.length == 0) return null;
        return min(0);
    }

    public Integer min(Integer index) {
        if (index < this.arr.length - 1) {
            int value = this.arr[index];
            int min = min(++index);
            return (min < value) ? min : value;
        }else{
            return this.arr[index];
        }
    }

//    **** #filter() ****
    public List<Integer> filter(Predicate<Integer> fn){
        List<Integer> filtered = new ArrayList<Integer>();
        return filter(0, filtered, fn);
    }

    public List<Integer> filter(Integer index, List<Integer> filtered, Predicate<Integer> fn) {
        if (index < this.arr.length) {
            if(fn.test(this.arr[index])){
                filtered.add(this.arr[index]);
            }
            return filter(++index, filtered, fn);
        }else{
            return filtered;
        }
    }

//    **** #reject() ****
    public List<Integer> reject(Predicate<Integer> fn){
        return reject(0, new ArrayList<Integer>(), fn);
    }

    public List<Integer> reject(Integer index, List<Integer> rejected, Predicate<Integer> fn){
        if(index < this.arr.length) {
            int val = this.arr[index];
            if(!fn.test(val)) {
                rejected.add(val);
            }
            return reject(++index, rejected, fn);
        }else{
            return rejected;
        }
    }


//    **** #every() ****
    public Boolean every(Predicate<Integer> fn){
        if(this.arr.length == 0) return true;
        return every(0, fn);
    }

    public Boolean every(int index, Predicate<Integer> fn){
        if(index < this.arr.length){
            if(!fn.test(this.arr[index])) return false;
            return every(++index, fn);
        }else{
            return true;
        }
    }


//    **** #some() ****
    public Boolean some(Predicate<Integer> fn) {
        return some(0, fn);
    }

    public Boolean some(int index, Predicate<Integer> fn) {
        if(index < this.arr.length){
            return fn.test(this.arr[index]) || some(++index, fn);
        }
        return false;
    }

//    **** #none ****
    public Boolean none(Predicate<Integer> fn) {
        return none(0, fn);
    }

    public Boolean none(int index, Predicate<Integer> fn) {
        if(index < this.arr.length){
            return ( !fn.test(this.arr[index]) && none(++index, fn) );
        }
        return true;
    }

//    **** #map ****
    public List<Integer> map(Function<Integer, Integer> fn){
        return map(0, fn);
    }

    public LinkedList<Integer> map(int index, Function<Integer, Integer> fn) {
        if(index < this.arr.length){
            LinkedList<Integer> list = map((index + 1), fn);
            list.addFirst(fn.apply(this.arr[index]));
            return list;
        }
        return new LinkedList<>();
    }
}



