package com.lemon.service.impl;

import com.lemon.dao.IBaseDao;
import com.lemon.dao.IContentDao;
import com.lemon.domain.impl.content.Content;
import com.lemon.query.BaseQuery;
import com.lemon.service.IContentService;
import com.lemon.service.bo.lemonContent.LemonContentAddBo;
import com.lemon.utils.StringUtils;
import javassist.bytecode.annotation.BooleanMemberValue;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by simpletour_Jenkin on 2016/7/29.
 */
@Service
public class ContentServiceImpl extends BaseServiceImpl<Content,BaseQuery> implements IContentService {
    @Resource
    private IContentDao lemonDao;

    @Override
    protected IBaseDao<Content, BaseQuery> getBaseDao() {
        return lemonDao;
    }

    @Override
    @Transactional
    public Boolean addLemonContent(LemonContentAddBo lemonContentAddBo) {

        if (!validContentIsRight(lemonContentAddBo)) return false;
        // 添加content表

        return null;
    }

    private Boolean validContentIsRight(LemonContentAddBo lemonContentAddBo){
        if (lemonContentAddBo.getUserId()==null) return false;
        if (!StringUtils.notEmpty(lemonContentAddBo.getContent())) return false;
        if (!StringUtils.notEmpty(lemonContentAddBo.getTitle())) return false;
        if (lemonContentAddBo.getFinishedTime()==null) return false;
        return true;
    }
}
