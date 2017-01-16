package com.lemon.service.impl;

import com.lemon.dao.IAccessControlDao;
import com.lemon.dao.IBaseDao;
import com.lemon.domain.impl.access.AccessControl;
import com.lemon.query.BaseQuery;
import com.lemon.service.IAccessControlService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;

/**
 * Created by simpletour_Jenkin on 2016/8/22.
 */
@Service
public class AccessControlServiceImpl extends BaseServiceImpl<AccessControl, BaseQuery> implements IAccessControlService  {

    @Resource
    private IAccessControlDao accessControlDao;

    @Override
    protected IBaseDao<AccessControl, BaseQuery> getBaseDao() {
        return this.accessControlDao;
    }
}
