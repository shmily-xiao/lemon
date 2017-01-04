package com.lemon.convertor.user;

import com.lemon.convertor.ConvertorResult;
import com.lemon.domain.impl.user.User;
import com.lemon.domain.impl.user.UserRecord;
import com.lemon.view.user.HeadUserInfoView;
import org.springframework.stereotype.Component;

/**
 * Created by simpletour_Jenkin on 2017/1/4.
 */
@Component("HeadUserInfoView_Convertor")
public class HeadUserInfoViewConvertor implements ConvertorResult<HeadUserInfoView>{
    @Override
    public HeadUserInfoView getResult(Object... obj) {
        // 用的时候需要确保有那么多参数
        User user = (User) obj[0];
        UserRecord userRecord = (UserRecord) obj[1];

        HeadUserInfoView userInfoView = new HeadUserInfoView();
        // user
        userInfoView.setId(user.getId());
        userInfoView.setAvatar(user.getAvatar());
        userInfoView.setNickName(user.getNickName());
        // userRecord
        userInfoView.setScore(userRecord.getScore());
        userInfoView.setType(userRecord.getType().getValue());

        return userInfoView;
    }
}
