package com.lemon.service;

import com.lemon.domain.impl.msm.MsmSendlog;

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
