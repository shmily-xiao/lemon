package com.lemon.framework.mapping.paramcreator;

import com.lemon.framework.mapping.core.IParamCreator;
import org.springframework.stereotype.Component;

/**
 * Created by zhouxin on 2016/8/11.
 * 默认的参数Creator，在MappingExcutor.map中使用
 * 构造参数规则：
 * 所有参数列表直接返回
 * 顺序依次是：
 * 1. 源属性值
 * 2. 源对象
 * 3. 源属性Field
 * 4. 目标对象
 * 5. 目标属性Field
 * 6. 映射规则注解
 * 7. 映射类型注解
 * 8. 外参数列表数组
 *
 */
@Component("DEFAULT_PARAM_CREATOR")
public class DefaultParamCreator implements IParamCreator {

    @Override
    public Object[] createParam(Object... obj) {
        return obj;
    }
}
