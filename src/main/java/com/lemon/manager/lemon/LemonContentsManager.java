package com.lemon.manager.lemon;

import com.lemon.convertor.ConvertorResult;
import com.lemon.domain.impl.access.AccessControl;
import com.lemon.domain.impl.content.Content;
import com.lemon.domain.impl.friend.Friendship;
import com.lemon.enums.ContentType;
import com.lemon.enums.StrategyType;
import com.lemon.form.lemonContents.LemonContentsAddForm;
import com.lemon.framework.enumwrapper.EnumWrapper;
import com.lemon.framework.enumwrapper.Option;
import com.lemon.query.access.AccessControlQuery;
import com.lemon.query.friendship.FriendshipQuery;
import com.lemon.query.lemon.LemonContentQuery;
import com.lemon.service.IAccessControlService;
import com.lemon.service.IContentService;
import com.lemon.service.IFriendshipService;
import com.lemon.service.IInteractionService;
import com.lemon.service.bo.lemonContent.LemonContentAddBo;
import com.lemon.utils.BeanLocator;
import com.lemon.view.lemon.add.LemonAddView;
import com.lemon.view.lemon.contents.LemonContentListViews;
import com.lemon.view.lemon.contents.LemonContentsElementView;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by simpletour_Jenkin on 2017/1/5.
 */
@Component
public class LemonContentsManager {

    @Resource
    private IContentService contentService;

    @Resource
    private IInteractionService interactionService;

    @Resource
    private IFriendshipService friendshipService;

    @Resource
    private IAccessControlService accessControlService;

    /**
     * 初始化添加页面的数据
     * @return
     */
    public LemonAddView initAddView(){

        LemonAddView addView = new LemonAddView();
        List<Option> contentsTypes = EnumWrapper.of(ContentType.class).getAllOptionsList();
        List<Option> strategyTypes = EnumWrapper.of(StrategyType.class).getAllOptionsList();

        addView.setContentsType(contentsTypes);
        addView.setStrategyTypes(strategyTypes);
        return addView;
    }

    /**
     * 添加lemonContent内容
     * @param form
     * @param userId
     * @return
     */
    public Boolean addContent(LemonContentsAddForm form, Long userId){
        ConvertorResult convertorResult = (ConvertorResult) BeanLocator.findBeanByName("ContentFormToBo_Convertor");
        LemonContentAddBo lemonContentAddBo = (LemonContentAddBo) convertorResult.getResult(form, userId);
        return  contentService.addLemonContent(lemonContentAddBo);
    }

    /**
     * 根据用户的id来查找与用户相关的好友的所有的（包括自己的）发布内容
     *
     * 1.查找用户的的所有好友
     * 2.查找用户和所有好友共同发布的内容（按照策略过滤）
     * 3.查找Interaction表中的数据，发现那些收藏和点赞的
     *
     * @param userId
     * @return
     */
    public List<LemonContentsElementView> findLemonContentsWithFriend(Long userId){
        // 查找所有的好友
        FriendshipQuery friendshipQuery = new FriendshipQuery();
        friendshipQuery.setUserId(userId);
        List<Friendship> friendships = friendshipService.findList(friendshipQuery);

        // 得到好友的id和自己的id
        Set<Long> friendIdSets = new HashSet<>();
        friendships.forEach(friendship -> friendIdSets.add(friendship.getFriendId()));

        // 查找所有的发布内容
        LemonContentQuery query = new LemonContentQuery();
        query.setUserId(userId);
        List<Content> contentList = contentService.findList(query);
        List<Content> friendContents = friendIdSets.stream()
                .map(set -> new LemonContentQuery(set))
                .flatMap(lemonContentQuery -> contentService.findList(lemonContentQuery).stream())
                .filter(content -> !content.getDel())
                .filter(content -> {
                    AccessControlQuery accessControlQuery = new AccessControlQuery();
                    accessControlQuery.setRowTable("content");
                    accessControlQuery.setRowId(content.getId());
                    Optional<AccessControl> accessControl = accessControlService.findOne(accessControlQuery);
                    if (!accessControl.isPresent()) return false;
                    return !accessControl.get().getStrategy().equals(StrategyType.PRIVATE);
                })
                .collect(Collectors.toList());
        contentList.addAll(friendContents);


        return null;
    }

    /**
     * 登录用户的所有的发布内容，只有登录用户自己的
     * 1.查找用户的发布的所有内容
     *
     * @param userId
     * @return
     */
    public List<LemonContentsElementView> findLemonContentsHimself(Long userId){


        return null;
    }



    public static void main(String[] args) {
        String date = "2016-04-11 10:00";
        System.out.println(LocalDateTime.parse(date));
        System.out.println(LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE));

    }
}
