package com.lemon.service.impl;

import com.alibaba.fastjson.JSON;
import com.lemon.dao.*;
import com.lemon.domain.impl.access.AccessControl;
import com.lemon.domain.impl.content.Content;
import com.lemon.domain.impl.content.ContentPlan;
import com.lemon.domain.impl.user.UserRecord;
import com.lemon.enums.UserType;
import com.lemon.exception.BaseSystemException;
import com.lemon.query.BaseQuery;
import com.lemon.query.content.LemonContentQuery;
import com.lemon.query.user.UserRecordQuery;
import com.lemon.service.IContentService;
import com.lemon.service.bo.lemonContent.LemonContentAddBo;
import com.lemon.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by simpletour_Jenkin on 2016/7/29.
 */
@Service
public class ContentServiceImpl extends BaseServiceImpl<Content,BaseQuery> implements IContentService {
    @Resource
    private IContentDao contentDao;

    @Resource
    private IContentPlanDao contentPlanDao;

    @Resource
    private IAccessControlDao accessControlDao;

    @Resource
    private IUserDao userDao;

    @Resource
    private IUserRecordDao userRecordDao;

    @Override
    protected IBaseDao<Content, BaseQuery> getBaseDao() {
        return contentDao;
    }

    /**
     * 添加用户的记录
     * 1.添加content表
     * 2.添加contentPlan表
     * 3.添加accessController表
     * 4.修改用户UserRecord表记录（更新成就值和达到范围之后更新用户等级）
     * @param lemonContentAddBo
     * @return
     */
    @Override
    @Transactional
    public Boolean addLemonContent(LemonContentAddBo lemonContentAddBo) {

        if (!validContentIsRight(lemonContentAddBo)) return false;
        // 添加content表
        Content content = this.initContent(lemonContentAddBo);
        contentDao.insert(content);

        // 添加contentPlan表
        ContentPlan contentPlan = this.initContentPlan(content,lemonContentAddBo);
        contentPlanDao.insert(contentPlan);

        // 添加accessController
        AccessControl control = this.initAccessControl(content,lemonContentAddBo);
        accessControlDao.insert(control);

        // 更新UserRecord表记录，第一条记录是在注册的时候就已经插入了
        UserRecordQuery recordQuery  = new UserRecordQuery();
        recordQuery.setUserId(content.getUserId());
        List<UserRecord> userRecords =  userRecordDao.findEntities(recordQuery);
        if (userRecords!=null && !userRecords.isEmpty()){
            UserRecord userRecord = userRecords.get(0);
            Long score = userRecord.getScore()+lemonContentAddBo.getContentsType().getScore();
            userRecord.setScore(score);
            userRecord.setType(UserType.findUserType(score));
            userRecordDao.update(userRecord);
        }

        return Boolean.TRUE;
    }

    @Override
    public List<Content> findContentsByUserId(Long userId) {
        LemonContentQuery query = new LemonContentQuery();
        query.setUserId(userId);
        List<Content>contents = contentDao.findEntities(query);
        // Collections.emptyList() this may be call UnsupportedOperationException
        return contents!=null&&!contents.isEmpty() ? contents : new ArrayList<>();
    }

    /**
     * 数据的校验
     * @param lemonContentAddBo
     * @return
     */
    private Boolean validContentIsRight(LemonContentAddBo lemonContentAddBo){
        if (lemonContentAddBo.getUserId()==null) return false;
        if (!StringUtils.notEmpty(lemonContentAddBo.getContent())) return false;
        if (!StringUtils.notEmpty(lemonContentAddBo.getTitle())) return false;
        if (lemonContentAddBo.getFinishedTime()==null) return false;
        return true;
    }

    /**
     * 添加content的内容
     * @param lemonContentAddBo
     * @return
     */
    private Content initContent(LemonContentAddBo lemonContentAddBo){
        Content content = new Content();
        content.setTitle(lemonContentAddBo.getTitle());
        content.setDescription(lemonContentAddBo.getContent());
        content.setImages(JSON.toJSONString(lemonContentAddBo.getImages()));
        content.setType(lemonContentAddBo.getContentsType());
        content.setUserId(lemonContentAddBo.getUserId());
        return content;
    }

    /**
     * 添加contentPlan表
     * @param content
     * @param bo
     * @return
     */
    private ContentPlan initContentPlan(Content content, LemonContentAddBo bo){
        if (content.getId()==null){
            throw new BaseSystemException("内容没有被正确的存储，请稍后重试！");
        }
        ContentPlan contentPlan = new ContentPlan();
        contentPlan.setContentId(content.getId());
        contentPlan.setExpectTime(bo.getFinishedTime());
        contentPlan.setRemind(bo.getNeedMessage());
        contentPlan.setUserId(bo.getUserId());
        contentPlan.setFinishedTime(null);
        return contentPlan;
    }

    /**
     * 添加accessController表
     * @param content
     * @param bo
     * @return
     */
    private AccessControl initAccessControl(Content content, LemonContentAddBo bo){
        AccessControl control = new AccessControl();
        control.setRowId(content.getId());
        control.setRowTable("content");
        control.setStrategy(bo.getStrategyType());
        return control;
    }

}
