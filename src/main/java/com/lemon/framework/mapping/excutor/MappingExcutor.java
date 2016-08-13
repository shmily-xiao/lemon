package com.lemon.framework.mapping.excutor;

import com.lemon.framework.mapping.annotation.MappingRule;
import com.lemon.framework.mapping.annotation.MappingClass;
import com.lemon.framework.mapping.core.IConvertor;
import com.lemon.framework.mapping.core.IParamCreator;
import com.lemon.utils.BeanLocator;
import com.lemon.utils.ClassUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by zhouxin on 2016/8/10.
 * 源对象转化为目标对象功能的执行器，对外可调用该方法执行
 *
 * 寻找Bean时的规则：同一个接口的Bean通过Bean Name分三类
 * 1. 定制
 * 2. 公共
 * 3. 默认
 * 优先级它们是依次递减，也就是定制优先级最高，默认优先级最低
 * 但是定制类和公共类的Bean不一定存在，但是默认的Bean是一定存在的，有且只有一个
 */
public class MappingExcutor {

    // 实现IParamCreator接口的Bean name的后缀
    private static final String PARAM_CREATOR = "PARAM_CREATOR";

    // 实现IConvertor接口的Bean name的后缀
    private static final String CONVERTOR = "CONVERTOR";

    // 公共
    private static final String COMMON = "COMMON";

    // 默认
    private static final String DEFAULT = "DEFAULT";

    // 过滤Field的过滤器集合
    private final static List<Predicate<Field>> fieldFilters = Arrays.asList(
            // 属性必须是包含Mapping注解的，才表示需要映射
            field -> field.isAnnotationPresent(MappingRule.class)
    );

    /**
     * 根据源对象映射为目标对象
     * @param originObj 源对象
     * @return 目标对象
     */
    public static <T> T map(Object originObj, Object ... paramObjs){
        Objects.requireNonNull(originObj);

        // 获取源对象中的所有属性（无静态属性）
        Field[] originFields = ClassUtils.getAllFieldsWithRootNoStatic(originObj.getClass());

        // 根据源所在的Class类型上的类注解MappingClass来找到对应目标类型的class
        MappingClass mappingClass = (MappingClass) originObj.getClass().getAnnotation(MappingClass.class);

        // 创建目标对象
        T targetObj = (T) ClassUtils.initObj(mappingClass.value());

        Arrays.stream(originFields).filter(MappingExcutor::isFieldValid)
                                   .forEach(originField -> setTargetObjValue(originField, originObj, targetObj, mappingClass, paramObjs));
        return targetObj;
    }

    /**
     * 用源对象的属性值根据Mapping注解配置的规则来映射属性值到目标对象
     * @param originField
     * @param targetObj
     * @param <T>
     */
    private static <T> void setTargetObjValue(Field originField, Object originObj, T targetObj, MappingClass mappingClass, Object ... paramObjs) {

        // 获取当前属性对应的属性的值
        Object originValue = ClassUtils.getValue(originObj, originField.getName());

        // 获取当前属性的Mapping注解
        MappingRule mappingRule = originField.getAnnotation(MappingRule.class);

        // 获取目标对象属性的name
        String targetName = "".equals(mappingRule.name()) ? originField.getName() : mappingRule.name();

        // 获取目标属性Field对象
        Field targetField = getTargetFiled(targetObj.getClass(), targetName);
        Objects.requireNonNull(targetField);

        // 获取最终需要映射的属性值
        Object resultValue = mappingValue(originValue, originField, mappingRule, targetObj, targetField, originObj, mappingClass, paramObjs);

        // 把属性值set到目标对象上
        ClassUtils.setValue(targetField, resultValue, targetObj);
    }

    /**
     * 根据映射规则Mapping来调整当前源属性值originValue，来转化为最终需要的目标属性值
     * @param originValue 源属性值
     * @param originField 源属性Field
     * @param mappingRule 映射规则注解
     * @param targetObj 目标对象
     * @param targetField 目标属性Field
     * @param originObj 源对象
     * @param mappingClass 映射类型注解
     * @param paramObjs 外参数列表
     * @return
     */
    private static <T> Object mappingValue(Object originValue, Field originField, MappingRule mappingRule, T targetObj, Field targetField, Object originObj, MappingClass mappingClass, Object ... paramObjs) {

        // 构建参数列表，用于后续转换器的进行转化的时候的参数
        Object[] objs = initParam(originValue, originField, mappingRule, targetObj, targetField, originObj, mappingClass, paramObjs);

        // 找到转换当前所需要的属性值的转换器
        IConvertor convertor = initConvertor(originObj, originField, targetObj, targetField, mappingRule, mappingClass);

        return convertor.convert(originValue, objs);
    }

