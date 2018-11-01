package com.revolut.rate.common.util;

public interface Mapper<T, R> {

    R map(T model);
}
