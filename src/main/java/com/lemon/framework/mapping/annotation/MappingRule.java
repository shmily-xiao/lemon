package com.lemon.framework.mapping.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhouxin on 2016/8/9.
 * 将源对象转化为目标对象，标明源对象和目标对象中属性映射关系的注解接口
 * 是一种映射规则
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MappingRule {

    /**
     * 目标对象中的属性名，默认是空字符串
     * @return
     */
    String name() default "";

    /**
     * 映射规则类型
     * @return
     */
    MappingRuleType type() default MappingRuleType.ORIGIN_VALUE;


    /**
     * 属性取值的映射规则的类型
     */
    public enum MappingRuleType{
        // 取源属性值
        ORIGIN_VALUE,

        // 该属性又是另一个源对象，需要继续映射
        CONTINUE_MAPPING,

        // 不取源属性值，取保留数据库里该对象的属性值，此时MappingExcutor.map的参数列表中要传入数据库中对应的对象
        RESERVE_DB
    }
}
