package com.lemon.controller.interaction;

import com.lemon.controller.BaseController;
import com.lemon.domain.impl.content.Content;
import com.lemon.domain.impl.content.Interaction;
import com.lemon.enums.InteractionType;
import com.lemon.form.AjaxResponse;
import com.lemon.pojo.constants.LemonConstants;
import com.lemon.query.content.InteractionQuery;
import com.lemon.service.IContentService;
import com.lemon.service.IInteractionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Created by simpletour_Jenkin on 2017/1/17.
 */
@Controller
public class InteractionController extends BaseController{

    @Resource
    private IInteractionService interactionService;

    @Resource
    private IContentService contentService;

    /**
     *
     * @param type      用户的行为类型 点赞|收藏
     * @param contentId 内容的id
     * @param status    是喜欢（true）还是取消喜欢（false）
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/lemon/lemons/{type:COLLECT|LIKE}/{contentId:\\d+}/{status:true|false}",method = RequestMethod.POST)
    public AjaxResponse interaction(@PathVariable InteractionType type, @PathVariable Long contentId, @PathVariable Boolean status, HttpServletRequest request){
        if (!super.isUserLoginIn(request)){
            return AjaxResponse.fail().msg("用户没有登录").url(LemonConstants.LOGIN_URL);
        }
        InteractionQuery query = new InteractionQuery();
        Long userId = super.getUserInfoUserID(request);
        query.setContentId(contentId);
        query.setUserId(userId);
        query.setAction(type.name());
        Optional<Interaction> interactionOld = interactionService.findOne(query);
        // 说明已经点赞或者是收藏过了
        if (interactionOld.isPresent()){
            // 如果是true说明是再一次收藏或者是再一次喜欢
            if (status){
                return AjaxResponse.fail().msg("已经"+type.getValue()+"过了，无需再一次"+type.getValue());
            }else {
                // 说明是取消收藏或者是取消喜欢，于是删除记录
                interactionService.delete(interactionOld.get().getId());
                return AjaxResponse.ok().msg(type.getValue()+"取消成功");
            }
        }else {
            // 没有记录说明是第一次操作，没有收藏或者是没有喜欢
            if (status){
                Interaction interaction = new Interaction();
                interaction.setContentId(contentId);
                interaction.setUserId(userId);
                interaction.setAction(type);
                Optional<Content> content = contentService.find(contentId);
                if (content.isPresent()){
                    interactionService.insert(interaction);
                    return AjaxResponse.ok().msg(type.getValue()+"成功");
                }else {
                    return AjaxResponse.fail().msg(type.getValue()+"失败，内容不存在");
                }
            }else {
                return AjaxResponse.fail().msg("您还未"+type.getValue()+"过此内容");
            }
        }
    }
}
