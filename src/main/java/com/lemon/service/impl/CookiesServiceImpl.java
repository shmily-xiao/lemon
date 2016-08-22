package com.lemon.service.impl;

import com.lemon.dao.CookiesDao;
import com.lemon.domain.impl.Cookies;
import com.lemon.service.ICookiesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by simpletour_java on 2015/6/4.
 */
@Service
public class CookiesServiceImpl implements ICookiesService {

    @Resource
    private CookiesDao cookiesDao;

    @Override
    public Cookies findCookiesByUserId(Long id) {
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
