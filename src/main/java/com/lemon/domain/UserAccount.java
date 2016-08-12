package com.lemon.domain;

import com.lemon.domain.interfaces.IUserAccount;
import com.lemon.enums.AccountType;

/**
 * Created by simpletour_Jenkin on 2016/8/12.
 */
public class UserAccount extends BaseDomain implements IUserAccount{

    /**
     * 用户的id
     *
     **/
    private Long userId;

    /**
     * 用户的账号
     */
    private String account;

    /**
     * 账号的类型
     */
    private AccountType type;


    @Override
    public String getAccount() {
        return account;
    }

    @Override
    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
