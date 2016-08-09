package com.lemon.convert;

/**
 * Created by simpletour_Jenkin on 2016/8/9.
 */
public interface IConvertor<T,U> {
    /**
     * 定义转化的行为，当前用于了request对象转化为domain对象
     * @param value
     * @param obj
     * @return
     */
    U convert(T value, Object ... obj);
}
