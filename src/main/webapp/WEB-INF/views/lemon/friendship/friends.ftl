<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>好友</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- 可选的Bootstrap主题文件（一般不使用） -->
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"/>

    <link href="/css/ngdialog/ngDialog.min.css" rel="stylesheet"/>
    <link href="/css/ngdialog/ngDialog-theme-default.min.css" rel="stylesheet"/>
    <link href="/css/ngdialog/ngDialog-theme-plain.min.css" rel="stylesheet"/>

    <script src="/js/angular.min.js"></script>

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <!-- 模态弹出框的JS-->
    <script src="http://cdnjs.cloudflare.com/ajax/libs/ng-dialog/0.4.0/js/ngDialog.min.js"></script>


    <style>
        body {
            padding-top: 50px;
        }
        .starter-template {
            padding: 40px 15px;
            text-align: center;
        }
        .navbar-default {
            background-color: #DEDEDE;
            border-color: #e7e7e7;
        }
        /*为了不闪烁*/
        [ng\:cloak], [ng-cloak], [data-ng-cloak], [x-ng-cloak], .ng-cloak, .x-ng-cloak {
            display: none !important;
        }
    </style>
</head>

<body ng-app="myApp" ng-controller="friends">

<!--header-->
<#assign currentPage = "friends">
<#include "/lemon/common/head.ftl">
<!--header end-->


<div class="container">
    <div class="col-md-12 column">
        <div class="col-md-6 column">
            <div style="margin-top: 20px;margin-bottom: 20px;">
                <form class="bs-example bs-example-form" role="form">
                    <div class="input-group">
                        <input type="text" class="form-control" ng-model="account" placeholder="好友搜索">
                        <span class="input-group-addon" style="cursor:pointer" ng-click="searchUser(event)">
                            <span class="glyphicon glyphicon-search" style="color: rgb(152, 151, 151); font-size: 15px;">搜索</span>
                        </span>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="row clearfix" style="padding: 5px;">
        <div class="col-md-12 column">
        </div>
            <div class="col-md-12 column" ng-if="successStatus=='true'" ng-cloak="">
                <div class="col-md-6 column">
                    <div class="col-sm-2 column">
                        <button type="button" class="btn btn-default" ng-click="addUser(event)">关注</button>
                        <a href="/lemon/lemons/{{friendView.userId}}/friend/contents" ><button type="button" class="btn btn-default">查看</button></a>
                    </div>


                    <div class="col-md-4 column">
                        <div class="thumbnail" ng-click="addOrLookUser(event)" style="cursor:pointer">
                            <img ng-src="{{friendView.avatar}}" class="img-rounded">
                        </div>
                    </div>
                    <div  class="col-md-6 column">
                        <p >昵称：<span ng-bind="friendView.nickName"></span></p>
                        <p >性别：<span ng-bind="friendView.sex"></span></p>
                        <p >生日：<span ng-bind="friendView.birthday"></span></p>
                        <p >简介：<span ng-bind="friendView.profile"></span></p>
                    </div>
                </div>
            </div>
    </div>
    <div class="row clearfix" style="padding: 5px;">
        <div class="col-md-12 column" style="margin-top: 20px;" ng-repeat="groupView in friendGrouds" ng-cloak="">
        <h4 ng-bind="groupView.groupName"></h4>
        <div class="row clearfix" style="margin-top: 15px;border-top: 1px solid rgba(129, 138, 135, 0.57);padding: 5px;">
            <div class="col-md-12 column">
            </div>
            <div class="col-md-12 column">
                <div class="col-md-4 column" ng-repeat="friend in groupView.elementViewList" ng-cloak=" ">
                    <div class="col-md-7 column">
                        <a href="/lemon/lemons/{{friend.userId}}/friend/contents">
                            <div class="thumbnail">
                                <img ng-src="{{friend.avatar}}" class="img-rounded">
                            </div>
                        </a>
                    </div>
                    <div  class="col-md-5 column">
                        <p ng-bind="friend.nickName"></p>
                        <p>性别：<span ng-bind="friend.sex"></span></p>
                        <p>生日：<span ng-bind="friend.birthday"></span></p>
                        <p>简介：<span ng-bind="friend.profile"></span></p>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </div>
