<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>我的发布</title>
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

  <body ng-app="myApp" ng-controller="myselfContents">

  <#assign currentPage = "myselfPage">
  <#include "/lemon/common/head.ftl">

  <div class="container">
      <#list lemonContents as lemonContent>
      <div class="row clearfix" style="border: 1px solid rgba(129, 138, 135, 0.57);padding: 5px;margin-top: 10px;margin-bottom: 16px;">
          <div class="col-md-1 column">
          </div>
          <div class="col-md-10 column">
              <div class="row clearfix">
                  <div class="col-md-11 column">
                      <div class="row clearfix" style="margin-top: 12px;margin-left: 2px;">
                          <#if lemonContent.type !="DREAM">
                              <div class="col-md-8 column">
                          <#else>
                              <div class="col-md-4 column">
                          </#if>
                          <#if lemonContent.type =="DREAM">
                              <#if lemonContent.status=="FINISHED">
                                  <span class="glyphicon glyphicon-ok" style="color: rgb(0, 185, 0); font-size: 17px;"> &nbsp;已完成</span>
                              <#elseif lemonContent.status=="FAILED">
                                  <span class="glyphicon glyphicon-remove" style="color: rgb(0, 0, 0); font-size: 17px;"> &nbsp;失败</span>
                              <#elseif lemonContent.status=="WILLEXPIRE">
                                  <span class="glyphicon glyphicon-warning-sign" style="color: rgb(255, 0, 0); font-size: 17px;"> &nbsp;即将到期</span>
                              <#else>
                                  <span class="glyphicon glyphicon-repeat" style="color: rgb(0, 172, 255); font-size: 17px;"> &nbsp;正在进行</span>
                              </#if>
                          </#if>
                            </div>
                          <div class="col-md-4 column">
                              <p>创建时间：${lemonContent.createTime}</p>
                          </div>
                          <#if lemonContent.type =="DREAM">
                          <div class="col-md-4 column">

                              <#if lemonContent.status=="FINISHED">
                                  <p>完成时间：${lemonContent.finishedTime}</p>
                              <#elseif lemonContent.status=="FAILED">
                                  <p>未完成</p>
                              <#else>
                                  <p>倒计时：${lemonContent.leftTime}天</p>
                              </#if>

                          </div>
                          </#if>
                      </div>
                    <div class="col-md-12 column" style="margin-top: -12px;">
                        <#if !lemonContent.imageUrl?has_content>
                            <h2>
                            ${lemonContent.title}
                            </h2>
                            <p>
                            ${lemonContent.description}
                            </p>
                        <#else >
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="thumbnail" style=" margin-top: 16px;border: 0px solid #ddd;">
                                        <img src="${lemonContent.imageUrl[0]}" />
                                        <div class="caption">
                                            <h3>
                                            ${lemonContent.title}
                                            </h3>
                                            <p>
                                            ${lemonContent.description}
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </#if>
                    </div>

                      <#if lemonContent.type =="DREAM" && (lemonContent.status=="WILLEXPIRE"||lemonContent.status=="DOING") && isCurrentUser=='true'>
                      <div class="row clearfix">
                          <div class="col-md-4 column">
                          </div>
                          <div class="col-md-4 column">
                          </div>
                          <div class="col-md-4 column">
                              <a href="#" ng-click="finishContent(event,${lemonContent.id})" style="color: rgb(52, 58, 68);font-weight: bold;font-size: inherit;">&nbsp;完成</a>
                              <#if lemonContent.status=="WILLEXPIRE">
                              &nbsp;&nbsp;
                              <a href="#" style="color: rgb(52, 58, 68);font-weight: bold;font-size: inherit;">&nbsp;延期</a>
                              </#if>
                              &nbsp;&nbsp;
                              <a href="#" style="color: rgb(52, 58, 68);font-weight: bold;font-size: inherit;">&nbsp;放弃</a>
                          </div>
                      </div>
                      </#if>
                  </div>
              </div>
          </div>
      </div>
      </#list>
  </div>
          <script src="/js/angular.min.js"></script>
          <script>
              var app = angular.module('myApp', []);
              app.controller('myselfContents',['$scope','$http',function($scope,$http) {

                  $scope.finishContent = function($event, contentId) {
                      var url = '/lemon/finish/'+ contentId;
                      $http.post(url).success(function(data) {
                          if(data.code==0){
                              alert("修改成功");
                              window.location.reload();
                          }else{
                              alert("修改失败");
                          }
                      }).error(function(){
                          alert('网络异常，请重试');
                      })
                  };
              }]);
          </script>

  </body>
</html>
