package com.lemon.utils;

import com.lemon.domain.impl.BaseDomain;
import com.lemon.enums.UtilsConstants;
import com.lemon.framework.mapping.core.IConvertor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by simpletour_Jenkin on 2016/8/9.
 */
public class ClassUtils {
    /**
     * 获取类clazz的所有Field，包括其父类的Field，如果重名，以子类Field为准。
     *
     * @param clazz
     * @return Field数组
     */
    public static Field[] getAllFieldsWithRoot(Class<?> clazz) {
        return getAllFieldsWithRoot(clazz, null);
    }

    /**
     * 获取类clazz的所有Field，包括其父类的Field，如果重名，以子类Field为准。无静态属性
     *
     * @param clazz
     * @return Field数组
     */
    public static Field[] getAllFieldsWithRootNoStatic(Class<?> clazz) {
        return getAllFieldsWithRoot(clazz, item -> !Modifier.isStatic(item.getModifiers()));
    }

    /**
     * 获取类clazz的所有Field，包括其父类的Field，如果重名，以子类Field为准。
     *
     * @param clazz
     * @return Field数组
     */
    public static Field[] getAllFieldsWithRoot(Class<?> clazz, Predicate<Field> predicate) {
        List<Field> fieldList = new ArrayList<Field>();
        Field[] dFields = clazz.getDeclaredFields();
        if (null != dFields && dFields.length > 0) fieldList.addAll(Arrays.asList(dFields));

        // 若父类是Object，则直接返回当前Field列表
        Class<?> superClass = clazz.getSuperclass();
        if (Object.class.equals(superClass)) return dFields;

        // 递归查询父类的field列表
        Field[] superFields = getAllFieldsWithRoot(superClass, predicate);
        if (null != superFields && superFields.length > 0) {
            Stream.of(superFields).filter(field -> !fieldList.contains(field))
                    .filter(field -> predicate == null || predicate.test(field))
                    .forEach(field -> fieldList.add(field));
        }

        Field[] result = new Field[fieldList.size()];
        fieldList.toArray(result);
        return result;
    }

    /**
     * 根据field名字和目标class获取field字段
     *
     * @param clazz
     * @param name
     * @return
     */
    public static Field getFieldWithRoot(Class<?> clazz, String name) {
        Field[] dFields = getAllFieldsWithRoot(clazz);
        return Stream.of(dFields).filter(field -> field.getName().equals(name)).findFirst().orElse(null);
    }

    /**
     * 获取Class类下的所有声明的字段的filed字段
     *
     * @param targetClass
     * @return
     */
    public static Field[] getAllFields(Class targetClass) {
        return targetClass.getDeclaredFields();
    }

    /**
     * 根据请求对象和属性的name来用get方法获取对应的属性值
     *
     * @param obj
     * @param name
     * @return
     */
    public static Object getValue(Object obj, String name) {
        Object value = null;
        try {
            Method method = obj.getClass().getMethod(UtilsConstants.GETTER + name.substring(0, 1).toUpperCase() + name.substring(1, name
                    .length()));
            value = method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     *
     * @param obj
     * @param field
     * @return
     */
    public static Object getValue(Object obj, Field field){
        return getValue(obj, field.getName());
    }

    /**
     * 初始化目标对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends BaseDomain> T init(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 初始化目标对象
     *
     * @param clazz
     * @return
     */
    public static Object initObj(Class clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把找到的具体值设置到目标对象中
     *
     * @param name
     * @param value
     * @param targetObj
     * @param <T>
     */
    public static <T extends BaseDomain> void setValue(String name, Object value, Object targetObj) {
        try {
            Class fieldClass = getFieldWithRoot(targetObj.getClass(), name).getType();
            Method method = targetObj.getClass().getMethod(UtilsConstants.SETTER + StringUtils.toFirstUpCase(name), fieldClass);
            // 从数据库转换到实体的类时候，有可能出现传入的value和目标的实体类的属性类型不匹配的情况，导致报错java.lang.IllegalArgumentException: argument type mismatch
            // 这里把传入的value转换为对应的实体类的属性的类型
            value = processValue(value, fieldClass, targetObj);
            method.invoke(targetObj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 把找到的具体值设置到目标对象中
     *
     * @param targetField
     * @param value
     * @param targetObj
     * @param <T>
     */
    public static <T extends BaseDomain> void setValue(Field targetField, Object value, Object targetObj) {
        try {
            Method method = targetObj.getClass().getMethod(UtilsConstants.SETTER + StringUtils.toFirstUpCase(targetField.getName()), targetField.getType());
            method.invoke(targetObj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取类名中删除removed部分后的名称
     *
     * @param clazz
     * @param removed 要删除的部分
     * @return
     */
    public static String getPartialClassName(Class clazz, String removed) {
        String canonicalName = clazz.getSimpleName();
        return canonicalName.substring(0, canonicalName.lastIndexOf(removed));
    }

    /**
     * 当value和fieldClass类型不匹配时，进行转换，把value转化为fieldClass
     * 当前遇到的情况数据库传来的value类型为bigInteger，目标属性的值为Long
     *
     * @param value
     * @param fieldClass
     * @param targetObj
     * @return
     */
    private static Object processValue(Object value, Class fieldClass, Object targetObj) {

        // 若值为null，则直接返回，不需要转换
        if (value == null) return value;

        // 若数据库是字符串类型，而目标是枚举类型，则根据枚举的name进行转化
        if (fieldClass.isEnum()) {
            return EnumUtils.getEnumByEnumName(fieldClass, value);
        } else {
            IConvertor convertor = (IConvertor) BeanLocator.findBeanByName(value.getClass().getSimpleName() + "_" + fieldClass.getSimpleName() +
                    UtilsConstants.SET_VALUE);
            if (convertor == null) return value;
            return convertor.convert(value, fieldClass, targetObj);
        }
    }
}
