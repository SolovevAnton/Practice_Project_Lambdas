package com.solovev;

import com.solovev.util.BernoulliGenerator;
import com.solovev.util.Filter;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Main {

    /**
     * Method to filter array based on the given Filter realisation
     *
     * @param arr    arr to filter
     * @param filter Filter interface realisation to use
     * @return filtered array, or empty if nothing matched filter
     */
    public static <T> T[] filter(T[] arr, Filter<T> filter) {
        List<T> filtered = new ArrayList<>();
        for (T elem : arr) {
            if (filter.apply(elem)) {
                filtered.add(elem);
            }
        }
        return filtered.toArray((T[]) Array.newInstance(arr.getClass().getComponentType(), filtered.size()));
    }

    /**
     * Method prints array of given type
     *
     * @param arr array to print
     */
    public static <T> void print(T[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        Integer[] arrInt = {-1, -2, -3, -4, -5, -5, 0, 1, 1, 2, 3, 4, 5};
        Double[] arrDouble = {-Math.PI, -Math.E, -1.0, 0.5, 0.0, 1.0, 1.0 / 6, 1.1, -691.0 / 2730, 2.2, -1.0 / 30, 4.0, 1.0 / 42, 7.0 / 6};
        String[] arrString = {"","Hi!","My name is"," ","abcd","XYZ"};


        Scanner scan = new Scanner(System.in);

        //Test 1a
//        print(filter(arrInt, new FilterOnlyPositive()));
        //Test 1b
//        System.out.print("Input integer: ");
//        int number = scan.nextInt();
//        Filter<Integer> matchInput = new Filter<>() {
//            @Override
//            public boolean apply(Integer elem) {
//                return elem == number;
//            }
//        };
//        print(filter(arrInt, matchInput));
//        //Test1c
//        print(filter(arrInt, (Integer i) -> i % 2 == 0));

        //Test2
        //bernoulli tests
        int scale = 10;
        int startIndex = 0;
        int finishIndex = 20;
        List<BigDecimal> bernoulliList = BernoulliGenerator.numberList(startIndex, finishIndex, scale);

        //the minimum difference for values to be considered equal
        BigDecimal precise = BigDecimal.valueOf(0.00001);
        //finding function
        BiFunction<Number, List<BigDecimal>, Boolean> find = (i, list) ->
        {
            BigDecimal value = BigDecimal.valueOf(i.doubleValue());
            for (BigDecimal bd : list) {
                if (bd.subtract(value).abs().compareTo(precise) < 0) {
                    return true;
                }
            }
            return false;
        };

        //for integer
        print(filter(arrInt, i ->
                find.apply(i, bernoulliList)));
        //for double
        print(filter(arrDouble, i ->
                find.apply(i, bernoulliList)));

        //Test3
        System.out.print("Input integer: ");
        int length = scan.nextInt();
        //3a
        print(filter(arrString, new Filter<String>(){
            @Override
            public boolean apply(String elem) {
                return elem.length() > length;
            }
        }));
        Filter<String> isLexSort = (str) -> {
           char[] stringToSort = str.toCharArray();
            Arrays.sort(stringToSort);
            return String.valueOf(stringToSort).equals(str);
        };
        //3b
        print(filter(arrString,isLexSort));
    }


}