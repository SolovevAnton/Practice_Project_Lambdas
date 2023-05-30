package com.solovev;

import com.solovev.util.BernoulliGenerator;
import com.solovev.util.Filter;

import java.lang.reflect.Array;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        Double[] arrDouble = {-Math.PI, -Math.E, -1.0, -0.5, 0.0, 1.0, 1.0/6 , 1.1, 2.2, 3.0, 4.0};

        //Test 1a
//        print(filter(arrInt, new FilterOnlyPositive()));
        //Test 1b
//        Scanner scan = new Scanner(System.in);
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
        System.out.println(BernoulliGenerator.numberList(0,scale));
        System.out.println(BernoulliGenerator.numberList(1,scale));
        System.out.println(BernoulliGenerator.numberList(3,scale));
        System.out.println(BernoulliGenerator.numberList(4,scale));
        System.out.println(BernoulliGenerator.numberList(5,scale));

    }
}