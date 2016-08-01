package com.lemon.service;

import com.lemon.domain.MsmSendlog;
import com.lemon.query.BaseQuery;
import com.lemon.query.msmSendlog.MsmSendlogQuery;

import java.util.List;
import java.util.Optional;

/**
 * Created by simpletour_Jenkin on 2016/7/29.
 */
public interface IMsmSendlogService {

    /**
     * 根据条件查询验证码
     * @param mobile
     * @return
     */
    Optional<MsmSendlog> getTheAuthCodeByMobile(String mobile);

    /**
     * 添加
     * @param object
     * @return
     */
    Optional<MsmSendlog> insert(MsmSendlog object);

}
