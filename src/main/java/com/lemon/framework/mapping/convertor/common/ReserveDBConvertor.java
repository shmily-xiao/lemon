package com.lemon.framework.mapping.convertor.common;

import com.lemon.framework.mapping.core.IConvertor;
import com.lemon.utils.ClassUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * Created by zhouxin on 2016/8/11.
 * 映射属性值为数据库中的该字段的值，需要在额外参数列表中获取数据库里的那个对象，再根据当前的源属性Field根据Name来
 */
@Component("RESERVE_DB_COMMON_CONVERTOR")
public class ReserveDBConvertor implements IConvertor {

    @Override
    public Object convert(Object value, Object... obj) {

        // 外参数列表
        Object[] params = (Object[]) obj[7];

        // 数据库中的源对象
        Object dbObj = params[0];

        // 源属性Field
        Field orginField = (Field) obj[2];

        return ClassUtils.getValue(dbObj, orginField);
    }
}
