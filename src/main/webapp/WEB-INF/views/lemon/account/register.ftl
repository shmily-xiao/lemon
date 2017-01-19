<!DOCTYPE html>
<html >
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">
    <meta name="baidu-site-verification" content="H53YfCY2d4">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta http-equiv="content-security-policy" content="default-src 'self'; script-src 'self' 'unsafe-inline' 'unsafe-eval' *.baidu.com; img-src 'self' *.baidu.com data: mp.weixin.qq.com; connect-src 'self' *.haobtc.com ws://*.haobtc.com wss://*.haobtc.com; style-src 'self' 'unsafe-inline'; child-src 'self' *.haobtc.com; frame-src 'self' *.haobtc.com">
    <!-- Bootstrap -->
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/bootstrap.2183d05f5a0a.css" rel="stylesheet" />
    <link href="/css/index.c4dfc26089f4.css" rel="stylesheet"/>
    <link href="/css/font-awesome.8e12157da5fc.css" rel="stylesheet"/>
    <link href="/css/intlTelInput.1c5f40243067.css" rel="stylesheet" />
    <link href="/css/intlTelInput-reload.32b2d0bf5fb4.css" rel="stylesheet" />
    <link rel="stylesheet" href="/css/footer.a18761b4dee9.css">

    <script src="/js/angular.min.js"></script>
    <#--<script src="/js/ngPlugin.js"></script>-->
    <script src="/js/jquery-3.1.0.min.js" charset="utf-8"></script>
    <script src="/js/jquery.nicescroll.min.js"></script>
    <script src="/js/md5.js"></script>

</head>
<body>

<div class="collapse navbar-collapse" id="menu-content">
    <ul class="nav navbar-nav navbar-right">
        <li><a href="/lemon/account/login">登录</a></li>
        <li><a href="/lemon/account/register">注册</a></li>

    </ul>
</div>
<!-- /.navbar-collapse -->
<!--main content-->
<div class="container main-container" ng-app="myApp" ng-controller="new-singup">
    <div class="row signup-main">
        <!--signup-main开始-->
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <!--panel 开始-->
                <div class="panel-heading text-center">
                    <h4 id="signup-heading">注册</h4>
                </div>
                <div class="panel-body">
                    <!--panel body开始-->
                    <div class="row ">
                        <form method="post" name="signup" action="/lemon/account/register" class="col-md-8 col-md-offset-2 signup-action">
                            <!--signup action开始-->
                            <#--<input type="hidden" name="csrfmiddlewaretoken" value="tE0wbJECnGxg4yslkSRxwuldjNC99sfB">-->
                            <input type="text" style="display:none">
                            <input type="password" style="display:none">
                            <input type="password" style="position:absolute; top:-2000px;">
                            <div class="form-group form-group-lg" id="id_account_wrapper">
                                <div class="account-phone">
                                    <p>手机号</p>
                                </div>
                                <div class="intl-tel-input">
                                    <input class="form-control"
                                           type="tel"
                                           ng-pattern="/^1[3|4|5|7|8]\d{9}$/"
                                           ng-trim='false'
                                           ng-model="mobile"
                                           ng-minlength=11
                                           name="mobile"
                                           placeholder="你的手机号码"/>
                                </div>
                                <span class="error line-alert text-danger" ng-cloak ng-class="[{'hidden':!(signup.mobile.$invalid)}]">手机号填写不正确</span>
                            </div>
                            <div class="form-group form-group-lg center-block signup-password" id="id_password_wrapper">
                                <p>密码</p>
                                <input class="form-control"
                                       type="password"
                                       ng-trim='false'
                                       ng-model="password"
                                       ng-minlength=8
                                       ng-maxlength=100
                                       name="password" placeholder="请输入你的密码,最少8位" />
                                <div id="password_error" class="line-alert text-danger" ng-cloak ng-class="[{'hidden':!(signup.password.$invalid)}]">
                                    <span class="error line-alert text-danger">密码最少8位</span>
                                </div>
                            </div>
                            <div class="form-group form-group-lg center-block signup-password" id="id_confirm_wrapper">
                                <p>确认密码</p>
                                <input class="form-control"
                                       type="password"
                                       ng-trim='false'
                                       ng-model="confirmPassword"
                                       name="confirmPassword" placeholder="再次确认你的密码" />
                                <div id="confirm_password_error" class="line-alert text-danger" ng-cloak ng-class="[{'hidden':confirmPassword==password}]">
                                    <span class="error line-alert text-danger"> 两次密码不一致</span>
                                </div>
                            </div>
                            <div class="form-group form-group-lg" id="id_agreement_wrapper">
                                <p>
                                    <span>
                                        <input id="id_agreement"
                                               name="agreement"
                                               ng-model="agreement"
                                               type="checkbox"/>
                                    </span>
                                    我同意&nbsp;<a href="#">使用条款</a>&nbsp;和&nbsp;<a href="#">隐私条款</a>.
                                </p>
                                <div id="agreement_error" class="line-alert text-danger " ng-cloak ng-class="[{'hidden':isValid}]">
                                    <span class="error line-alert text-danger"> 请确认协议</span>
                                </div>
                            </div>
                            <input id="id_create_button"
                                   ng-click="signupSubmit($event)"
                                   class="btn btn-primary btn-lg btn-block signup-action-creat-button" value="创建新账号"/>
                        </form>
                        <!--signup action结束-->
                        <div class="col-md-8 col-md-offset-2 signup-intro">
                            <p class="text-center">已经拥有账号?<a href="/lemon/account/login">点此登录</a></p>
                        </div>
                    </div>
                </div>
                <!--panel body结束-->
            </div>
            <!--panel 结束-->
        </div>
    </div>
    <!--signup-main结束-->
</div>
<!--main content-->

<script type="text/javascript">
    var app = angular.module('myApp', []);
    app.controller('new-singup',['$scope','$http',function($scope,$http){
           $scope.mobile='';
           $scope.password='';
           $scope.confirmPassword='';
           $scope.agreement = '';
           $scope.isValid=true;

            // 注册
           $scope.signupSubmit = function($event) {
               if ($scope.signup.$invalid || $scope.password != $scope.confirmPassword
                       || $scope.mobile=='' || $scope.password=='' || !$scope.agreement || $scope.agreement==''){
                   if ($scope.mobile=='') $scope.signup.mobile.$invalid=true;
                   if ($scope.password == '') $scope.signup.password.$invalid=true;
                   if ($scope.agreement=='') {
                       $scope.isValid=false;
                   }else {
                       $scope.isValid=true;
                   }
                   return;
               }
               $scope.isValid=true;
               $http.post('/lemon/account/register',{mobile:$scope.mobile,password:hex_md5($scope.password)}).success(function(data) {
                   if(data.code==0){
                       location.href = data.url;
                   }else if(data.code == 1){
                       alert(data.reason);
                   }
               }).error(function(){
                   alert('网络异常，请重试');
               })
           };
    }])
</script>

</body>
</html>