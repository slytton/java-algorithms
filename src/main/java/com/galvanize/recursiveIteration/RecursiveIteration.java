package com.galvanize.recursiveIteration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by gschool on 6/14/16.
 */

@FunctionalInterface
interface  Reducable<Prev, Current>{
    public Prev apply(Prev prev, Current current);
}

public class RecursiveIteration <T extends Comparable<T> & Appendable> {
    private T[] arr;
    public RecursiveIteration(T[] arr){
        this.arr = arr;
    }

//    **** #max() ****
    public T max(){
        if(this.arr.length == 0) return null;
        return max(0);
    }

    public T max(Integer index){
        if(index < this.arr.length - 1){
            T value = this.arr[index];
            T max = max(++index);
            return ( max.compareTo(value) >= 0 ) ? max : value;
        }else{
            return this.arr[index];
        }
    }

//    **** #min() ****
    public T min(){
        if(this.arr.length == 0) return null;
        return min(0);
    }

    public T min(Integer index) {
        if (index < this.arr.length - 1) {
            T value = this.arr[index];
            T min = min(++index);
            return (min.compareTo(value) < 0) ? min : value;
        }else{
            return this.arr[index];
        }
    }

//    **** #filter() ****
    public List<T> filter(Predicate<T> fn){
        List<T> filtered = new ArrayList<T>();
        return filter(0, filtered, fn);
    }

    public List<T> filter(Integer index, List<T> filtered, Predicate<T> fn) {
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
    public List<T> reject(Predicate<T> fn){
        return reject(0, new ArrayList<>(), fn);
    }

    public List<T> reject(Integer index, List<T> rejected, Predicate<T> fn){
        if(index < this.arr.length) {
            T val = this.arr[index];
            if(!fn.test(val)) {
                rejected.add(val);
            }
            return reject(++index, rejected, fn);
        }else{
            return rejected;
        }
    }


//    **** #every() ****
    public Boolean every(Predicate<T> fn){
        if(this.arr.length == 0) return true;
        return every(0, fn);
    }

    public Boolean every(int index, Predicate<T> fn){
        if(index < this.arr.length){
            if(!fn.test(this.arr[index])) return false;
            return every(++index, fn);
        }else{
            return true;
        }
    }


//    **** #some() ****
    public Boolean some(Predicate<T> fn) {
        return some(0, fn);
    }

    public Boolean some(int index, Predicate<T> fn) {
        if(index < this.arr.length){
            return fn.test(this.arr[index]) || some(++index, fn);
        }
        return false;
    }


//    **** #none ****
    public Boolean none(Predicate<T> fn) {
        return none(0, fn);
    }

    public Boolean none(int index, Predicate<T> fn) {
        if(index < this.arr.length){
            return ( !fn.test(this.arr[index]) && none(++index, fn) );
        }
        return true;
    }


//    **** #map ****
    public ArrayList<T> map(Function<T, T> fn){
        return map(0, new ArrayList<T>(), fn);
    }

    public ArrayList<T> map(int index, ArrayList<T> list, Function<T, T> fn) {
        if(index < this.arr.length){
            list.add(fn.apply(this.arr[index]));
            list = map((index + 1), list, fn);
            return list;
        }
        return list;
    }


//    **** #join ***
    public String join(String delim) {
        if(this.arr.length == 0) return "";
        return join(0, delim);
    }

    private String join(int index, String delim) {
        if(index < this.arr.length - 1){
            return this.arr[index].toString() + delim + join(++index, delim);
        }
        return this.arr[index].toString();
    }

//    **** #split ****
    public static ArrayList<String> split(String toSplit, String delim){
        return split(0, new ArrayList<>(), 0, toSplit, delim);
    }

    private static ArrayList<String> split(int index, ArrayList<String> list, int arrIndex, String toSplit, String delim){
        if (index < toSplit.length()){
            String character  = "" + toSplit.charAt(index);
            if (!character.equals(delim)){
                try {
                    list.add(character + list.get(arrIndex));
                } catch (Exception e){
                    list.add(character);
                }
            }else{
                arrIndex++;
            }
            split(++index, list, arrIndex, toSplit, delim);
        }
        return list;
    }

//    **** #reduce ****
    public T reduce(Reducable<T, T> fn, T start) {
        return reduce(0, fn , start);
    }

    private T reduce(int index, Reducable<T, T> fn, T start){
        if(index < this.arr.length){
            start = reduce(index + 1, fn, fn.apply(start, this.arr[index]));
        }

        return start;
    }

//    **** #indexOf ****
    public int indexOf(T item) {
        return indexOf(0, item);
    }

    private int indexOf(int index, T item) {
        if(index < this.arr.length) return this.arr[index] == item ? index : indexOf(index + 1, item);
        return -1;
    }


//    **** #leftPad ****
    public static String leftPad(String input, int times, String delim){
        return leftPad(0, input, times, delim);
    }

    public static String leftPad(int index, String input, int times, String delim){
        return (index < (times - input.length())) ? delim + leftPad(index + 1, input, times, delim) : input;
    }

//    **** Flatten ****
    public static ArrayList<Integer> flatten(Object array){
        if(array.getClass().getName().equals("java.lang.Integer")){
            ArrayList<Integer> list = new ArrayList<>();
            list.add(((Integer)array));
            return list;
        } else {
            ArrayList<Integer> result = new ArrayList<>();
            ArrayList<Integer> castArray = ((ArrayList<Integer>)array);
            for(int i = 0; i < castArray.size(); i++) {
                result.addAll(flatten(castArray.get(i)));
            }
            return result;
        }
    }
}