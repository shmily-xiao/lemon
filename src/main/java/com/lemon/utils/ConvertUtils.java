package com.lemon.utils;

import com.lemon.convert.Form2DomainConvert;
import com.lemon.convert.IConvertor;
import com.lemon.convert.IParamCreator;
import com.lemon.domain.BaseDomain;
import com.lemon.enums.UtilsConstants;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by simpletour_Jenkin on 2016/8/9.
 */
public class ConvertUtils {
    /**
     * 转换工具
     * @param requestObj 请求对象
     * @param targetClass 目标Class类
     * @param objects 参数列表
     * @param <T>
     * @return
     */
    public static <T extends BaseDomain> T convert(Object requestObj, Class<T> targetClass, Object ... objects){
        Field[] fields = ClassUtils.getAllFieldsWithRoot(requestObj.getClass());
        String name = null;
        Object value = null;

        T t = ClassUtils.init(targetClass);
        for (Field field : fields){
            // 若变量没有Transition注解，则直接跳过
            if (!field.isAnnotationPresent(Form2DomainConvert.class)) continue;

            // 获取当前requestObj中field对应的属性的值
            value = ClassUtils.getValue(requestObj, field.getName());

            Form2DomainConvert convert = field.getAnnotation(Form2DomainConvert.class);

            // 获取目标对象属性的name
            name = "".equals(convert.name())?field.getName():convert.name();

            Field targetField = getTargetFiled(targetClass, name);

            // 对获取到的value做特殊转换
            value = transformValue(requestObj, field, value, convert, targetField, objects);

            ClassUtils.setValue(name, value, t);
        }
        return t;
    }

    private static <T extends BaseDomain> Field getTargetFiled(Class<T> targetClass, String name) {
        return Stream.of(ClassUtils.getAllFieldsWithRoot(targetClass)).filter(item -> name.equals(item.getName())).findFirst().orElse(null);
    }

    /**
     * 对获取到的value做特殊转换
     *
     * @param requestObj 请求对象
     * @param field 当前转换的filed
     * @param value 当前转换的filed对应的值
     * @param convert 当前filed的Transition注解信息
     * @param objects 参数列表，当前没有用
     * @return
     */
    private static Object transformValue(Object requestObj, Field field, Object value, Form2DomainConvert convert, Field targetField, Object ... objects) {
        IConvertor convertor = null;

        // 若当前表明是需要保留数据库中的属性值，则直接取数据库中对象对应的属性值
        if (convert.isReserveDB() && objects.length != 0 && objects[0] != null){
            Object dbObject = objects[0];
            return ClassUtils.getValue(dbObject, targetField.getName());
        }

        // 若目标转换对象为一个实际class类，则进行找具体的转换类做转换
        if (!Object.class.equals(convert.clazz()) && value != null){
            convertor = (IConvertor)BeanLocator.findBeanByName(UtilsConstants.TRANSITION + "_" + field.getType().getSimpleName() + "_" + convert.clazz().getSimpleName() +
                    "_" +UtilsConstants.CONVERTOR);

            // 若没有具体的转换类做转换，则直接做原始默认转换
            if (convertor == null) {
                if (value instanceof Collection){
                    Collection collection = (Collection) value;
                    return collection.stream().map(item -> convert(item, convert.clazz(), objects)).collect(Collectors.toList());
                }
                return convert(value, convert.clazz(), objects);
            }
            return convertor.convert(value, convert.clazz(), objects);
        }

        // 当目标对象和源对象不是单单的一一对应时，采用下面的方式进行映射
        // 分两步
        // 1. 先构建需要的参数列表（可定制）
        // 2. 再根据参数列表，在转换器里进行转换（可定制）
        if (!Form2DomainConvert.ConvertorType.DEFAULT.equals(convert.type())){
            // 构建参数列表
            Object[] objs = initParam(requestObj, field, value, convert, targetField, objects);

            convertor = (IConvertor)BeanLocator.findBeanByName(requestObj.getClass().getSimpleName() + "_" + field.getName() + "_" + UtilsConstants.CONVERTOR);

            // 若没有定制的参数creator，则根据类型获取默认的参数creator
            if (convertor == null){
                convertor = (IConvertor) BeanLocator.findBeanByName(UtilsConstants.DEFAULT + "_" + convert.type().name() + "_" + UtilsConstants.CONVERTOR);
            }

            return convertor.convert(value, objs);
        }

        // 若没有转换器，则直接返回
        return value;
    }

    /**
     * 根据参数creator来创建参数列表
     * @param requestObj 请求对象
     * @param field 请求对象中当前循环的Filed
     * @param value 当前Filed的值
     * @param convert 当前Filed上的注解
     * @param objects 其他参数列表
     * @param targetField 目标Filed
     * @return
     */
    private static Object[] initParam(Object requestObj, Field field, Object value, Form2DomainConvert convert, Field targetField, Object[] objects) {

        // 找定制的参数creator
        IParamCreator creator = (IParamCreator) BeanLocator.findBeanByName(requestObj.getClass().getSimpleName() + "_" +
                field.getName() + "_" +
                UtilsConstants.PARAM_CREATOR);
        // 若没有定制的参数creator，则根据类型获取默认的参数creator
        if (creator == null){
            creator = (IParamCreator) BeanLocator.findBeanByName(UtilsConstants.DEFAULT + "_" + convert.type().name() +  "_" + UtilsConstants.PARAM_CREATOR);
        }

        // 若没有默认的creator，则采用公共的creator
        if (creator == null){
            creator = (IParamCreator) BeanLocator.findBeanByName(UtilsConstants.COMMON + "_" + UtilsConstants.PARAM_CREATOR);
        }

        return creator.createParam(requestObj, field, convert, value, targetField, objects);
    }
}
