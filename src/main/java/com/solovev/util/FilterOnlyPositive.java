package com.solovev.util;

/**
 * Filter realisation, to keep only positive values
 */
public class FilterOnlyPositive implements Filter<Integer> , Detector {
    @Override
    public boolean apply(Integer elem) {
        return elem > 0;
    }

    @Override
    public boolean detect() {
        return this.getClass().getDeclaredFields().length > 0;
    }

}
