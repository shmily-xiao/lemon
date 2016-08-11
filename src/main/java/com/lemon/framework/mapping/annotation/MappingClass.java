package com.lemon.framework.mapping.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhouxin on 2016/8/9.
 * 用在源对象上，用于标明目标对象的Class
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MappingClass {

    /**
     * 该源对象需要转化成的目标对象所对应的class
     * @return
     */
    Class value();

    /**
     * 当前映射的类型，默认是form对象映射domain对象
     * @return
     */
    MappingClassType type() default MappingClassType.FORM_2_DOMAIN;

    /**
     * 当前源对象和目标对象映射的类型
     */
    public enum MappingClassType{
        // form对象映射为domain对象
        FORM_2_DOMAIN
    }
}

