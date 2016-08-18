package com.lemon.domain.interfaces.user;

import com.lemon.domain.interfaces.ibase.ICreatedTime;
import com.lemon.enums.AccountType;

/**
 * Created by simpletour_Jenkin on 2016/8/12.
 *
 * 用的的账号表，用于登录的账号记录表
 */
public interface IUserAccount extends ICreatedTime {

    // 用户的id
    Long getUserId();
    void setUserId(Long userId);

    // 用户的手机号，QQ号，我们自己系统的一个账号
    String getAccount();
    void setAccount(String account);

    // 类型，账号的类型，有 mobile qq systemDefault
    AccountType getType();
    void setType(AccountType type);
}
