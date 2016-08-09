package com.lemon.utils;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by simpletour_Jenkin on 2016/8/9.
 */
public class EnumUtils {
    /**
     * 根据枚举的名字获取枚举类型
     * @param enumType
     * @param enumName
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T extends java.lang.Enum, U> T getEnumByEnumName(Class<T> enumType, U enumName){
        T[] enums = enumType.getEnumConstants();
        return Stream.of(enums).filter(t -> t.name().equalsIgnoreCase(enumName.toString())).findFirst().orElse(enums[0]);
    }

    /**
     * 根据枚举的名字获取枚举类型
     * @param enumType
     * @param enumName
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T extends java.lang.Enum, U> Optional<T> getEnumByEnumNameToOp(Class<T> enumType, U enumName){
        T[] enums = enumType.getEnumConstants();
        return Stream.of(enums).filter(t -> t.name().equalsIgnoreCase(enumName.toString())).findFirst();
    }

    /**
     * 根据枚举类型，和枚举属性中的值，还有第几个属性值来获取枚举
     * @param enumType
     * @param filedValue
     * @param methodIndex
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> T getEnumByFieldNum(Class<T> enumType, U filedValue, Integer methodIndex){
        Field field = Stream.of(enumType.getDeclaredFields()).filter(item -> !item.isEnumConstant())
                .filter(item -> !item.isSynthetic())
                .collect(Collectors.toList()).get(methodIndex-1);
        return getEnumByFieldName(enumType, filedValue, field.getName());

    }

    /**
     * 根据枚举类型，和枚举属性中的值，获取枚举，默认取第二个属性
     * @param enumType
     * @param filedValue
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> T getEnumByFieldNum(Class<T> enumType, U filedValue){
        return getEnumByFieldNum(enumType, filedValue, 2);
    }

    /**
     * 根据枚举类型，和枚举属性中的值，和属性value获取枚举
     * @param enumType
     * @param filedValue
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> T getEnumByValueField(Class<T> enumType, U filedValue){
        return getEnumByFieldName(enumType, filedValue, "value");
    }

    /**
     * 根据枚举类型，和枚举属性中的值，和属性名字获取枚举
     * @param enumType
     * @param filedValue
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> T getEnumByFieldName(Class<T> enumType, U filedValue, String fieldName){
        T[] enums = enumType.getEnumConstants();
        return Stream.of(enums).filter(t-> filedValue.equals(ClassUtils.getValue(t, fieldName)))
                .findFirst().orElse(enums[0]);
    }


    public static <T, U> Optional<T> getEnumByFieldNameWithOutDefault(Class<T> enumType, U filedValue, String fieldName){
        T[] enums = enumType.getEnumConstants();
        return Stream.of(enums).filter(t-> filedValue.equals(ClassUtils.getValue(t, fieldName)))
                .findFirst();
    }
}
