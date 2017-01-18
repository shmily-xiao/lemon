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
                              <p>昵称：{{lemonContent.nickName}}</p>
                          </div>
                          <div class="col-md-4 column">
                              <p>创建时间：{{lemonContent.createTime}}</p>
                          </div>

                          <div class="col-md-4 column" ng-if="lemonContent.type == 'DREAM'">

                                  <p ng-if="lemonContent.finishedTime!=null">完成时间：{{lemonContent.finishedTime}}</p>
                                  <p ng-if="lemonContent.leftTime!=null&&lemonContent.leftTime>=0">剩余时间：{{lemonContent.leftTime}}天</p>
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
                              <a href="" data-status="{{lemonContent.likeStatus}}"
                                 ng-click="likeStatusClick($event,lemonContent.id)"
                                 ng-class="{'true':'red','false':'gray'}[{{lemonContent.likeStatus=='true'}}]">
                                  <span class="glyphicon glyphicon-thumbs-up" ng-class="{'true':'red','false':'gray'}[{{lemonContent.likeStatus=='true'}}]"
                                        style="font-size: 6px;"></span>
                                  <span data-counts="{{lemonContent.likeCount}}">点赞({{lemonContent.likeCount}})</span></a>
                              <#--<a href="#"><span>评论</span></a>-->
                              &nbsp;&nbsp;
                              <#--<a href=""-->
                                 <#--ng-click="collectStatusClick($event,${lemonContent.collectStatus},${lemonContent.id})"-->
                                 <#--style="color: <#if lemonContent.collectStatus=="true">rgb(255, 0, 0)<#else>rgb(152, 151, 151)</#if>;">-->
                                  <#--<span class="glyphicon glyphicon-heart"-->
                                        <#--style="color: <#if lemonContent.collectStatus=="true">rgb(255, 0, 0)<#else>rgb(152, 151, 151)</#if>; font-size: 6px;"> </span>-->
                                  <#--&nbsp;收藏(${lemonContent.collectCount})</a>-->
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
          $scope.collectStatus = '';
          $scope.likeStatus = '';

          // todo 有问题
          // 点赞
          $scope.likeStatusClick = function (event,contentId) {
//              if ($scope.likeStatus==''){
//                  $scope.likeStatus = likeStatus;
//              }
              var likeStatus = angular.element(event.currentTarget).attr('data-status');
              if (likeStatus == 'true'){
                  $scope.likeStatus = "false";
              }else {
                  $scope.likeStatus = "true";
              }
              var url = "/lemon/lemons/LIKE/" + contentId+"/"+ $scope.likeStatus;

              $http.post(url).success(function (data) {
                  if (data.code == 0) {
                      // 应该去更改颜色的和数量的
                      var counts = angular.element(event.currentTarget).find('span').eq(1).attr('data-counts');
                      if(likeStatus == 'true') {
                          angular.element(event.currentTarget).removeClass('red').addClass('gray');
                          angular.element(event.currentTarget).attr('data-status',$scope.likeStatus);
                          angular.element(event.currentTarget).find('span').removeClass('red').addClass('gray');
                          angular.element(event.currentTarget).find('span').eq(1).attr('data-counts',(+counts-1)).text('点赞('+(+counts-1)+')');
                      } else {
                          angular.element(event.currentTarget).removeClass('gray').addClass('red');
                          angular.element(event.currentTarget).attr('data-status',$scope.likeStatus);
                          angular.element(event.currentTarget).find('span').removeClass('gray').addClass('red');
                          angular.element(event.currentTarget).find('span').eq(1).attr('data-counts',(+counts+1)).text('点赞('+(+counts+1)+')');
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
          $scope.collectStatusClick = function (event,collectStatus,contentId) {
              if (collectStatus){
                  $scope.collectStatus = "false";
              }else {
                  $scope.collectStatus = "true";
              }
              var url = "/lemon/lemons/COLLECT/" + contentId+"/"+ $scope.collectStatus;

              $http.post(url).success(function (data) {
                  if (data.code == 0) {
                      // 应该去更改颜色的和数量的
                      alert(data.msg);
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
