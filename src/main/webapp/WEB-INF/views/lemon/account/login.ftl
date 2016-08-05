
<!DOCTYPE html>
<html >
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">
    <meta name="baidu-site-verification" content="H53YfCY2d4">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap -->
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/bootstrap.2183d05f5a0a.css" rel="stylesheet">
    <link href="/css/index.c4dfc26089f4.css" rel="stylesheet"/>
    <link href="/css/font-awesome.8e12157da5fc.css" rel="stylesheet"/>
    <link href="/css/intlTelInput.1c5f40243067.css" rel="stylesheet" />
    <link href="/css/intlTelInput-reload.32b2d0bf5fb4.css" rel="stylesheet" />
    <link rel="stylesheet" href="/css/footer.a18761b4dee9.css">
    s
    <script src="/js/angular.min.js"></script>
    <script src="/js/jquery-3.1.0.min.js" charset="utf-8"></script>
    <script src="/js/jquery.nicescroll.min.js"></script>
    <script src="/js/md5.js"></script>

</head>
<body>

<div class="collapse navbar-collapse" id="menu-content">
    <ul class="nav navbar-nav navbar-right">
        <li><a href="/account/login">登录</a></li>
        <li><a href="/account/register">注册</a></li>

    </ul>
</div>
<div class="container main-container" ng-app="myApp" ng-controller="userLogin">

    <div class="row">

        <!--  -->
        <div class="_alert_ alert alert-dismissible text-center hide" role="alert">
            <button type="button" class="close" data-dismiss="alert">
                <span aria-hidden="true">×</span><span class="sr-only"></span>
            </button>
            <div class="_alert_msg_"></div>
        </div>
        <!--  -->

    </div>
    <div class="row login-main" >
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="panel-heading text-center">
                    <h4 id="login-heading">登录</h4>
                </div>
                <div class="panel-body">
                    <div class="row ">
                        <form method="post" action="/account/login" name="login" class="col-md-8 col-md-offset-2 login-action">
                            <!--login action开始-->
                            <div class="form-group form-group-lg center-block " id="login-account">
                                <p>账号</p>
                                <div class="intl-tel-input">
                                    <div class="flag-dropdown">
                                    <input class="form-control"
                                           type="text"
                                           name="mobile"
                                           ng-pattern="/^1[3|4|5|7|8]\d{9}$/"
                                           ng-trim='false'
                                           ng-model="mobile"
                                           ng-minlength=11
                                           placeholder="请输入您的手机号码"/>
                                    </div>
                                    <div id="account_error" class="line-alert text-danger" ng-cloak ng-class="[{'hidden':!(login.mobile.$invalid)}]">
                                        <span class="error line-alert text-danger" >手机号填写不正确</span>
                                    </div>
                                </div>
                            <div class="form-group form-group-lg center-block " id="login-password">
                                <p>密码</p>
                                <input class="form-control"
                                       id="id_password"
                                       name="password"
                                       ng-trim='false'
                                       ng-model="password"
                                       ng-minlength=8
                                       ng-maxlength=100
                                       type="password"
                                       placeholder="请输入您的密码"/>
                                <div id="password_error" class="line-alert text-danger" ng-cloak ng-class="[{'hidden':!(login.password.$invalid)}]">
                                    <span class="error line-alert text-danger">密码最少8位</span>
                                </div>
                            </div>
                            <input ng-click="loginSubmit($event)" class="btn btn-primary btn-lg  btn-block login-action-button" value="登录"/>
                            <p>
                                <a href="/auth/forget/?k=DhRNzw">忘记密码?</a>
                                或
                                <a href="/account/register">创建新账号</a>


                            </p><div class="login-wechat" data-expire_seconds="300">
                            <a class="btn btn-default text-center hidden-xs" data-toggle="modal" data-target="#wechat-login-qr"><i class="fa fa-wechat"></i>微信登录</a>
                        </div>


                            <p></p>


                            <div class="modal fade" id="wechat-login-qr" tabindex="-1" role="dialog" aria-labelledby="requestlabel">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h4 class="modal-title" id="requestlabel">
                                                <img src="/img/qq16w.ico" style="margin-top: -3px; margin-right: 3px;"/>QQ登录
                                            </h4>
                                        </div>
                                        <div class="modal-body">
                                            <div class="_wx_">
                                                <img class="_wx_qrcode center-block" width="160" height="160" src="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQFh8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL0JVeUQzYWJsejZtMnFZenEyMkpXAAIE/UmjVwMELAEAAA==">
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

                        </form>
                        <!--login action end-->
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<script type="text/javascript">
    var app = angular.module('myApp', []);

    app.controller('userLogin',['$scope','$http',function($scope,$http) {
        $scope.mobile='';
        $scope.password='';

        $scope.loginSubmit = function($event) {
            if ($scope.login.$invalid || $scope.mobile=='' || $scope.password=='' ){
                if ($scope.mobile=='') $scope.login.mobile.$invalid=true;
                if ($scope.password == '') $scope.login.password.$invalid=true;
                return;
            }
            $http.post('/account/login',{mobile:$scope.mobile,password:hex_md5($scope.password)}).success(function(data) {
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