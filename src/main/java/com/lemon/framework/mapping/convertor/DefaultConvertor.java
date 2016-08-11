package com.lemon.framework.mapping.convertor;

import com.lemon.framework.mapping.core.IConvertor;
import org.springframework.stereotype.Component;

/**
 * Created by zhouxin on 2016/8/11.
 * 默认的参数转换器，在MappingExcutor.map中使用
 * 不做转换，直接返回源属性值
 */
@Component("DEFAULT_CONVERTOR")
public class DefaultConvertor implements IConvertor {

    @Override
    public Object convert(Object value, Object... obj) {
        return value;
    }
}
