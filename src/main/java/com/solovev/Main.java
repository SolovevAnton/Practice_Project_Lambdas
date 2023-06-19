package com.solovev;

import com.solovev.util.BernoulliGenerator;
import com.solovev.util.Detector;
import com.solovev.util.Filter;
import com.solovev.util.FilterOnlyPositive;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.BiFunction;

public class Main {
    private int testingField;

    public Main(int testingField) {
        this.testingField = testingField;
    }

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

    /**
     * Method to check if string is a word
     * ;
     * As words are counted those, contain more than 2 letters (and only latin letters)
     *
     * @param str to check
     * @return true if string is a word
     */
    public boolean isWord(String str) {
        int minLettersToBeWord = 2;
        String regexThatWordsCanContain = "[a-zA-Z]+";
        return str.length() > minLettersToBeWord && str.matches(regexThatWordsCanContain);
    }

    public static void main(String[] args) {
        Integer[] arrInt = {-1, -2, -3, -4, -5, -5, 0, 1, 1, 2, 3, 4, 5};
        Double[] arrDouble = {-Math.PI, -Math.E, -1.0, 0.5, 0.0, 1.0, 1.0 / 6, 1.1, -691.0 / 2730, 2.2, -1.0 / 30, 4.0, 1.0 / 42, 7.0 / 6};
        String[] arrString = {"", "Hi!", "My name is", " ", "abcd", "XYZ"};
        String[] arrStringWithNulls = {"", null, "Hi!", "My name is", " ", "abcd", null, "XYZ"};
        Object[] constructed = {new Main(1), new BernoulliGenerator(), new FilterOnlyPositive()};
        Detector[] detectObjects = {new BernoulliGenerator(), new BernoulliGenerator(), new FilterOnlyPositive()};
        Main mainToref = new Main(1);

        Scanner scan = new Scanner(System.in);

        //Test 1a
        print(filter(arrInt, new FilterOnlyPositive()));
        //Test 1b
        System.out.print("Input integer: ");
        int number = scan.nextInt();
        Filter<Integer> matchInput = new Filter<>() {
            @Override
            public boolean apply(Integer elem) {
                return elem == number;
            }
        };
        print(filter(arrInt, matchInput));
//        //Test1c
        print(filter(arrInt, i -> i % 2 == 0));

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
        print(filter(arrString, isLexSort));
        //3c
        print(filter(arrString, mainToref::isWord));

        //Test 4

        Filter<Object> isNotDefaultObj = ob -> {
            try {
                return !ob.getClass().getDeclaredConstructor().newInstance().equals(ob);
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException | IllegalArgumentException ignored) {
                return true;
            }
        };
        print(filter(arrStringWithNulls, Objects::nonNull));
        print(filter(constructed, isNotDefaultObj));

        //Test 5
        print(filter(detectObjects, Detector::detect));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Main main = (Main) o;

        return testingField == main.testingField;
    }

    @Override
    public int hashCode() {
        return testingField;
    }
}