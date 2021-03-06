package com.lemon.service.impl;

import com.lemon.dao.*;
import com.lemon.domain.impl.access.AccessControl;
import com.lemon.domain.impl.user.User;
import com.lemon.domain.impl.user.UserAccount;
import com.lemon.domain.impl.user.UserRecord;
import com.lemon.enums.AccessControlRowTableType;
import com.lemon.enums.AccountType;
import com.lemon.enums.SignupType;
import com.lemon.enums.StrategyType;
import com.lemon.query.BaseQuery;
import com.lemon.query.user.UserAccountQuery;
import com.lemon.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * Created by simpletour_Jenkin on 2016/7/28.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User,BaseQuery> implements IUserService {

    @Resource
    private IUserDao userDao;

    @Resource
    private IUserAccountDao userAccountDao;

    @Resource
    private IAccessControlDao accessControlDao;

    @Resource
    private IUserRecordDao userRecordDao;

    @Override
    protected IBaseDao<User, BaseQuery> getBaseDao() {
        return userDao;
    }

    @Override
    public Optional<User> find(Long id) {
        Optional<User> user = super.find(id);
        List<UserAccount> userAccounts = userAccountDao.findEntities(new UserAccountQuery(id));
        userAccounts.forEach(userAccount -> {
            if (AccountType.MOBILE.equals(userAccount.getType())){
                user.get().setMobile(userAccount.getAccount());
            }else if (AccountType.QQ.equals(userAccount.getType())){
                user.get().setQqNo(userAccount.getAccount());
            }
        });
        return user;
    }

    @Override
    @Transactional
    public Optional<User> createUser(User user,  String account) {
        // 创建用户表
        Optional<User> newUser = this.insert(user);

        // 创建登录表
        if (SignupType.MOBILE.equals(user.getSignupType())){
            userAccountDao.insert(new UserAccount(newUser.get().getId(),account, AccountType.MOBILE));
        }else if (SignupType.QQ.equals(user.getSignupType())){
            userAccountDao.insert(new UserAccount(newUser.get().getId(),account, AccountType.QQ));
        }
        userAccountDao.insert(new UserAccount(newUser.get().getId(),user.getAccount(), AccountType.SYSTEM_DEFAULT));

        AccessControl accessControl = new AccessControl(newUser.get().getId(), AccessControlRowTableType.USER.name(), StrategyType.PUBLIC);

        Optional<AccessControl> newAccessControl = accessControlDao.insert(accessControl) != 0 ? Optional.of(accessControl):Optional.empty();

        if (!newAccessControl.isPresent()) return Optional.empty();

        UserRecord userRecord = new UserRecord(newAccessControl.get().getId(),newUser.get().getId());

        Optional<UserRecord> newUserRecord = userRecordDao.insert(userRecord)!=0?Optional.of(userRecord):Optional.empty();

        if (!newUserRecord.isPresent()) return Optional.empty();

        return newUser;
    }

    @Override
    public Optional<User> findUserByAccount(String account) {
        List<UserAccount> userAccounts = userAccountDao.findEntities(new UserAccountQuery(account));
        if (userAccounts == null || userAccounts.isEmpty()){
            return Optional.empty();
        }
        Optional<User> user = this.find(userAccounts.get(0).getUserId());
        return user;
    }


}
