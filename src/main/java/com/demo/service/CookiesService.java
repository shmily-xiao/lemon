package com.demo.service;

import com.demo.domain.Cookies;

/**
 * Created by simpletour_java on 2015/6/4.
 */
public interface CookiesService {
    Cookies findCookies(String id);
    int insertCookies(Cookies cookies);
    int updateCookies(Cookies cookies);
}
