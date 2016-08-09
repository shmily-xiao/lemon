package com.lemon.convert;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhouxin on 2016/8/9.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Form2DomainConvert {
    /**
     * 目标属性的名字
     * @return
     */
    String name() default "";

    /**
     * 目标对象类型
     * @return
     */
    Class clazz() default Object.class;

    /**
     * 转换器类型
     * @return
     */
    ConvertorType type() default ConvertorType.DEFAULT;

    /**
     * 转换器转换时需要的参数
     * @return
     */
    String args() default "";

    /**
     * 是否要保留数据库中的取值，默认不保留，若保留的话，在调用ConvertUtils的时候，最后参数要传入数据库里的domain对象
     * @return
     */
    boolean isReserveDB() default false;

    public enum ConvertorType {
        DEFAULT,

        // 用于时间字符串转化为Long的情况，此时args参数应该是时间的格式字符串例如：yyyy-MM-dd
        DATE2LONG,

        // 获取该属性时，要用request对象中的其他属性作条件，此时args参数应该是其他属性的名字
        MIXPARAM,

        // 通过Id获取对应的domain对象，此时args参数应该写对应domain对象的包名+类名
        ID2DOMAIN,

        // 把对象改为JSON对象
        OBJ2JSON,

        // 把时间字符串转化为时间类型
        STRING2DATE,

        // 枚举字符串转化为枚举，其中枚举字符串要用枚举的name
        ENUMNAME2ENUM,

        // 其他类型
        OTHER;
    }
}
