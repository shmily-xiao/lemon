package com.demo.service.impl;

import com.demo.dao.CookiesDao;
import com.demo.domain.Cookies;
import com.demo.service.CookiesService;

/**
 * Created by simpletour_java on 2015/6/4.
 */
public class CookiesServiceImpl implements CookiesService{
    private CookiesDao cookiesDao;

    public void setCookiesDao(CookiesDao cookiesDao) {  //这是application-service里面的依赖注入
       this.cookiesDao = cookiesDao;
    }

    @Override
    public Cookies findCookies(String id) {
        return cookiesDao.findCookies(id);
    }

    @Override
    public int insertCookies(Cookies cookies) {
        return cookiesDao.insertCookies(cookies);
    }

    @Override
    public int updateCookies(Cookies cookies) {
        return cookiesDao.updateCookies(cookies);
    }
}
