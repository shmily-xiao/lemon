package com.lemon.framework.mapping.convertor.common;

import com.lemon.framework.mapping.core.IConvertor;
import com.lemon.framework.mapping.excutor.MappingExcutor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by zhouxin on 2016/8/11.
 * 源属性又是另一个源对象，所以需要继续映射
 * 其中若这个源属性是一个集合，则默认按照转为集合目标对象的规则来进行映射
 */
@Component("CONTINUE_MAPPING_COMMON_CONVERTOR")
public class ContinueMappingConvertor implements IConvertor {

    @Override
    public Object convert(Object value, Object... obj) {

        // 外参数列表
        Object[] params = (Object[]) obj[7];

        // 若是集合，则需要把每一个源属性值都转化为目标属性值，然后在组成集合
        if (value instanceof Collection) {
            Collection collection = (Collection) value;
            return collection.stream().map(item -> MappingExcutor.map(item, params)).collect(Collectors.toList());
        }

        return MappingExcutor.map(value, params);
    }
}
