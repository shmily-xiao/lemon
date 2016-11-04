package com.lemon.service.impl;

import com.lemon.dao.IMsmSendlogDao;
import com.lemon.domain.impl.msm.MsmSendlog;
import com.lemon.query.msmSendlog.MsmSendlogQuery;
import com.lemon.service.IMsmSendlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * Created by simpletour_Jenkin on 2016/7/29.
 */
@Service
public class MsmSendlogServiceImpl implements IMsmSendlogService {

    @Resource
    private IMsmSendlogDao msmSendlogDao;

    @Override
    public Optional<MsmSendlog> getTheAuthCodeByMobile(String mobile) {

        List<MsmSendlog> msmSendlogs = msmSendlogDao.findEntities(new MsmSendlogQuery(mobile));
        return msmSendlogs.stream().sorted((c1,c2) -> c2.getCreatedTime().compareTo(c1.getCreatedTime())).findFirst();
    }

    @Override
    public Optional<MsmSendlog> insert(MsmSendlog object) {
        return msmSendlogDao.insert(object) !=0 ? Optional.of(object) : Optional.empty();
    }
}