    /**
     * 寻找转换器（Convertor）
     * 1. 先找定制转换器
     * 2. 若定制转换器不存在，则根据映射规则类型找通用的转换器
     * 3. 若通用转换器不存在，则采用默认转换器
     * @param originObj 源对象
     * @param originField 源属性Field
     * @param targetObj 目标对象
     * @param targetField 目标属性Field
     * @param mappingRule 映射规则注解
     * @param mappingClass 映射类型注解
     * @param <T>
     * @return
     */
    private static <T> IConvertor initConvertor(Object originObj, Field originField, T targetObj, Field targetField, MappingRule mappingRule, MappingClass mappingClass) {

        // 寻找定制的转换器
        IConvertor customizeConvertor = findCustomizeConvertor(mappingClass, mappingRule, originObj, originField, targetObj, targetField);

        // 若定制的转化器存在，则按照定制的转换器进行属性值映射
        if (customizeConvertor != null) return customizeConvertor;

        // 若定制转换器不存在，则找该映射类型下的公共转换器
        IConvertor commonMappingClassConvertor = findCommonMappingClassConvertor(mappingClass, mappingRule);

        // 若该映射类型下的公共转换器存在，则按照该映射类型下的公共转换器进行属性值映射
        if (commonMappingClassConvertor != null) return commonMappingClassConvertor;

        // 若该映射类型下的公共转换器不存在，则找映射规则下的公共转换器
        IConvertor commonMappingRuleConvertor = findCommonMappingRuleConvertor(mappingRule);

        // 若该映射规则下的公共转换器存在，则按照映射规则下的公共转换器进行属性值映射
        if (commonMappingRuleConvertor != null) return commonMappingRuleConvertor;

        // 若公共的转换器不存在，则找默认转换器，默认转换器一定存在，refer to：DefaultConvertor
        IConvertor defaultConvertor = findDefaultConvertor();
        return defaultConvertor;
    }

    /**
     * 找默认转换器
     * @return
     */
    private static IConvertor findDefaultConvertor() {

        // Bean名字的规则："DEFAULT" + "CONVERTOR"
        String beanName = DEFAULT + "_" + CONVERTOR;
        return findConvertor(beanName);
    }

    /**
     * 找映射规则下的公共转换器
     * @param mappingRule
     * @return
     */
    private static IConvertor findCommonMappingRuleConvertor(MappingRule mappingRule) {
        // Bean名字的规则：映射规则的类型 + "COMMON" + "CONVERTOR"
        String beanName = mappingRule.type() + "_" + COMMON + "_" + CONVERTOR;
        return findConvertor(beanName);
    }

    /**
     * 找映射类型下的公共转换器
     * @param mappingClass
     * @param mappingRule
     * @return
     */
    private static IConvertor findCommonMappingClassConvertor(MappingClass mappingClass, MappingRule mappingRule) {

        // Bean名字的规则：映射类型注解的类型 + 映射规则的类型 + "COMMON" + "CONVERTOR"
        String beanName = mappingClass.type() + "_" + mappingRule.type() + "_" + COMMON + "_" + CONVERTOR;
        return findConvertor(beanName);
    }

    /**
     * 根据参数列表寻找定制转换器
     * @param mappingClass 映射类型注解
     * @param mappingRule 映射规则注解
     * @param originObj 源对象
     * @param originField 源属性Field
     * @param targetObj 目标对象
     * @param targetField 目标属性Field
     * @return
     */
    private static <T> IConvertor findCustomizeConvertor(MappingClass mappingClass, MappingRule mappingRule, Object originObj, Field originField, T targetObj, Field targetField) {

        // Bean名字的规则：映射类型注解的类型 + 映射规则的类型 + 源对象的类名 + 源属性的名字 + 目标对象的类型 + 目标属性的名字 + "CONVERTOR"
        String beanName = mappingClass.type() + "_" + mappingRule.type() + "_" +
                          originObj.getClass().getSimpleName() + "_" + originField.getName() + "_" +
                          targetObj.getClass().getSimpleName() + "_" + targetField.getName() + "_" + CONVERTOR;
        return findConvertor(beanName);
    }

    /**
     * 根据Bean名字找寻IConvertor的Bean
     * @param beanName
     * @return
     */
    private static IConvertor findConvertor(String beanName){
        return (IConvertor) BeanLocator.findBeanByName(beanName);
    }

