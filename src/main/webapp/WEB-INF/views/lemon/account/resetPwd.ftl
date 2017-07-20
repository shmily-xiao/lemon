
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
    <link href="/css/intlTelInput-reload.32b2d0bf5fb4.css" rel="stylesheet" />
    <link href="/css/footer.a18761b4dee9.css" rel="stylesheet" >

    <script src="/js/angular.min.js"></script>
    <script src="/js/jquery-3.1.0.min.js" charset="utf-8"></script>
    <script src="/js/jquery.nicescroll.min.js"></script>
    <script src="/js/md5.js"></script>

    <style>
        .center-block {
            margin-bottom: 52px;
            margin-top: 15px;
        }
    </style>
</head>
<body>

<div class="collapse navbar-collapse" id="menu-content">
    <ul class="nav navbar-nav navbar-right">
        <li><a href="/lemon/account/login">登录</a></li>
        <li><a href="/lemon/account/register">注册</a></li>

    </ul>
</div>
<div class="container main-container" ng-app="myApp" ng-controller="resetPwd">

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
                    <h4 id="login-heading">重置密码</h4>
                </div>
                <div class="panel-body">
                    <div class="row ">
                        <form method="post" action="/lemon/account/login" name="resetPwd" class="col-md-8 col-md-offset-2 login-action">
                            <!--login action开始-->
                            <div class="form-group form-group-lg center-block " id="login-account">
                                <p>验证码</p>
                                <div class="intl-tel-input">
                                    <div class="flag-dropdown">
                                    <input class="form-control"
                                           type="text"
                                           name="authCode"
                                           ng-trim='false'
                                           ng-model="authCode"
                                           placeholder="请输入您收到的验证码"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group form-group-lg center-block signup-password" id="id_password_wrapper">
                                <p>新密码</p>
                                <input class="form-control"
                                       type="password"
                                       ng-trim='false'
                                       ng-model="password"
                                       ng-minlength=8
                                       ng-maxlength=100
                                       name="password" placeholder="请输入你的密码,最少8位" />
                                <div id="password_error" class="line-alert text-danger" ng-cloak ng-class="[{'hidden':!(resetPwd.password.$invalid)}]">
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

                            <input ng-click="resetPwdSubmit($event)" class="btn btn-primary btn-lg  btn-block login-action-button" value="提交"/>

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

    app.controller('resetPwd',['$scope','$http',function($scope,$http) {
        $scope.authCode= '${authCode}' || '';
        $scope.newPassword='';
        $scope.confirmPassword='';
        $scope.userId = ${userId};

        $scope.resetPwdSubmit = function($event) {
            if ($scope.resetPwd.password.$invalid || $scope.password != $scope.confirmPassword
                    || $scope.authCode=='' || $scope.password==''){

                if ($scope.password == '') $scope.resetPwd.password.$invalid=true;

                return;
            }
            var url = "/lemon/account/reset";
            $http.post(url, {userId: $scope.userId,authCode:$scope.authCode,newPassword:hex_md5($scope.password)}).success(function(data) {
                if(data.code==0){
                    location.href = data.url;
                }else {
                    alert(data.msg);
                }
            }).error(function(){
                alert('网络异常，请重试');
            })
        };
    }]);
</script>


</body>
</html>