package com.solovev.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;

public class BernoulliGenerator {

    /**
     * Method returns list of bernoulli numbers from 0 to finish index. B0 = 1;
     *
     * @param finishIndex index to finish must be > 0
     * @return List of bernoulli numbers
     * @throws IllegalArgumentException if finish < 0
     */
    static List<BigDecimal> numberSet(int finishIndex) {
        if (finishIndex < 0) {
            throw new IllegalArgumentException();
        }
        LinkedList<BigDecimal> resultList = new LinkedList<>();
        resultList.add(BigDecimal.ONE);

        BiFunction<Integer, Integer, BigDecimal> binomCoef = (n, k) ->
                new BigDecimal(factorial(BigInteger.valueOf(n)))
                        .divide(
                                new BigDecimal(factorial(BigInteger.valueOf(k)).multiply(BigInteger.valueOf(n - k)))
                        );

        BiFunction<Integer, Integer, BigDecimal> coef = (n, k) ->
                binomCoef.apply(n, k)
                        .divide(new BigDecimal(n - k + 1));

        //number calc
        Function<Integer, BigDecimal> bernoulliNumber = (n) ->
                coef.apply(n, n - 1).multiply(resultList.peekLast());

        //fill list
        IntStream.rangeClosed(0, finishIndex).forEach(i -> resultList.add(bernoulliNumber.apply(i)));
        return resultList;
    }

    /**
     * Method returns list of bernoulli numbers for the given index. B0 = 1;
     *
     * @param startIndex  index to start number to finish must be > 0
     * @param finishIndex index of bernoulli number to finish must be > 0
     * @return
     * @throws IllegalArgumentException if start < 0 or finish < 0
     */
    static List<BigDecimal> numberSet(int startIndex, int finishIndex) {
        if (startIndex < 0 || finishIndex < 0) {
            throw new IllegalArgumentException();
        }
        return null;
    }

    /**
     * Method calculates factorial of a given number
     *
     * @param n number to calculate factorial. Must be >= 0;
     * @return factorial of this BigInt
     * @throws IllegalArgumentException in value is less than 0
     */
    public static BigInteger factorial(BigInteger n) {
        if (n.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("factorial can be count only for a positive number");
        }
        BigInteger result = n.compareTo(BigInteger.ZERO) == 0 ? BigInteger.ONE : n;
        BigInteger counter = n;
        while (counter.compareTo(BigInteger.ONE) > 0) {
            counter = counter.subtract(BigInteger.ONE);
            result = result.multiply(counter);
        }
        return result;
    }
}
