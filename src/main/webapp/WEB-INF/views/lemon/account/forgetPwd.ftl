
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
<div class="container main-container" ng-app="myApp" ng-controller="forgetPwd">

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
                    <h4 id="login-heading">发送验证码到您的账户</h4>
                </div>
                <div class="panel-body">
                    <div class="row ">
                        <form method="post" action="/lemon/account/forget" name="forgetPwd" class="col-md-8 col-md-offset-2 login-action">
                            <!--login action开始-->
                            <div class="form-group form-group-lg center-block " id="login-account">
                                <p>您的账户</p>
                                <div class="intl-tel-input">
                                    <div class="flag-dropdown">
                                    <input class="form-control"
                                           type="text"
                                           name="account"
                                           ng-trim='false'
                                           ng-model="account"
                                           placeholder="请输入您注册时候使用的账号"/>
                                    </div>
                                </div>
                            </div>

                            <input ng-click="forgetPwdSubmit($event)" class="btn btn-primary btn-lg  btn-block login-action-button" value="提交"/>
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

    app.controller('forgetPwd',['$scope','$http',function($scope,$http) {
        $scope.authCode= '${authKey}' || '';
        $scope.account='';

        $scope.forgetPwdSubmit = function($event) {
            if ($scope.account==''){
                return;
            }

            var url = "/lemon/account/forget";
            $http.post(url, {account: $scope.account,authKey:$scope.authCode}).success(function(data) {
                if(data.code==0){
                    alert(data.msg);;
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