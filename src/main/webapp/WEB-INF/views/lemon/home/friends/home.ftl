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
      </style>
  </head>

  <body>

  <!-- header -->
  <#assign currentPage = "friendsHomepage">
  <#include "/lemon/common/head.ftl">
  <!-- header end -->

  <div class="container">
      <#list lemonContents as lemonContent>
        <div class="row clearfix" style="margin-top: 15px;border-top: 1px solid rgba(129, 138, 135, 0.57);padding: 5px;">
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
                              <p>昵称：${lemonContent.nickName}</p>
                          </div>
                          <div class="col-md-4 column">
                              <p>创建时间：${lemonContent.createTime}</p>
                          </div>
                          <div class="col-md-4 column">
                              <#if lemonContent.finishedTime?has_content>
                                  <p>完成时间：${lemonContent.finishedTime}</p>
                              <#elseif lemonContent.leftTime?has_content>
                                  <p>剩余时间：${lemonContent.leftTime}天</p>
                              <#else>
                                  <p>未完成</p>
                              </#if>
                          </div>
                      </div>
                    <div class="col-md-12 column" style="margin-top: -12px;">
                        <#if lemonContent.imageUrl.empty>
                        <h2>
                            ${lemonContent.title}
                        </h2>
                        <p>
                            ${lemonContent.description}
                        </p>
                        <#else >
                        <div class="row">
                            <div class="col-md-12">
                                <div class="thumbnail">
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

                      <div class="row clearfix">
                          <div class="col-md-4 column">

                          </div>
                          <div class="col-md-4 column">
                          </div>
                          <div class="col-md-4 column">
                              <a href="#" style="color: rgb(152, 151, 151);"><span class="glyphicon glyphicon-thumbs-up" style="color: rgb(152, 151, 151); font-size: 6px;"> </span>&nbsp;点赞(${lemonContent.likeCount})</a>
                              <#--<a href="#"><span>评论</span></a>-->
                              &nbsp;&nbsp;
                              <a href="#" style="color: rgb(152, 151, 151);"><span class="glyphicon glyphicon-heart" style="color: rgb(152, 151, 151); font-size: 6px;"> </span>&nbsp;收藏(${lemonContent.collectCount})</a>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
          <div class="col-md-1 column">
          </div>
      </div>
      </#list>
  </div>

  </body>
</html>
