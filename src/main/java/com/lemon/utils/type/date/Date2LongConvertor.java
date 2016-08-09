package com.lemon.utils.type.date;


import com.lemon.convert.IConvertor;
import com.lemon.utils.DateUtils;
import org.springframework.stereotype.Component;

/**
 * Created by zhouxin on 2015/12/19.
 */
@Component("DEFAULT_DATE2LONG_CONVERTOR")
public class Date2LongConvertor implements IConvertor<String, Long> {

    @Override
    public Long convert(String value, Object... obj) {
        if (value == null) return null;
        // 获取传入的时间的格式
        String format = (String)obj[0];
        return Long.valueOf(DateUtils.parseDate(value, new String[]{format}).getTime() / 1000);
    }
}
