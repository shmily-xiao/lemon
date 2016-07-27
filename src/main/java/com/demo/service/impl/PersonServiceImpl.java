package com.demo.service.impl;

import com.demo.dao.InfoDao;
import com.demo.dao.PersonDao;
import com.demo.domain.BrandMobileInfoEntity;
import com.demo.service.PersonService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by simpletour_java on 2015/9/9.
 */
public class PersonServiceImpl implements PersonService {

    @Resource
    private PersonDao personDao;

    @Resource
    private InfoDao infoDao;

    @Override
    public void updateByConditions(BrandMobileInfoEntity brandMobileInfoEntity) {
        personDao.updateByConditions(brandMobileInfoEntity);
    }

    @Override
    public void insertByConditions(BrandMobileInfoEntity brandMobileInfoEntity) {
        infoDao.insertByConditions(brandMobileInfoEntity);
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    public void setInfoDao(InfoDao infoDao) {
        this.infoDao = infoDao;
    }
}
