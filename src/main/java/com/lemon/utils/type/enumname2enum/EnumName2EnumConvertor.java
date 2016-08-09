package com.lemon.utils.type.enumname2enum;

import com.lemon.convert.IConvertor;
import com.lemon.utils.EnumUtils;
import org.springframework.stereotype.Component;

/**
 * Created by zhouxin on 2015/12/28.
 */
@Component("DEFAULT_ENUMNAME2ENUM_CONVERTOR")
public class EnumName2EnumConvertor<T extends Enum> implements IConvertor<String, T> {

    @Override
    public T convert(String value, Object... obj) {
        if (value == null) return null;
        // 获取当前的枚举的包全名
        String enumName = (String) obj[0];
        T result = null;
        try {
            Class<T> tClass = (Class<T>) Class.forName(enumName);
            result = EnumUtils.getEnumByEnumNameToOp(tClass, value).get();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
