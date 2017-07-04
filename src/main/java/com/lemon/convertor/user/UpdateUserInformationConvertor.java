package com.lemon.convertor.user;

import com.lemon.convertor.ConvertorResult;
import com.lemon.domain.impl.user.User;
import com.lemon.form.user.UserInformationForm;
import com.lemon.utils.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Created by simpletour_Jenkin on 2017/1/25.
 */
@Component("UpdateUserInformation_Convertor")
public class UpdateUserInformationConvertor implements ConvertorResult<User>{
    @Override
    public User getResult(Object... obj) {
        User user = (User) obj[0];
        UserInformationForm form = (UserInformationForm) obj[1];

        if (StringUtils.notEmpty(form.getAvatar())){
            user.setAvatar(form.getAvatar());
        }
        if (StringUtils.notEmpty(form.getEmail())){
            user.setEmail(form.getEmail());
        }
        if (StringUtils.notEmpty(form.getName())){
            user.setName(StringUtils.replaceHtmlSpecialsToString(form.getName()));
        }
        if (StringUtils.notEmpty(form.getNickName())){
            user.setNickName(StringUtils.replaceHtmlSpecialsToString(form.getNickName()));
        }
        if (StringUtils.notEmpty(form.getProfile())){
            user.setProfile(StringUtils.replaceHtmlSpecialsToString(form.getProfile()));
        }
        if (form.getBirthday() != null){
            user.setBirthday(form.getBirthday());
        }
        if (form.getGender() != null){
            user.setGender(form.getGender());
        }
        return user;
    }
}
