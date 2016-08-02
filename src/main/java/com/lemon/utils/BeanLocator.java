package com.lemon.utils;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouxin on 2015/12/19.
 * 用于寻找bean的定位器，生成对应的bean对象
 */
@Component
public class BeanLocator implements BeanFactoryAware {
    private static BeanFactory beanFactory; //BEAN工厂

    @Override
    public void setBeanFactory(BeanFactory f) throws BeansException {
        this.beanFactory = f;
    }

    public static BeanFactory getBeanfactory() {
        return beanFactory;
    }

    /**
     * 根据bean的名字找bean的对象
     *
     * @param name
     * @return
     */
    public static Object findBeanByName(String name) {
        Object obj = null;
        try{
            obj = beanFactory.getBean(name);
        }catch (Exception e){

        }
        return obj;
    }

    /**
     *根据接口类型找到下面所有的实现类
     * @param type
     * @param <T>
     * @return
     */
    private static <T extends Object> Map<String, T> beanOfTypeIncludingAncestors(Class<T> type) {
        return beanFactory instanceof ListableBeanFactory?
                BeanFactoryUtils.beansOfTypeIncludingAncestors((ListableBeanFactory) beanFactory, type):new HashMap<>();
    }

    /**
     * 根据接口类型找到下面所有的实现类
     * @param type
     * @param <T>
     * @return
     */
    public static <T extends Object> List<T> getAllBeans(Class<T> type) {
        Map<String, T> map = beanOfTypeIncludingAncestors(type);
        return new ArrayList<>(map.values());
    }

}