</div>
<script type="text/ng-template" id="modalContent.html">
    <div class="col-md-12 column">
        <h4 style="text-align:center">添加确认</h4>
    </div>
    <div class="col-md-12 column">
        <p style="text-align:center">是否添加<span ng-bind="friendView.nickName"></span>为好友</p>
    </div>
    <div class="dialog_footer" style="text-align:center">
        <div class="ops">
            <button ng-click="cancel($event)">取&nbsp;&nbsp;消</button>
            <button ng-click="ok($event)">添&nbsp;&nbsp;加</button>
        </div>
    </div>
</script>

<script type="text/ng-template" id="status.html">
    <div class="col-md-12 column">
        <h4 style="text-align:center">添加状态</h4>
    </div>
    <div class="col-md-12 column">
        <p style="text-align:center"><span ng-bind="myMsg"></span></p>
    </div>
    <div class="dialog_footer" style="text-align:center">
        <div class="ops">
            <button ng-click="cancel($event)">取&nbsp;&nbsp;消</button>
        </div>
    </div>
</script>


<script>
    $(function () { $(".popover-options a").popover({html : true });});
</script>
<script>
    var app = angular.module('myApp', ['ngDialog']);
    app.controller('friends',['$scope','$http','ngDialog',function($scope,$http,ngDialog) {
        $scope.friendGrouds = ${friendGroupsViews}||[];
        $scope.account='';
        $scope.successStatus='false';

        $scope.searchUser = function($event) {
            if ($scope.account==''){
                alert('需要填写账户再搜索！');
                return;
            }
            var url = '/lemon/search/user/'+$scope.account;
            $http.post(url).success(function(data) {
                if(data.code==0){
                    $scope.friendView = JSON.parse(data.data);
                    $scope.successStatus='true';
                }else if(data.code == 1){
                    alert(data.msg);
                }
            }).error(function(){
                alert('网络异常，请重试');
            })
        };

        // 添加好友
        $scope.addUser = function(e) {
           $scope.addUserDialog = ngDialog.open({
                template: 'modalContent.html',
                className:'ngdialog-theme-default ngdialog-theme-custom',
                controller:'modalController',
                scope:$scope,
                disableAnimation: true
            });
        };
        $scope.bodyScroll = function (status) {
            $(document.body).css({'overflow': status});
        };
    }]);
    //区域弹出框controller
    app.controller('modalController', function ($scope,ngDialog,$http) {
        $scope.ok = function ($event) {
            var url='/lemon/friends/add/'+$scope.friendView.userId;

            $http.post(url).success(function (data) {
                var _scope = $scope.$parent;
                $scope.closeThisDialog('modalContent.html');
                _scope.myMsg = data.msg;
                ngDialog.open({
                    template: 'status.html',
                    className:'ngdialog-theme-default ngdialog-theme-custom',
                    controller:'status',
                    scope:_scope,
                    disableAnimation: true
                });
            }).error(function () {
                var _scope = $scope.$parent;
                $scope.closeThisDialog('modalContent.html');
                _scope.myMsg = "网络异常";
                ngDialog.open({
                    template: 'status.html',
                    className:'ngdialog-theme-default ngdialog-theme-custom',
                    controller:'status',
                    scope:_scope,
                    disableAnimation: true
                });
            });
        };

        $scope.cancel = function ($event) {
            ngDialog.closeAll('modalContent.html');
        };
    });

    //区域弹出框controller
    app.controller('status', function ($scope,ngDialog,$http) {
        $scope.cancel = function ($event) {
            ngDialog.closeAll('status.html');
        };
    });


</script>
</body>
</html>
