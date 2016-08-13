package com.lemon.query.user;

import com.lemon.enums.AccountType;
import com.lemon.query.BaseQuery;

/**
 * Created by simpletour_Jenkin on 2016/8/13.
 */
public class UserAccountQuery extends BaseQuery{
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

    public UserAccountQuery() {
    }

    public UserAccountQuery(String account, AccountType type, Long userId) {
        this.account = account;
        this.type = type;
        this.userId = userId;
    }

    public UserAccountQuery(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
