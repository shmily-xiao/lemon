drop database  if exists lemon;

create database lemon;
use lemon;

-- 用户的基本信息表
create table u_user(
    id BIGINT AUTO_INCREMENT primary key,
        created_time TIMESTAMP,
        avatar VARCHAR(255),
        gender VARCHAR(255),
        account  VARCHAR(255), -- 2016-8-11 用户在我们系统的唯一账号
        name VARCHAR(255),
        nick_name VARCHAR(255),
        birthday DATE,
        -- 2016-8-11 删除
       --  mobile VARCHAR(255),
       -- qq_no VARCHAR(255),
        email VARCHAR(255),
        profile VARCHAR(255),
        password VARCHAR(255),
        salt VARCHAR(255),
        status VARCHAR(255), -- 用户是否可用
        -- type VARCHAR(255), -- 用户的等级   2016-8-16 注释
        -- score BIGINT,    2016-8-16 注释
        -- zone_status VARCHAR(255),   2016-8-16 注释
        signup_type  VARCHAR(255),  -- 用户的注册类型
        -- sms_count  BIGINT, --   2016-8-11 添加用户购买短信的服务，初期可以免费，但是以后就不能了    2016-8-16 注释
        modify_available BOOLEAN -- 2016-8-11 是否可以更改系统的账号，默认有一次的
);

-- 2016-8-16  用户的业务数据表
create table user_record(
        id BIGINT AUTO_INCREMENT primary key,
        created_time TIMESTAMP,
        type VARCHAR(255), -- 用户的等级
        score BIGINT,  -- 用户的成就值
        -- zone_status VARCHAR(255), -- 空间的开发状态
        sms_count  BIGINT, --  添加用户购买短信的服务，初期可以免费，但是以后就不能了
        user_id  BIGINT,
 access_control_id BIGINT  -- 用户对好友的公开策略
);


-- 2016-8-11 用来存放用户的登录信息，为了实现多账号登录
create table user_account(
 id BIGINT AUTO_INCREMENT primary key,
     created_time TIMESTAMP,
        user_id BIGINT,
        account  VARCHAR(255),  -- 存放用户登录的账号信息
        type VARCHAR(255)  -- 账号信息的类型
);

-- 2016-8-16 内容表（评价和发表的内容）
create table content(
    id BIGINT AUTO_INCREMENT primary key,
         created_time TIMESTAMP,
        -- finished BOOLEAN,  2016-8-16 转移到  content_plan 中
        title VARCHAR(255), -- 2016-8-16 更改名字原来是name
        description VARCHAR(1000),
        images VARCHAR(1000), -- 2016-8-11 添加字段，用于存放用户上传的照片，为JSON字符串
        -- expect_time TIMESTAMP,    2016-8-16 转移到  content_plan 中
        -- finished_time TIMESTAMP,   2016-8-16 转移到  content_plan 中
        type VARCHAR(100),  -- 2016-8-11 事件的等级，梦想、琐事、抒情
       --  remind BOOLEAN,  -- 2016-8-11添加字段，用于表示是否用短信提醒用户  ，2016-8-16 转移到  content_plan 中
        user_id BIGINT,
        -- is_public BOOLEAN，2016-8-16 删除
        del  BOOLEAN  -- 2016-8-16 此记录是否删除
);

-- 2016-8-16  内容的计划表（时间安排）
create table  content_plan(
        id BIGINT AUTO_INCREMENT primary key,
        created_time TIMESTAMP,
        content_id BIGINT,
        finished BOOLEAN,
        expect_time TIMESTAMP,
        finished_time TIMESTAMP,
        remind BOOLEAN,
        user_id BIGINT
);
-- 2016-8-16  权限访问控制（用户的和内容的）
create table access_control(
         id BIGINT AUTO_INCREMENT primary key,
        created_time TIMESTAMP,
 row_table VARCHAR(255), -- 标示是内容的还是用户的
        row_id BIGINT,  -- 存放用户的id，或者是content的id
        strategy  VARCHAR(255) -- 公开，对好友可见，不可见，部分好友不可见
);

-- 微信用户表
create table wechat_user(
    id BIGINT AUTO_INCREMENT primary key,
        created_time TIMESTAMP,
        mobile VARCHAR(255),
        open_id VARCHAR(255),
        referrer VARCHAR(255),
        referrer_type VARCHAR(255),
        status VARCHAR(255),
        user_id BIGINT
);

-- 短信发送记录表
create table msm_sendlog(
    id BIGINT AUTO_INCREMENT primary key,
        created_time TIMESTAMP,
        auth_code VARCHAR(255),
        mobile VARCHAR(255),
        reason VARCHAR(255),
        status VARCHAR(255)
);

-- 好友关系表和好友权限表
create table friendship(
        id BIGINT AUTO_INCREMENT primary key,
        created_time TIMESTAMP,
        user_id BIGINT,
        friend_id BIGINT,
        type VARCHAR(255), -- 朋友的类型，普通或者是特别关注
       -- grouping VARCHAR(255) --转移到 friend_group
friend_group_id BIGINT,  -- 关联的分组信息
access_control_id BIGINT  -- 用户这条信息对好友的公开策略
);

-- 2016-8-16 用户的分组
create table friend_group(
id BIGINT AUTO_INCREMENT primary key,
       created_time TIMESTAMP,
user_id BIGINT,
group_name VARCHAR(255) -- 分组
);

-- cookies 存放，用于自动登录
create table cookies(
           user_id BIGINT ,
           session_id VARCHAR(255),
            login_time TIMESTAMP,
            life_time int
);

-- 用户的交互表，
create table  interaction(
       id BIGINT AUTO_INCREMENT primary key,
       created_time TIMESTAMP,
       user_id  BIGINT,
content_id BIGINT,
action VARCHAR(50) -- 点赞，收藏
);