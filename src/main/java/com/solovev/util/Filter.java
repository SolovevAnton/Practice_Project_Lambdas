package com.solovev.util;
@FunctionalInterface
public interface  Filter<T> {
   boolean apply(T elem);
}
