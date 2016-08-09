package com.lemon.utils.type.String2Date;

import com.lemon.convert.IConvertor;
import com.lemon.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by zhouxin on 2016/04/25.
 */
@Component("DEFAULT_STRING2DATE_CONVERTOR")
public class String2DateConvertor implements IConvertor<String, Date> {

    @Override
    public Date convert(String value, Object... obj) {
        if (value == null) return null;
        // 获取传入的时间的格式
        String format = (String)obj[0];
        return DateUtils.parseDate(value, new String[]{format});
    }
}
