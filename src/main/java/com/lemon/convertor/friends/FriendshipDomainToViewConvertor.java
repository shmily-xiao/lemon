package com.lemon.convertor.friends;

import com.lemon.convertor.ConvertorResult;
import com.lemon.domain.impl.friend.Friendship;
import com.lemon.domain.impl.user.User;
import com.lemon.utils.DateUtils;
import com.lemon.utils.StringUtils;
import com.lemon.view.friends.FriendElementView;
import org.springframework.stereotype.Component;

/**
 * Created by simpletour_Jenkin on 2017/1/19.
 */
@Component("FriendshipDomainToView_Convertor")
public class FriendshipDomainToViewConvertor implements ConvertorResult<FriendElementView> {
    @Override
    public FriendElementView getResult(Object... obj) {
        Friendship friendship = (Friendship)obj[0];
        User user = (User)obj[1];
        FriendElementView friendElementView = new FriendElementView();

        friendElementView.setAvator(user.getAvatar());
        if (user.getBirthday()!=null) {
            friendElementView.setBirthday(DateUtils.yearMonthDay(user.getBirthday().atStartOfDay()));
        }
        friendElementView.setId(friendship.getId());
        friendElementView.setNickName(user.getNickName());
        friendElementView.setProfile(StringUtils.notEmpty(user.getProfile())?user.getProfile():"ta很懒没有填写任何内容。");
        friendElementView.setSex(user.getGender().getValue());

        return friendElementView;
    }
}
