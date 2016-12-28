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

      </style>
  </head>

  <body>

  <!--header-->
  <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="container">
          <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                  <span class="sr-only">Toggle navigation</span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
              </button>
              <a class="avatar"><img src="/img/user48.ico"></a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
              <ul class="nav navbar-nav">
                  <li class="active"><a href="#">首页</a></li>
                  <li><a href="#about">个人中心</a></li>
                  <li><a href="#contact">好友</a></li>
                  <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown">更多 <span class="caret"></span></a>
                      <ul class="dropdown-menu" role="menu">
                          <li><a href="#">敬请期待</a></li>
                          <li class="divider"></li>
                          <li class="dropdown-header">友情链接</li>
                          <li><a href="#">Separated link</a></li>
                          <li><a href="#">One more separated link</a></li>
                      </ul>
                  </li>
              </ul>
              <ul class="nav navbar-nav navbar-right">
                  <li ><a href="#"><span>成就值：999</span></a></li>
                  <li ><a href="#"><span>等级：新手</span></a></li>
                  <li ><a href="#">记录<span class="glyphicon glyphicon-plus" style="color: rgb(152, 151, 151); font-size: 6px;"></span></a></li>
                  <li ><a href="#">签到<span class="glyphicon glyphicon-map-marker" style="color: rgb(152, 151, 151); font-size: 6px;"></span></a></li>
              </ul>
          </div><!--/.nav-collapse -->
      </div>
  </nav><!--header end-->

  <div class="container">
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
                              <p>昵称：nickname</p>
                          </div>
                          <div class="col-md-4 column">
                              <p>创建时间：2016-12-27</p>
                          </div>
                          <div class="col-md-4 column">
                              <p>完成时间：2017-4-5</p>
                          </div>
                      </div>
                    <div class="col-md-12 column" style="margin-top: -12px;">
                        <h2>
                            Heading
                        </h2>
                        <p>
                            Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.
                        </p>
                    </div>

                      <div class="row clearfix">
                          <div class="col-md-4 column">

                          </div>
                          <div class="col-md-4 column">
                          </div>
                          <div class="col-md-4 column">
                              <a href="#" style="color: rgb(152, 151, 151);"><span class="glyphicon glyphicon-thumbs-up" style="color: rgb(152, 151, 151); font-size: 6px;"> </span>&nbsp;点赞(10)</a>
                              <#--<a href="#"><span>评论</span></a>-->
                              &nbsp;&nbsp;
                              <a href="#" style="color: rgb(152, 151, 151);"><span class="glyphicon glyphicon-heart" style="color: rgb(152, 151, 151); font-size: 6px;"> </span>&nbsp;收藏(20)</a>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
          <div class="col-md-1 column">
          </div>
      </div>
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
                              <p>昵称：nickname</p>
                          </div>
                          <div class="col-md-4 column">
                              <p>创建时间：2016-12-27</p>
                          </div>
                          <div class="col-md-4 column">
                              <p>完成时间：2017-4-5</p>
                          </div>
                      </div>
                      <div class="col-md-12 column" style="margin-top: -12px;">
                          <h2>
                              Heading
                          </h2>
                          <p>
                              Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.
                          </p>
                      </div>

                      <div class="row clearfix">
                          <div class="col-md-4 column">

                          </div>
                          <div class="col-md-4 column">
                          </div>
                          <div class="col-md-4 column">
                              <a href="#" style="color: rgb(152, 151, 151);"><span class="glyphicon glyphicon-thumbs-up" style="color: rgb(152, 151, 151); font-size: 6px;"> </span>&nbsp;点赞(10)</a>
                          <#--<a href="#"><span>评论</span></a>-->
                              &nbsp;&nbsp;
                              <a href="#" style="color: rgb(152, 151, 151);"><span class="glyphicon glyphicon-heart" style="color: rgb(152, 151, 151); font-size: 6px;"> </span>&nbsp;收藏(20)</a>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
          <div class="col-md-1 column">
          </div>
      </div>
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
                              <p>昵称：nickname</p>
                          </div>
                          <div class="col-md-4 column">
                              <p>创建时间：2016-12-27</p>
                          </div>
                          <div class="col-md-4 column">
                              <p>完成时间：2017-4-5</p>
                          </div>
                      </div>
                      <div class="col-md-12 column" style="margin-top: -12px;">
                          <h2>
                              Heading
                          </h2>
                          <p>
                              Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.
                          </p>
                      </div>

                      <div class="row clearfix">
                          <div class="col-md-4 column">

                          </div>
                          <div class="col-md-4 column">
                          </div>
                          <div class="col-md-4 column">
                              <a href="#" style="color: rgb(152, 151, 151);"><span class="glyphicon glyphicon-thumbs-up" style="color: rgb(152, 151, 151); font-size: 6px;"> </span>&nbsp;点赞(10)</a>
                          <#--<a href="#"><span>评论</span></a>-->
                              &nbsp;&nbsp;
                              <a href="#" style="color: rgb(152, 151, 151);"><span class="glyphicon glyphicon-heart" style="color: rgb(152, 151, 151); font-size: 6px;"> </span>&nbsp;收藏(20)</a>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
          <div class="col-md-1 column">
          </div>
      </div>
  </div>

  </body>
</html>
