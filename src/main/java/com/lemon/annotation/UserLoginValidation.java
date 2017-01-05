package com.lemon.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by simpletour_Jenkin on 2017/1/4.
 */
@Retention(RUNTIME)
@Target({ElementType.METHOD})
public @interface UserLoginValidation {

}
