package com.lemon.utils.type.obj2json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lemon.convert.IConvertor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * Created by zhouxin on 2015/12/29.
 */
@Component("DEFAULT_OBJ2JSON_CONVERTOR")
public class Obj2JsonConvertor<T> implements IConvertor<T, Object> {

    @Override
    public Object convert(T value, Object... obj) {
        Field targetField = (Field) obj[0];
        // 若目标field为空，则直接返回value
        if(targetField == null) return value;

        if(targetField.getType().equals(Object.class)){
            return JSON.toJSON(value);
        }else if(targetField.getType().equals(String.class)){
            return JSONObject.toJSONString(value);
        }
        return value;
    }
}
