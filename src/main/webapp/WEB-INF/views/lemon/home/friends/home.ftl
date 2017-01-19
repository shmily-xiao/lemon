<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>首页</title>
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
          a.red{
              color: red;
          }
          a.gray{
            color:rgb(152, 151, 151)
          }
          /*为了不闪烁*/
          [ng\:cloak], [ng-cloak], [data-ng-cloak], [x-ng-cloak], .ng-cloak, .x-ng-cloak {
              display: none !important;
          }
      </style>
  </head>

  <body ng-app="myApp" ng-controller="lemonsFriend">

  <!-- header -->
  <#assign currentPage = "friendsHomepage">
  <#include "/lemon/common/head.ftl">
  <!-- header end -->

  <div class="container">
      <#--<#list lemonContents as lemonContent>-->
        <div class="row clearfix" style="margin-top: 15px;border-top: 1px solid rgba(129, 138, 135, 0.57);padding: 5px;margin-bottom: 16px;"
             ng-repeat="lemonContent in lemonContents">
          <div class="col-md-1 column">
          </div>
          <div class="col-md-10 column">
              <div class="row clearfix">
                  <div class="col-md-1 column">
                      <img alt="140x140" src="/img/user64.ico" />
                  </div>
                  <div class="col-md-11 column">
                      <div class="row clearfix" style="margin-top: 12px;margin-left: 2px;">
                          <div class="col-md-4 column">
                              <p>昵称：<span ng-bind="lemonContent.nickName"></span></p>
                          </div>
                          <div class="col-md-4 column">
                              <p>创建时间：<span ng-bind="lemonContent.createTime"></span></p>
                          </div>

                          <div class="col-md-4 column" ng-if="lemonContent.type == 'DREAM'" ng-cloak="">

                              <p ng-if="lemonContent.finishedTime!=null">完成时间：<span ng-bind="lemonContent.finishedTime"></span></p>
                              <p ng-if="lemonContent.leftTime!=null&&lemonContent.leftTime>=0">剩余时间：<span ng-bind="lemonContent.leftTime"></span>天</p>
                                  <p ng-if="lemonContent.finishedTime==null&&lemonContent.leftTime==null">未完成</p>

                          </div>
                      </div>
                    <div class="col-md-12 column" style="margin-top: 0px;">
                        <div  class="col-md-12 column" ng-if="lemonContent.imageUrl==null || lemonContent.imageUrl.length==0" style="margin-left: -14px">
                        <h3 ng-bind="lemonContent.title">
                        </h3>
                        <p ng-bind="lemonContent.description">
                        </p>
                        </div>
                        <div class="row" ng-if="lemonContent.imageUrl!=null && lemonContent.imageUrl.length!=0">
                            <div class="col-md-12">
                                <div class="thumbnail">
                                    <img src="{{lemonContent.imageUrl[0]}}" />
                                    <div class="caption">
                                        <h3 ng-bind="lemonContent.title">
                                        </h3>
                                        <p ng-bind="lemonContent.description">
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                      <div class="row clearfix">
                          <div class="col-md-4 column">

                          </div>
                          <div class="col-md-4 column">
                          </div>
                          <div class="col-md-4 column">
                              <a href=""
                                 ng-click="likeStatusClick($event,lemonContent.id)"
                                 ng-class="{'red':lemonContent.likeStatus=='true','gray':lemonContent.likeStatus!='true'}" >
                                  <span class="glyphicon glyphicon-thumbs-up" style="font-size: 6px;"></span>
                                  <span >点赞(<span ng-bind="lemonContent.likeCount"></span>)</span></a>
                              <#--<a href="#"><span>评论</span></a>-->
                              &nbsp;&nbsp;
                              <a href=""
                                 ng-click="collectStatusClick($event,lemonContent.id)"
                                 ng-class="{'red':lemonContent.collectStatus=='true','gray':lemonContent.collectStatus!='true'}">
                                  <span class="glyphicon glyphicon-heart" style="font-size: 6px;"> </span>
                                  &nbsp;收藏(<span ng-bind="lemonContent.collectCount"></span>)</a>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
          <div class="col-md-1 column">
          </div>
      </div>
      <#--</#list>-->
  </div>
  <script src="/js/angular.min.js"></script>
  <script>
      var app = angular.module('myApp', []);
      app.controller('lemonsFriend',['$scope','$http',function($scope,$http) {
          $scope.lemonContents = ${lemonContents}||[];

          // 点赞
          $scope.likeStatusClick = function (event,contentId) {
              var _this = this;
              var likeStatus = this.lemonContent.likeStatus;
              if (likeStatus=='true'){
                  likeStatus='false';
              }else {
                  likeStatus='true';
              }
              var url = "/lemon/lemons/LIKE/" + contentId+"/"+ likeStatus;
              $http.post(url).success(function (data) {
                  if (data.code == 0) {
                      _this.lemonContent.likeStatus = likeStatus;
                      if (likeStatus=='true'){
                          _this.lemonContent.likeCount = _this.lemonContent.likeCount+1;
                      }else {
                          _this.lemonContent.likeCount = _this.lemonContent.likeCount-1;
                      }
                  } else if (data.code == 1) {
                      alert(data.msg);
                  }else {
                      alert("网络异常");
                  }
              }).error(function () {
                  alert("网络异常");
              });

          };
          // 收藏
          $scope.collectStatusClick = function (event,contentId) {
              var _this = this;
              var collectStatus = this.lemonContent.collectStatus;
              if (collectStatus=='true'){
                  collectStatus='false';
              }else {
                  collectStatus='true';
              }
              var url = "/lemon/lemons/COLLECT/" + contentId+"/"+ collectStatus;
              $http.post(url).success(function (data) {
                  if (data.code == 0) {
                      _this.lemonContent.collectStatus = collectStatus;
                      if (collectStatus=='true'){
                          _this.lemonContent.collectCount = _this.lemonContent.collectCount+1;
                      }else {
                          _this.lemonContent.collectCount = _this.lemonContent.collectCount-1;
                      }
                  } else if (data.code == 1) {
                      alert(data.msg);
                  }else {
                      alert("网络异常");
                  }
              }).error(function () {
                  alert("网络异常");
              });
          };

      }]);
  </script>
  </body>
</html>
