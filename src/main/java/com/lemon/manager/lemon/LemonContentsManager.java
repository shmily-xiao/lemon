package com.lemon.manager.lemon;

import com.lemon.convertor.ConvertorResult;
import com.lemon.convertor.content.ContentDomainToFriendHomeViewConvertor;
import com.lemon.domain.impl.access.AccessControl;
import com.lemon.domain.impl.content.Content;
import com.lemon.domain.impl.content.ContentPlan;
import com.lemon.domain.impl.content.Interaction;
import com.lemon.domain.impl.friend.Friendship;
import com.lemon.domain.impl.user.User;
import com.lemon.domain.impl.user.UserRecord;
import com.lemon.enums.ContentType;
import com.lemon.enums.StrategyType;
import com.lemon.form.lemonContents.LemonContentsAddForm;
import com.lemon.framework.enumwrapper.EnumWrapper;
import com.lemon.framework.enumwrapper.Option;
import com.lemon.query.access.AccessControlQuery;
import com.lemon.query.content.LemonContentQuery;
import com.lemon.query.friendship.FriendshipQuery;
import com.lemon.service.*;
import com.lemon.service.bo.lemonContent.LemonContentAddBo;
import com.lemon.utils.BeanLocator;
import com.lemon.view.lemon.add.LemonAddView;
import com.lemon.view.lemon.contents.LemonContentsElementView;
import com.lemon.view.lemon.contents.PersonalCenterContentsView;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
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
    private IContentPlanService contentPlanService;

    @Resource
    private IFriendshipService friendshipService;

    @Resource
    private IAccessControlService accessControlService;

    @Resource
    private IUserService userService;

    @Resource
    private IUserRecordService userRecordService;

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
     *
     * @param form
     * @param userId
     *
     * @see com.lemon.convertor.content.ContentFormToBoConvertor
     *
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
     *     策略为：A.自己（用户）查看所有的内容
     *            B.查看好友内容的时候，需要排除掉好友设置为私有的内容
     * 3.查找Interaction表中的数据，发现那些收藏和点赞的
     *
     * @see ContentDomainToFriendHomeViewConvertor
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
        List<Content> contentList = contentService.findContentsByUserId(userId);
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

        // 组装
        Optional<User> userOptional = userService.find(userId);
        return contentList.stream()
                .map(content -> {
                    Optional<ContentPlan> contentPlan = contentPlanService.findByContentId(content.getId());
                    List<Interaction> interactions = interactionService.findByContentId(content.getId());
                    ConvertorResult convertorResult = (ConvertorResult)BeanLocator.findBeanByName("ContentDomainToFriendHomeView_Convertor");
                    return (LemonContentsElementView)convertorResult.getResult(content,contentPlan.get(),interactions,userOptional.get());
                })
                .collect(Collectors.toList());
    }

    /**
     * 登录用户的所有的发布内容，只有登录用户自己的
     * 1.查找用户的发布的所有内容
     *
     * @see com.lemon.convertor.content.ContentDomainToPersonalCenterViewConvertor
     *
     * @param userId
     * @return
     */
    public List<PersonalCenterContentsView> findLemonContentsHimself(Long userId){
        List<Content> contents = contentService.findContentsByUserId(userId);
        return contents.stream()
                .map(content -> {
                    Optional<ContentPlan> contentPlan = contentPlanService.findByContentId(content.getId());
                    ConvertorResult convertorResult = (ConvertorResult) BeanLocator.findBeanByName("ContentDomainToPersonalCenterView_Convertor");
                    return (PersonalCenterContentsView)convertorResult.getResult(content,contentPlan.get());
                }).collect(Collectors.toList());
    }


    /**
     * 只看好友的发布的内容
     * 1.当查看好友的内容的时候有一个限制，不能查看到好友设为私有的内容
     * 2.不能看到好友对其设置了的特定权限的内容，比如：好友主动屏蔽了当前用户，指定其不可见
     *
     * content ：的策略是对单个内容而言的，拥有最小内容的管理权限
     * friendship：的策略是针对好友的，比如某些内容屏蔽某一位好友
     * userRecord:  的策略是对用户整个内容而言的，如果设为私有，相当于空间被锁定，除了自己任何人不能看
     *
     * @param friendId
     * @param currentUserId
     * @return
     */
    public List<PersonalCenterContentsView> lookFriendContent(Long friendId,Long currentUserId){
        if (friendId == null || currentUserId == null) return Collections.emptyList();

        // 如果用户将自己空间锁定了看不到任何内容
        if (userRecordService.isUserPrivateHisAllContents(friendId)){
            return Collections.emptyList();
        }

        FriendshipQuery query = new FriendshipQuery();
        query.setUserId(friendId);
        query.setFriendId(currentUserId);
        Optional<Friendship> friendship = friendshipService.findOne(query);
        // 如果本身不是好友,最多可以看到两条公开的数据
        if (!friendship.isPresent()){
            return this.lookStrangerContents(friendId);
        }
        // 如果是好友的时候
        Long accessControlId = friendship.get().getAccessControlId();
        Optional<AccessControl> accessControl = accessControlService.find(accessControlId);
        // 如果权限记录中没有，说明是数据出了问题
        if (!accessControl.isPresent()){
            return Collections.emptyList();
        }
        // 如果用户对当前用户是隐藏了的（对其设为私有），也是看不到任何内容的
        if (StrategyType.PRIVATE.equals(accessControl.get().getStrategy())){
            return Collections.emptyList();
        }

        // 如果是好友，看不到好友将内容设为私密的记录且看不到，其他的可以看到
        return this.lookStrangerContents(friendId);

    }

    /**
     * 查看陌生人的内容只能看到最多两条公开的数据
     *
     * @param strangerUserId
     * @return
     */
    private List<PersonalCenterContentsView> lookStrangerContents(Long strangerUserId){
        List<PersonalCenterContentsView> lemonContentsHimself = this.findLemonContentsHimself(strangerUserId);
        return lemonContentsHimself.stream()
                .filter(view -> {
                    // 过滤，只保留公开的内容
                    AccessControlQuery query = new AccessControlQuery();
                    query.setRowId(view.getId());
                    query.setRowTable("content");
                    query.setStrategy(StrategyType.PUBLIC);
                    return accessControlService.findOne(query).isPresent();
                })
                .limit(2)
                .collect(Collectors.toList());
    }

    /**
     * 如果是好友，看不到好友将内容设为私密的记录且看不到，其他的可以看到
     * @param friendUserId
     * @return
     */
    private List<PersonalCenterContentsView> lookFriendContents(Long friendUserId){
        List<PersonalCenterContentsView> lemonContentsFriend = this.findLemonContentsHimself(friendUserId);
        return lemonContentsFriend.stream()
                .filter(view -> {
                    // 过滤掉私有的数据内容
                    AccessControlQuery query = new AccessControlQuery();
                    query.setRowId(view.getId());
                    query.setRowTable("content");
                    query.setStrategy(StrategyType.PRIVATE);
                    return !accessControlService.findOne(query).isPresent();
                })
                .collect(Collectors.toList());
    }







    public static void main(String[] args) {
        String date = "2016-04-11 10:00";
        System.out.println(LocalDateTime.parse(date));
        System.out.println(LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE));

    }
}
