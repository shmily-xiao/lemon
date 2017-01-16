package com.lemon.enums;

/**
 * Created by simpletour_Jenkin on 2016/8/3.
 * 标示用户的等级
 */
public enum UserType {
    NEWBIE("幼稚园小朋友",0L,50L),
    KINDERGARTEN_MIN("幼儿园小班小朋友",50L,50L),
    KINDERGARTEN_MID("幼儿园中班班小朋友",100L,100L),
    KINDERGARTEN_MAX("幼儿园大班小朋友",200L,150L),
    PRESCHOOL("学前班小朋友",350L,200L),
    PRIMARY_SCHOOL_STUDENT("小学生",550L,250L),
    MIDDLE_SCHOOL_STUDENT("初中生",800L,300L),
    HIGH_SCHOOL_STUDENT("高中生",1100L,350L),
    UNIVERSITY_STUDENT("大学生",1450L,400L),
    GRADUATE_STUDENT("研究生",1850L,450L),
    DOCTOR("博士",2300L,500L),
    POST_DOCTOR("博士后",2800L,550L),
    ACADEMICIAN("院士",3350L,600L),
    DEFAULT("未知",-1L);

    private String value;
    private Long score;
    // 成长经验值
    private Long empiricalValue;

    UserType(){}

    UserType(String value){
        this.value = value;
    }

    UserType(String value, Long score) {
        this.value = value;
        this.score = score;
    }

    UserType(String value, Long score, Long empiricalValue) {
        this.value = value;
        this.score = score;
        this.empiricalValue = empiricalValue;
    }


    /**
     * 通过分数来查找对应的类型
     * @param score
     * @return
     */
    public static UserType findUserType(Long score){
        for (UserType userType : UserType.values()){
            if (userType.getScore()<=score && score<(userType.getScore()+userType.getEmpiricalValue())){
                return userType;
            }else if (userType.getScore() >= 3950L){
                return UserType.ACADEMICIAN;
            }
        }
        return UserType.DEFAULT;
    }

    public String getValue() {
        return value;
    }

    public Long getScore() {
        return score;
    }

    public Long getEmpiricalValue() {
        return empiricalValue;
    }
}
