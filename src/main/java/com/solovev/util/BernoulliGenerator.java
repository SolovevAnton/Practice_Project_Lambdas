package com.solovev.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;

public class BernoulliGenerator implements  Detector {
    int testField;

    /**
     * Method returns list of bernoulli numbers from 0 to finish index. B0 = 1;
     *
     * @param finishIndex index to finish must be > 0
     * @return List of bernoulli numbers
     * @throws IllegalArgumentException if finish < 0
     */
    public static List<BigDecimal> numberList(int finishIndex, int scale) {
        if (finishIndex < 0) {
            throw new IllegalArgumentException("finish index must > 0");
        }
        List<BigDecimal> resultList = new ArrayList<>(finishIndex + 1);

        BiFunction<Integer, Integer, BigDecimal> binomCoef = (n, k) ->
                new BigDecimal(factorial(BigInteger.valueOf(n)))
                        .divide(
                                new BigDecimal(
                                        factorial(BigInteger.valueOf(k))
                                                .multiply(factorial(BigInteger.valueOf(n - k)))),
                                scale,
                                RoundingMode.HALF_EVEN
                        );

        BiFunction<Integer, Integer, BigDecimal> coef = (n, k) ->
                binomCoef.apply(n, k)
                        .divide(new BigDecimal(n - k + 1), scale, RoundingMode.HALF_EVEN);

        //number calc
        Function<Integer, BigDecimal> bernoulliNumber = (n) -> {
            //line added for more precision, but will work without it
            if (n > 1 && n % 2 != 0) {
                return BigDecimal.ZERO;
            }

            BigDecimal sum = new BigDecimal(0);
            for (int i = 0; i < n; i++) {
                sum = sum.add(coef.apply(n, i).multiply(resultList.get(i)));
            }
            return BigDecimal.ONE.subtract(sum);
        };

        IntStream
                .rangeClosed(0, finishIndex)
                .forEach(i -> resultList.add(bernoulliNumber.apply(i)));

        return resultList;
    }

    /**
     * Method returns list of bernoulli numbers for the given index. B0 = 1;
     *
     * @param startIndex  index to start number to finish must be > 0
     * @param finishIndex index of bernoulli number to finish must be > 0
     * @return list of bernoulli numbers from start to finish indexes including
     * @throws IllegalArgumentException if start < 0 or finish < 0
     */
    public static List<BigDecimal> numberList(int startIndex, int finishIndex, int scale) {
        if (startIndex < 0) {
            throw new IllegalArgumentException("start index must > 0");
        }

        //ToDo refactor steam
        return numberList(finishIndex, scale)
                .stream()
                .skip(startIndex)
                .toList();
    }

    /**
     * Method calculates factorial of a given number
     *
     * @param n number to calculate factorial. Must be >= 0;
     * @return factorial of this BigInt
     * @throws IllegalArgumentException in value is less than 0
     */
    private static BigInteger factorial(BigInteger n) {
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

    @Override
    public boolean detect() {
        return BernoulliGenerator.class.getDeclaredFields().length > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BernoulliGenerator that = (BernoulliGenerator) o;

        return testField == that.testField;
    }

    @Override
    public int hashCode() {
        return testField;
    }
}
