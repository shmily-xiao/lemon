package com.lemon.framework.mapping.core;

/**
 * Created by zhouxin on 2015/12/19.
 * 转换器，用于把参数T转化为U，其中obj为参数列表
 */
public interface IConvertor<T, U> {
    /**
     * 定义转化的行为，当前用于了request对象转化为domain对象
     * @param value
     * @param obj
     * @return
     */
    U convert(T value, Object... obj);
}
