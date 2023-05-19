package com.solovev;

import com.solovev.util.Filter;
import com.solovev.util.FilterOnlyPositive;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
        Integer[] arr = {-1, -2, -3, -4, -5, -5, 0, 1, 1, 2, 3, 4, 5};
        //Test 1a
        print(filter(arr, new FilterOnlyPositive()));
        //Test 1b
        Scanner scan = new Scanner(System.in);
        System.out.print("Input integer: ");
        int number = scan.nextInt();
        Filter<Integer> matchInput = new Filter<>() {
            @Override
            public boolean apply(Integer elem) {
                return elem == number;
            }
        };
        print(filter(arr, matchInput));
        //Test3
        print(filter(arr, (Integer i) -> i % 2 == 0));
    }
}