    /**
     * 构建参数列表
     * 1. 先找定制参数Creator
     * 2. 若定制参数Creator不存在，则采用MappingClass.type和MappingRule.type下的公共的参数Creator
     * 3. 若MappingClass.type下的公共默认的参数Creator不存在，则采用默认参数Creator
     * @param originValue 源属性值
     * @param originField 源属性Field
     * @param mappingRule 映射规则注解
     * @param targetObj 目标对象
     * @param targetField 目标属性Field
     * @param originObj 源对象
     * @param mappingClass 映射类型注解
     * @param paramObjs 外参数列表
     * @return
     */
    private static <T> Object[] initParam(Object originValue, Field originField, MappingRule mappingRule, T targetObj,
                                          Field targetField, Object originObj, MappingClass mappingClass, Object[] paramObjs) {

        // 找定制的参数Creator
        IParamCreator customizeCreator = findCustomizeCreator(mappingClass, mappingRule, originObj, originField, targetObj, targetField);

        // 若定制的参数Creator存在，则按照定制的参数Creator来构造参数列表
        if (customizeCreator != null) return customizeCreator.createParam(originValue, originObj, originField, targetObj, targetField, mappingRule, mappingClass, paramObjs);

        // 找映射类型注解的类型下的公共参数Creator
        IParamCreator commonCreator = findCommonCreator(mappingClass, mappingRule);

        // 若当前映射类型下的公共参数Creator存在，则按照公共参数Creator来构造参数列表
        if (commonCreator != null) return commonCreator.createParam(originValue, originObj, originField, targetObj, targetField, mappingRule, mappingClass, paramObjs);

        // 找默认的参数Creator
        IParamCreator defaultCreator = findDefaultCreator();
        Objects.requireNonNull(defaultCreator);

        // 定制的参数Creator一定存在，refer to：DefaultParamCreator
        return defaultCreator.createParam(originValue, originObj, originField, targetObj, targetField, mappingRule, mappingClass, paramObjs);
    }

    /**
     * 寻找默认的参数Creator
     * @return
     */
    private static IParamCreator findDefaultCreator() {

        // Bean名字的规则："DEFAULT" + "_PARAM_CREATOR"
        String beanName = DEFAULT + "_" + PARAM_CREATOR;
        return findParamCreator(beanName);
    }

    /**
     * 根据映射类型和映射规则，寻找该类型和该规则下的公共参数Creator
     * @param mappingClass 映射类型注解
     * @param mappingRule 映射规则注解
     * @return
     */
    private static IParamCreator findCommonCreator(MappingClass mappingClass, MappingRule mappingRule) {

        // Bean名字的规则：映射类型注解的类型 + 映射规则的类型 + "COMMON" + "_PARAM_CREATOR"
        String beanName = mappingClass.type() + "_" + mappingRule.type() + "_" + COMMON + "_" + PARAM_CREATOR;
        return findParamCreator(beanName);
    }

    /**
     * 根据参数列表寻找定制的参数Creator
     * @param mappingClass 映射类型注解
     * @param mappingRule 映射规则注解
     * @param originObj 源对象
     * @param originField 源属性Field
     * @param targetObj 目标对象
     * @param targetField 目标属性Field
     * @return
     */
    private static <T> IParamCreator findCustomizeCreator(MappingClass mappingClass, MappingRule mappingRule, Object originObj, Field originField, T targetObj, Field targetField) {

        // Bean名字的规则：映射类型注解的类型 + 映射规则的类型 + 源对象的类名 + 源属性的名字 + 目标对象的类型 + 目标属性的名字 + "_PARAM_CREATOR"
        String beanName = mappingClass.type() + "_" + mappingRule.type() + "_" +
                          originObj.getClass().getSimpleName() + "_" + originField.getName() + "_" +
                          targetObj.getClass().getSimpleName() + "_" + targetField.getName() + "_" + PARAM_CREATOR;
        return findParamCreator(beanName);
    }

    /**
     * 根据Bean名字找寻IParamCreator的Bean
     * @param beanName
     * @return
     */
    private static IParamCreator findParamCreator(String beanName){
        return (IParamCreator) BeanLocator.findBeanByName(beanName);
    }

    /**
     * 根据名字找到对应的Field对象
     * @param targetClass
     * @param name
     * @param <T>
     * @return
     */
    private static <T> Field getTargetFiled(Class<T> targetClass, String name) {
        return Stream.of(ClassUtils.getAllFieldsWithRoot(targetClass)).filter(item -> name.equals(item.getName())).findFirst().orElse(null);
    }

    /**
     * 判断当前属性是否有效
     * @param field
     * @return
     */
    private static Boolean isFieldValid(Field field){
        return fieldFilters.stream().allMatch(fieldPredicate -> fieldPredicate.test(field));
    }

    /**
     * 根据原对象中注解的目标对象类型进行创建对象
     * @param originObj
     * @return
     */
    private static <T> T initTargetObj(Object originObj) {
        // 根据源所在的Class类型上的类注解MappingClass来找到对应目标类型的class
        MappingClass mappingClass = (MappingClass) originObj.getClass().getAnnotation(MappingClass.class);
        return (T) ClassUtils.initObj(mappingClass.value());
    }
}
