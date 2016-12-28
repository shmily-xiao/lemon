<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <link rel="shortcut icon" href="/img/gift.jpg" type="image/png">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <script src="/js/jquery.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <style>
        body, div, ul, li{margin:0; padding:0;font-style: normal;font:12px/22px "\5B8B\4F53",Arial, Helvetica, sans-serif}
        /* \5B8B\4F53 代表 宋体，更多中文字体转 Unicode http://www.divcss5.com/jiqiao/j325.shtml */
        ol, ul ,li{list-style:none}
        body{color:#000000;background:#FFF}
        .clear{clear:both;height:1px;width:100%; overflow:hidden; margin-top:-1px}
        a{color:#000000;text-decoration:none}
        a:hover{color:#BA2636}
        /*.nas{*/
            /*width: 70%;*/
            /*height: auto;*/
            /*border: 1px solid #000000;*/
        /*}*/
        ul.nav{ width:100%; height:60px; background:#50AAE7;margin:0 auto}
        /*ul.nav li{display:inline; height:60px}*/
        ul.nav li{display:inline; padding:0 20px; height:60px; line-height:60px;
            color:#202020; font-family:"\5FAE\8F6F\96C5\9ED1"; font-size:16px}
        ul.nav li a:hover{background: #6789bb  }
        /*.avatar{margin-left: 17px}*/
        img{
            border: 0;
            vertical-align:middle;
            width:60px;
            height:60px;
            /*position: relative;*/
            /*margin-top: -7px;*/
        }
        .caret{
            margin-left: 7px;
        }
        .achievement_value{
            margin-left: 12rem;
        }
        .dropdown-menu{
            background-color:#50AAE7 ;
            min-width: 104px;
        }
        .nav .open>a, .nav .open>a:hover, .nav .open>a:focus {
            /*background-color: #428bca;*/
            border-color: #428bca;
        }
        #user_avatar{
            margin-left: 4rem;
            margin-top: 2rem;
            -webkit-border-radius: 50em;
            -moz-border-radius: 50em;
            border-radius: 50em;
        }
        .comment{
            margin: auto;
            border: 1px solid white;
            width: 80%;
            margin-top: 4rem;
        }
        .border{
            width: 80%;
        }
        .user_comment{
            border: 1px solid rgba(120, 120, 120, 0.36);
            height: auto;
            margin-left: 14rem;
            margin-top: -5rem;
        }
    </style>
</head>
<body>
<div class="nas">
    <ul class="nav nav-tabs">
        <li class="avatar"><img src="/img/user64.ico"></li>
        <li class="right_value"><a href="http://www.divcss5.com/">首页</a></li>
        <li class="right_value"><a href="http://www.divcss5.com/html/">个人中心</a></li>
        <li class="right_value"><a href="http://www.divcss5.com/rumen/">好友</a></li>
        <li class="dropdown right_value">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                更多<span class="caret"></span>
            </a>
            <ul class="dropdown-menu">
                <li><a href="">学生宿舍</a></li>
                <li><a href="">寝室列表</a></li>
                <li><a href="">学生列表</a></li>
            </ul>
        </li>

        <li class="achievement_value right_value">成就值：</li>
        <li class="grade right_value">等级：</li>
        <li class="add_content right_value">添加</li>
        <li class="right_value">签到</li>
    </ul>
    <div class="content">
        <div class="comment">
            <img id="user_avatar" src="/img/user.jpg">
            <div class="border">
                <div class="user_comment">
                    <div class="userName">
                        用户名
                    </div>
                    <div class="createTime">
                        创建时间：2016-8-9
                    </div>
                    <div class="endTime">
                        倒计时:123天
                    </div>
                    <div class="comment_content">
                        进行自定义只需点击几下即可为模板提供全新的外观。在功能区的“设计”选项卡上，检查主题、颜色和字体库以预览各种选择的不同外观。然后单击即可应用您喜欢的内容。
                    </div>
                    <div class="operation">
                        <div class="click">点赞(10)</div>
                        <div class="discuss">评论(11)</div>
                        <div class="Collection">收藏(12)</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>