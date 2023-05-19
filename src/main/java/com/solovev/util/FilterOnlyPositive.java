package com.solovev.util;

/**
 * Filter realisation, to keep only positive values
 */
public class FilterOnlyPositive implements Filter<Integer> {
    @Override
    public boolean apply(Integer elem) {
        return elem > 0;
    }
}
