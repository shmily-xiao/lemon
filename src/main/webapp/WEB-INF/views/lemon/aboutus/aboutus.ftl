<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>首页</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/js/webuploader/webuploader.css"/>
    <!-- 可选的Bootstrap主题文件（一般不使用） -->
    <link src="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></link>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap-datetimepicker.zh-CN.js"></script>
    <style>
        img{
            width: 100%;
            height: 200px;
        }
        .title{
            text-align: center;
            font-size: 36px;
            font-family: Algerian;
            margin-top: 89px;
            margin-bottom: 20px;
        }
       .introduce {
           text-align: justify;
           text-indent:30px;
           line-height: 3;
           letter-spacing: 2px;
           border: 2px solid #d7d7d7;
           border-radius: 4px;
           padding: 10px;
       }

    </style>
</head>
<body ng-app="myApp" ng-controller="lemonAboutUs">

<!--header-->
<#assign currentPage = "others">
<#include "/lemon/common/head.ftl">
<body>
<div class="container">
    <!--<div class="col-md-12 column">-->
    <div class="row">
        <div class="col-md-2 column">
        </div>
        <div class="col-md-8 column">
            <div class="col-md-12 column">
                <div class="title">
                    <p>网站介绍</p>
                </div>
            </div>
        </div>
        <div class="col-md-2 column">
        </div>
    </div>
    <div class="row">
        <div class="col-md-2 column">
        </div>
        <div class="col-md-8 column">
            <div class="col-md-12 column">
                <div class="introduce">
                    <h3>一个开发者的独白</h3>
                    <h4>第一阶段</h4>
                    <p>
                        本网站最初的想法是记录生活中点点滴滴，可以是自己想做的事情，可以是琐碎的事情，也可以是一些煽情的话语。
                        当然在你没有好友的时候，这更像是一个树洞。一个属于自己的小秘密，你可以在这里畅所欲言（如果你要发车，我会首先去验证其真实性。
                        如果发现是一张假的车票，我会将其删掉），我们不会将这些秘密告诉别人。即使你有了好友还是可以在“个人中心”里面设置权限。
                        我们希望你有自己的小秘密，也希望你有自己的朋友圈。
                    </p>
                    <p>
                        在我原来的设想中"想做的事情"是可以有短信提醒功能的，这就像是一个你自己的小管家或者是一个监督者的角色。他的任务就是告诉你自己，
                        你的目标是否在原来设想的时间之内完成了。不过，这个功能还在努力的开发中，还有很多困难需要去克服，比如申请短信模板，比如购买短信条数等等。
                        同时以后我还会开通qq快速登录，那样就不用再输入繁琐的密码。以后还会开拓微信公众号。希望给你们带来方便和快乐。
                    </p>
                    <p>
                        我努力给你们呈现一个友好的UI，但是从原型到设计到页面再到功能再到实现，无奈就是有几个程序猿哥哥（主要是我一个人），而且不怎么会处理前端问题，而这恰恰
                        是你们能够直观看到的。如果有显示问题请你们见谅，同时告诉我，我的个人邮箱是wangzaijun1234@126.com。“个人中心”里面有一个反馈功能，
                        但是一直还处于调试阶段，所以有任何你们觉得可以提升的地方还是发邮件给我吧。
                    </p>
                    <p>
                        再一次感谢你能来到这里，我希望你能够继续用下去，并告诉我的不足。
                    </p>
                    <h3>感谢</h3>
                    <p>
                        虽然大部分都是我一个人在折腾，但是还是感谢小伙伴的帮助。名单如下：
                    </p>
                    <p>
                        一个写代码的... 、 small_Alex 小刚 、 roddy 、 啊...木有芒果哒 、 Jason Yang 、 FernAbby 、 嗯
                    </p>
                    <h4>第二阶段</h4>
                    <p>
                        阶段性的更新和调整：
                        1. 反馈邮箱的功能得到完善，可以正常的发送反馈信息了。
                        2. 去掉了原来发送短信的想法，改用邮件提醒。通知会在到期时间的前一天发送邮件到您个人中心填写的邮箱中。
                    </p>
                </div>
            </div>
        </div>
        <div class="col-md-2 column">
        </div>
    </div>

</div>
<script type="text/javascript" src="/js/webuploader/webuploader.html5only.min.js"></script>
</body>
</html>