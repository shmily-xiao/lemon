package com.lemon.service.impl;

import com.lemon.dao.IAccessControlDao;
import com.lemon.dao.IBaseDao;
import com.lemon.dao.IUserRecordDao;
import com.lemon.domain.impl.access.AccessControl;
import com.lemon.domain.impl.user.UserRecord;
import com.lemon.enums.StrategyType;
import com.lemon.query.BaseQuery;
import com.lemon.query.user.UserRecordQuery;
import com.lemon.service.IUserRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * Created by Administrator on 2016/8/19 0019.
 */
@Service
public class UserRecordServiceImpl extends BaseServiceImpl<UserRecord, BaseQuery> implements IUserRecordService {

    @Resource
    private IUserRecordDao userRecordDao;

    @Resource
    private IAccessControlDao accessControlDao;

    @Override
    protected IBaseDao<UserRecord, BaseQuery> getBaseDao() {
        return this.userRecordDao;
    }

    @Override
    public Optional<UserRecord> findUserRecordByUserId(Long userId) {
        UserRecordQuery query = new UserRecordQuery();
        query.setUserId(userId);
        List<UserRecord> userRecords = userRecordDao.findEntities(query);
        return (userRecords==null || userRecords.isEmpty())?Optional.empty():Optional.of(userRecords.get(0));
    }

    @Override
    public Boolean isUserPrivateHisAllContents(Long userId) {
        Optional<UserRecord> userRecord= this.findUserRecordByUserId(userId);
        // 如果没有这条记录，说明是userId不正确，默认是锁住的
        if (!userRecord.isPresent()) {
            return Boolean.TRUE;
        }
        Long accessControlId = userRecord.get().getAccessControlId();
        AccessControl accessControl = accessControlDao.find(accessControlId);
        // 如果没有权限的设置记录，默认锁住
        if (accessControl==null){
            return Boolean.TRUE;
        }
        // 显示声明已经锁住了
        if (StrategyType.PRIVATE.equals(accessControl.getStrategy())){
            return Boolean.TRUE;
        }
        // 其他时候说明没有锁着
        return Boolean.FALSE;
    }
}
