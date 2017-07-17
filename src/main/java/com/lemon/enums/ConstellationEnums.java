package com.lemon.enums;

import com.lemon.domain.impl.content.Interaction;
import sun.rmi.server.InactiveGroupException;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by wangzaijun on 2017/7/17.
 */
public enum  ConstellationEnums {
    Aquarius ("水瓶座",1,20,2,18),
    Pisces ("双鱼座",2,19,3,20),
    Aries ("白羊座",3,21,4,19),
    Taurus("金牛座",4,20,5,20),
    Gemini ("双子座",5,21,6,21),
    Cancer ("巨蟹座",6,22,7,22),
    Leo ("狮子座",7,23,8,22),
    Virgo ("处女座",8,23,9,22),
    Libra ("天秤座",9,23,10,23),
    Scorpio ("天蝎座",10,24,11,22),
    Sagittarius ("射手座",11,23,12,21),
    Capricorn ("摩羯座",12,22,1,19);

    private String name;
    private Integer startMonth;
    private Integer startDay;
    private Integer endMonth;
    private Integer endDay;

    ConstellationEnums(String name, Integer startMonth, Integer startDay, Integer endMonth, Integer endDay){
        this.name = name;
        this.startMonth = startMonth;
        this.startDay = startDay;
        this.endMonth = endMonth;
        this.endDay = endDay;
    }

    public String getName() {
        return name;
    }

    public Integer getStartMonth() {
        return startMonth;
    }

    public Integer getStartDay() {
        return startDay;
    }

    public Integer getEndMonth() {
        return endMonth;
    }

    public Integer getEndDay() {
        return endDay;
    }

    public static ConstellationEnums getEnums(int month, int day){
        return Arrays.asList(ConstellationEnums.values())
                .stream()
                .filter( item -> item.getStartMonth() <= month  && month <= item.getEndMonth() && item.startDay <= day && day <= item.endDay)
                .findFirst()
                .orElse(ConstellationEnums.Aquarius);
    }
}
