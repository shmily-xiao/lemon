<!DOCTYPE html>
<html ng-app="app">
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">
    <meta name="baidu-site-verification" content="H53YfCY2d4">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta http-equiv="content-security-policy" content="default-src 'self'; script-src 'self' 'unsafe-inline' 'unsafe-eval' *.baidu.com; img-src 'self' *.baidu.com data: mp.weixin.qq.com; connect-src 'self' *.haobtc.com ws://*.haobtc.com wss://*.haobtc.com; style-src 'self' 'unsafe-inline'; child-src 'self' *.haobtc.com; frame-src 'self' *.haobtc.com">
    <!-- Bootstrap -->
    <link href="/css/bootstrap.css" rel="stylesheet">
    <script src="/js/angular.min.js"></script>
    <script src="/js/ngPlugin.js"></script>
    <script src="/js/jquery-3.1.0.min.js" charset="utf-8"></script>
    <script src="/js/jquery.nicescroll.min.js"></script>
    <script src="/js/md5.js"></script>

</head>
<body ng-controller="new-singup">

<div class="collapse navbar-collapse" id="menu-content">
    <ul class="nav navbar-nav navbar-right">
        <li><a href="/account/login">登录</a></li>
        <li><a href="/account/register">注册</a></li>

    </ul>
</div>
<!-- /.navbar-collapse -->
<!--main content-->
<div class="container main-container">
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
                        <form method="post" action="/account/register" class="col-md-8 col-md-offset-2 signup-action">
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
                                           ng-keydown="key($event)"
                                           ng-pattern="/^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,2,3,5-9]))\\d{8}$/"
                                           ng-trim='false'
                                           ng-model="mobile"
                                           ng-init="mobileShow=false"
                                           ng-focus="mobileShow=true"
                                           ng-blur="mobileShow=false"
                                           user-mobile  dy-name="'mobile'"
                                           ng-maxlength=11
                                           name="mobile"  placeholder="你的手机号码">
                                </div>
                            </div>
                            <div class="form-group form-group-lg center-block signup-password" id="id_password_wrapper">
                                <p>密码</p>
                                <input class="form-control" ng-keydown="key($event)" ng-trim='false' password ng-init="passwdShow=false" ng-focus="passwdShow=true" ng-blur="passwdShow=false"  ng-model="password" name="password" placeholder="请输入你的密码,最少10位" type="password">
                                <div id="password_error" class="line-alert text-danger hidden">

                                </div>
                            </div>
                            <div class="form-group form-group-lg center-block signup-password" id="id_confirm_wrapper">
                                <p>确认密码</p>
                                <input class="form-control" ng-keydown="key($event)" ng-trim='false'  password ng-init="passwdShow=false" ng-focus="passwdShow=true" ng-blur="passwdShow=false"  ng-model="confirmPassword" name="confirmPassword" placeholder="再次确认你的密码" type="password">
                                <div id="confirm_password_error" class="line-alert text-danger hidden">

                                </div>
                            </div>
                            <div class="form-group form-group-lg" id="id_agreement_wrapper">
                                <p>
                            <span>
                                <input id="id_agreement" name="agreement" type="checkbox">
                            </span>我同意&nbsp;<a href="#">使用条款</a>&nbsp;和&nbsp;<a href="#">隐私条款</a>.</p>
                                <div id="agreement_error" class="line-alert text-danger hidden">

                                </div>
                            </div>
                            <input id="id_create_button" ng-click="signupSubmit($event.target)"  class="btn btn-primary btn-lg btn-block signup-action-creat-button" value="创建新账号">
                        </form>
                        <!--signup action结束-->
                        <div class="col-md-8 col-md-offset-2 signup-intro">
                            <p class="text-center">已经拥有账号?<a href="/account/login">点此登录</a>
                            </p>
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
    app.controller('new-singup',['$scope','$http','alertMsg',function($scope,$http,alertMsg){

        // 注册
        $scope.key = function(e){
            $scope.summitValib = true;
            if($scope.summitValib){
                $scope.signupSubmit(angular.element(e.target).parents('form').find('button[name=button]'));
            }
        };
        $scope.signupSubmit = function($event) {
            angular.element($event).attr('disabled','disabled');
            var $this = angular.element($event).prev('.login-tips');
            $http.post('/account/register',{mobile:$scope.mobile,password:hex_md5($scope.password)}).success(function(data) {
                if(data.code==0){
                    location.href=data.url;
                }else if(data.code == 1){
                    angular.element($event).attr('disabled',false);
                    alertMsg.show($this,{
                        type : 'error',
                        content:data.msg
                    });
                }
            }).error(function(){
                angular.element($event).attr('disabled',false);
                alertMsg.show($this,{
                    type : 'error',
                    content:'网络异常，请重试'
                });
            })
        };
    }])
</script>

</body>
</html>