

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">
    <meta name="baidu-site-verification" content="H53YfCY2d4" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <#--<meta name="description" content="这是描述，这是描述，这是描述">-->
    <#--<meta name="keywords" content="关键字，关键字，关键字">-->


    <title>
        从自己想做的第一件事开始
    </title>
    <link rel="shortcut icon" href="/img/gift.jpg" type="image/png">
    <!--  css -->

    <link href="/css/bootstrap.2183d05f5a0a.css" rel="stylesheet" />
    <link href="/css/index.c4dfc26089f4.css" rel="stylesheet"/>
    <link href="/css/font-awesome.8e12157da5fc.css" rel="stylesheet"/>
    <link href="/css/intlTelInput.1c5f40243067.css" rel="stylesheet" />
    <link href="/css/intlTelInput-reload.32b2d0bf5fb4.css" rel="stylesheet" />
    <link rel="stylesheet" href="/css/footer.a18761b4dee9.css">

    <!--  css -->
    <style>
        html,body{
         width: 100%;
            height: 100%;
        }
        .row.showcase-number{
            position: absolute;
            bottom: 36px;
            left: 0;
            right: 0;
        }
        .row {
            margin-right: 0px;
        }
    </style>

</head>

<body>
<!-- content -->



<div class="index-head" style="height: 100%;background-image:url('/img/ship.jpg')" ng-app="myApp" ng-controller="indexHome">
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="collapse navbar-collapse" id="index-nav-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <#--<li style="margin-top: 15px;"><a href="/lemon/account/login" style="color: #202020;">登录</a></li>-->
                    <#--<li>-->
                        <#--<a href="/lemon/account/register">-->
                            <#--<button class="btn navbar-btn btn-primary " style="color: #202020;border: 1px solid #202020;">注册</button>-->
                        <#--</a>-->
                    <#--<li>-->
                    <li>
                        <a href="/lemon/aboutus">
                            <button class="btn navbar-btn btn-primary " style="color: #202020;border: 1px solid #202020;">网站介绍</button>
                        </a>
                    <li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container" id="home-intro">

        <!--左侧介绍与右侧登录框开始-->
        <div class="row hidden-xs">
            <div class="col-md-4 index-intro-slogan" style="margin-top: 30%;">
                <h1 style="color: rgba(53, 53, 50, 0.74);font-size: x-large;">
                    我们一定要有梦想
                </h1>
                <h1 style="color: rgba(53, 53, 50, 0.74);font-size: x-large;">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;万一实现了呢！
                </h1>
            </div>
            <div class="col-md-3 col-md-offset-5" id="home-intro-log">
                <div class="form-group" id="home-switcher-wrap">
                    <ul class="login-switcher">
                        <li target='#home-login-form'
                            ng-class="[{active:isLoginForm}]"
                            ng-click="loginForm($event)">
                            <a href="javascript:;">登录</a>
                        </li>
                        <li target='#home-signup-form'
                            ng-class="[{active:isSignupForm}]"
                            ng-click="signupForm($event)">
                            <a href="javascript:;">注册</a>
                        </li>
                    </ul>
                </div>

                <div class="home-forms" data-login="False">
                    <form id="home-login-form" method="post" class="home-login-form home-form-content" name="login" action="/lemon/account/login" ng-style="{'display':loginStyle}">

                        <div class="col-md-4 col-sm-6 home-login-form-icon">
                            <img src="/img/user48.ico" style="margin-top: 4px; margin-left: 12px;">
                        </div>
                        <div class="col-md-8 col-sm-6 home-login-form-desc">
                            <h4>安全登录</h4>
                            <p>我们保障您的安全.</p>
                        </div>
                        <input class="form-control"
                               id="login_account"
                               name="username"
                               type="text"
                               ng-pattern="/^1[3|4|5|7|8]\d{9}$/"
                               ng-trim='false'
                               ng-model="loginMobile"
                               ng-minlength=11
                               placeholder="请输入您的手机号码"/>
                        <label></label>
                        <input id="login_password"
                               name="password"
                               type="password"
                               class="form-control"
                               ng-trim='false'
                               ng-model="loginPassword"
                               ng-minlength=8
                               ng-maxlength=100
                               placeholder="请输入您的密码">
                        <label></label>
                        <button ng-click="loginSubmit($event)" type="button" class="btn btn-primary btn-lg btn-block btn-primary home-log-signup-action">登录</button>
                        <div class="home-login-wechat" data-expire_seconds=300>
                            <a class="btn btn-default" data-toggle="modal" data-target="#wechat-login-qr">
                                <img src="/img/qq16w.ico" style="margin-top: -3px; margin-right: 3px;"/>QQ登录
                            </a>
                        </div>

                        <a class="index-pwd-forget" href="/lemon/account/forget">忘记密码?</a>
                    </form>

                    <div class="modal fade" id="wechat-login-qr" tabindex="-1" role="dialog" aria-labelledby="requestlabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 class="modal-title" id="requestlabel">QQ登录</h4>
                                </div>
                                <div class="modal-body">
                                    <div class="_wx_">
                                        <img class="_wx_qrcode center-block" width="160" height="160">
                                        <p class="text-center"><i class="fa fa-wechat fa-2x"></i></p>
                                        <p class="text-center">使用您的微信app扫描二维码</p>
                                        <p class="text-center">
                                            <a class="label label-red _refresh_btn hidden">
                                                微信登录超时, 请刷新二维码
                                                <i class="fa fa-refresh"></i>
                                            </a>
                                        </p>
                                    </div>
                                </div>
                                <div class="modal-footer modal-dismiss-action">
                                    <a id="modal-dismiss-action-btn" class="center-block" data-dismiss="modal">
                                        <i class="fa fa-qrcode"></i>
                                        通过微信app扫描二维码
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--  -->


                    <form id="home-signup-form" class="home-signup-form home-form-content" name="signup" method="post" action="/lemon/account/register" ng-style="{'display':signupStyle}">
                        <input type='text' style='display:none' />
                        <input type='password' style='display:none' />
                        <input type='password' style='position:absolute; top:-2000px;'/>
                        <div class="form-group">
                            <input class="form-control"
                                   id="signup_account"
                                   type="tel"
                                   ng-pattern="/^1[3|4|5|7|8]\d{9}$/"
                                   ng-trim='false'
                                   ng-model="signupMobile"
                                   ng-minlength=11
                                   placeholder="请输入你的手机号">
                        </div>
                        <div class="form-group">
                            <input class="form-control"
                                   id="id_password"
                                   type="password"
                                   ng-trim='false'
                                   ng-model="signupPassword"
                                   ng-minlength=8
                                   ng-maxlength=100
                                   placeholder="请输入你的密码,最少8位"/>
                        </div>
                        <div class="form-group">
                            <input class="form-control"
                                   id="id_confirm_password"
                                   placeholder="再次确认你的密码"
                                   ng-trim='false'
                                   ng-model="signupConfirmPassword"
                                   ng-minlength=8
                                   ng-maxlength=100
                                   type="password" />
                        </div>
                        <div class="form-group">
                            <input id="id_agreement"
                                   ng-model="agreement"
                                   type="checkbox" />
                            我同意&nbsp;<a href="#">使用条款</a>&nbsp;和&nbsp;<a href="#">隐私条款</a>.
                        </div>
                        <button ng-click="signupSubmit($event)" type="button" class="btn btn-primary btn-lg btn-block btn-primary home-log-signup-action">创建新账号</button>
                    </form>
                </div>
                <!--右侧登录框开始-->
            </div>
            <!--右侧登录框结束-->
        </div>

        <div class="row index-quick-action visible-xs">
            <div class="col-md-12">
                <a href="/lemon/account/register" class="btn btn-lg center-block index-quick-signup">注册</a>
                <a href="/lemon/account/login" class="btn btn-lg center-block index-quick-login" style="border: 1px solid rgba(199, 191, 191, 0.56);color: #404040;">登录</a>
                <a href="/lemon/aboutus" class="btn btn-lg center-block index-quick-login" style="border: 1px solid rgba(199, 191, 191, 0.56);color: #404040;margin-top: 15px;">网站介绍</a>
            </div>
        </div>
    </div>
    <!--左侧介绍与右侧登录框结束-->

    <div class="row showcase-number">
        <div class="col-md-4 col-sm-4 col-xs-4">
            <p class="pull-right"><span id="showcase-number-profit"></span></p>
        </div>
        <div class="col-md-4 col-sm-4 col-xs-4">
            <p class="pull-right"><span id="showcase-number-profit"></span></p>
        </div>
        <div class="col-md-4 col-sm-4 col-xs-4">
            <p class="pull-right">作品来自shmily-xiao</p>
        </div>

    </div>
