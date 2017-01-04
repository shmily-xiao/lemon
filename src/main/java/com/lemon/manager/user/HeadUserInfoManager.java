package com.lemon.manager.user;

import com.lemon.controller.BaseController;
import com.lemon.convertor.ConvertorResult;
import com.lemon.domain.impl.user.User;
import com.lemon.domain.impl.user.UserRecord;
import com.lemon.query.user.UserRecordQuery;
import com.lemon.service.IUserRecordService;
import com.lemon.service.IUserService;
import com.lemon.utils.BeanLocator;
import com.lemon.view.user.HeadUserInfoView;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Created by simpletour_Jenkin on 2017/1/4.
 */
@Component
public class HeadUserInfoManager extends BaseController{

    @Resource
    private IUserService userService;

    @Resource
    private IUserRecordService userRecordService;

    @Resource
    private BeanLocator beanLocator;

    /**
     * 公共的方法得到顶部用户的数据
     *
     * @see com.lemon.convertor.user.HeadUserInfoViewConvertor
     * @param request
     * @return
     */
    public HeadUserInfoView getUserView(HttpServletRequest request){
        Long userId = super.getUserInfoUserID(request);
        if (userId == null) return new HeadUserInfoView();

        Optional<User> userOptional = userService.find(userId);
        if (!userOptional.isPresent()) return new HeadUserInfoView();
        UserRecordQuery userRecordQuery = new UserRecordQuery();
        userRecordQuery.setUserId(userId);
        Optional<UserRecord> userRecordOptional = userRecordService.findOne(userRecordQuery);
        if (!userRecordOptional.isPresent()) return new HeadUserInfoView();
        // @see HeadUserInfoView_Convertor
        ConvertorResult convertorResult = (ConvertorResult) beanLocator.findBeanByName("HeadUserInfoView_Convertor");
        return (HeadUserInfoView) convertorResult.getResult(userOptional.get(),userRecordOptional.get());
    }
}
