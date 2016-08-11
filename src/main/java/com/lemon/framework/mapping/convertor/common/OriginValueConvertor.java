package com.lemon.framework.mapping.convertor.common;

import com.lemon.framework.mapping.core.IConvertor;
import org.springframework.stereotype.Component;

/**
 * Created by zhouxin on 2016/8/11.
 * 不做转换，直接返回源属性值
 */
@Component("ORIGIN_VALUE_COMMON_CONVERTOR")
public class OriginValueConvertor implements IConvertor {

    @Override
    public Object convert(Object value, Object... obj) {
        return value;
    }
}