</div>
<script src="/js/angular.min.js"></script>
<script src="/js/jquery-3.1.0.min.js" charset="utf-8"></script>
<script src="/js/jquery.nicescroll.min.js"></script>
<script src="/js/md5.js"></script>
<script>
    function toQQLogin()
    {
        //以下为按钮点击事件的逻辑。注意这里要重新打开窗口
        //否则后面跳转到QQ登录，授权页面时会直接缩小当前浏览器的窗口，而不是打开新窗口
        var A=window.open("/lemon/account/qq/login","TencentLogin",
                "width=450,height=320,menubar=0,scrollbars=1,resizable=0,status=1,titlebar=0,toolbar=0,location=1");
    }
</script>
<script type="text/javascript">

    var app = angular.module('myApp', []);

    app.controller('indexHome',['$scope','$http',function($scope,$http) {
        $scope.isLoginForm=true;
        $scope.isSignupForm=false;
        $scope.loginStyle='block';
        $scope.signupStyle='none';

        $scope.loginMobile='';
        $scope.loginPassword='';
        $scope.signupMobile='';
        $scope.signupPassword='';
        $scope.signupConfirmPassword='';
        $scope.agreement = '';


        $scope.loginForm =function(){
            $scope.isLoginForm=true;
            $scope.isSignupForm=false;
            $scope.loginStyle='block';
            $scope.signupStyle='none';
        };

        $scope.signupForm =function() {
            $scope.isLoginForm=false;
            $scope.isSignupForm=true;
            $scope.loginStyle='none';
            $scope.signupStyle='block';
        };

        // 登录
        $scope.loginSubmit = function($event) {
            if ($scope.login.$invalid || $scope.loginMobile=='' || $scope.loginPassword=='' ){
                alert('账号或密码错误');
                return;
            }
            $http.post('/lemon/account/login',{mobile:$scope.loginMobile,password:hex_md5($scope.loginPassword)}).success(function(data) {
                if(data.code==0){
                    location.href = data.url;
                }else if(data.code == 1){
                    alert(data.reason);
                }
            }).error(function(){
                alert('网络异常，请重试');
            })
        };

        // 注册
        $scope.signupSubmit = function($event) {
            if ($scope.signup.$invalid || $scope.signupPassword != $scope.signupConfirmPassword
                    || $scope.signupMobile=='' || $scope.signupPassword=='' || !$scope.agreement || $scope.agreement==''){
                if (!$scope.agreement) {
                    alert("请勾选协议");
                }else {
                    alert('检查表单是否填写正确');
                }
                return;
            }
            $scope.isValid=true;
            $http.post('/lemon/account/register',{mobile:$scope.signupMobile,password:hex_md5($scope.signupPassword)}).success(function(data) {
                if(data.code==0){
                    location.href = data.url;
                }else if(data.code == 1){
                    alert(data.reason);
                }
            }).error(function(){
                alert('网络异常，请重试');
            })
        };

    }]);

</script>

</body>

</html>
