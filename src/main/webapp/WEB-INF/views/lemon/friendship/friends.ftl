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
    <link src="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></link>

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
    <div class="col-md-12 column" style="margin-top: 20px;" ng-repeat="groupView in friendGroupsViews" ng-cloak="">
        <h4 ng-bind="groupView.groupName"></h4>
        <div class="row clearfix" style="margin-top: 15px;border-top: 1px solid rgba(129, 138, 135, 0.57);padding: 5px;">
            <div class="col-md-12 column">
            </div>
            <div class="col-md-12 column">
                <div class="col-md-4 column" ng-repeat="friend in groupView.elementViewList" ng-cloak=" ">
                    <div class="col-md-7 column">
                        <div class="thumbnail">
                            <img src="{{friend.avatar}}" class="img-rounded">
                        </div>
                        <div  class="col-md-5 column">
                            <p ng-bind="friend.nickName"></p>
                            <p ng-bind="friend.sex"></p>
                            <p >生日：<span ng-bind="friend.birthday"></span></p>
                            <p ng-bind="friend.profile"></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/js/angular.min.js"></script>
<script>
    var app = angular.module('myApp', []);
    app.controller('friends',['$scope','$http',function($scope,$http) {
        $scope.friendGrouds = ${friendGroupsViews}||[];
    }]);
</script>
</body>
</html>
