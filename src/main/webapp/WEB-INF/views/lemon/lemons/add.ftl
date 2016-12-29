<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>首页</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <!-- 可选的Bootstrap主题文件（一般不使用） -->
    <link src="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></link>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap-datetimepicker.zh-CN.js"></script>
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
                <li ><a href="#">首页</a></li>
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
                <li class="active"><a href="#">记录<span class="glyphicon glyphicon-plus" style="color: rgb(152, 151, 151); font-size: 6px;"></span></a></li>
                <li ><a href="#">签到<span class="glyphicon glyphicon-map-marker" style="color: rgb(152, 151, 151); font-size: 6px;"></span></a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav><!--header end-->


<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="row clearfix">
                <div class="col-md-2 column">
                </div>
                <div class="col-md-8 column">

                    <div class="col-md-12 column" style="margin-top: 53px;">
                        <form class="bs-example bs-example-form" role="form">
                            <div class="panel panel-default">

                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon">标题</span>
                                    <input type="text" class="form-control" placeholder="标题">
                                </div>
                                <div class="panel-body">
                                    <div class="form-group">
                                        <textarea class="form-control" rows="10" style="resize:none;"></textarea>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12 column">
                            <div class="form-group">
                                <label for="exampleInputFile">上传图片</label><input type="file" id="exampleInputFile" />
                                <p class="help-block">
                                    最多可上传九张图片
                                </p>
                            </div>
                            </div>

                            <div class="btn-group">
                                <div class="col-md-6 column">
                                <div class="input-group input-group-md">
                                    <span class="input-group-addon">选择类型</span>
                                    <select>
                                        <option>想做的事</option>
                                        <option>琐碎的事</option>
                                        <option>抒情的话</option>
                                    </select>
                                </div>
                                </div>
                                <div class="col-md-6 column">
                                <div class="input-group input-group-md">
                                    <span class="input-group-addon">完成的时间</span>
                                    <input type="text" value="" id="datetimepicker" data-date-format="yyyy-mm-dd hh:ii">
                                    </input>
                                </div>
                                </div>
                                <div class="col-md-3 column">
                                <p >是否需要短息提醒</p>
                                </div>

                                <div class="col-md-4 column">
                                <label class="checkbox-inline">
                                    <input type="radio" name="optionsRadiosinline" id="optionsRadios3" value="option1" > 是
                                </label>
                                <label class="checkbox-inline">
                                    <input type="radio" name="optionsRadiosinline" id="optionsRadios4"  value="option2" checked> 否
                                </label>
                                </div>
                                <div class="col-md-4 column">
                                <div class="input-group input-group-md">
                                    <span class="input-group-addon">可见范围</span>
                                    <select>
                                        <option>公开</option>
                                        <option>私有</option>
                                        <option>对好友可见</option>
                                    </select>
                                </div>
                                </div>
                            </div>
                            <div class="col-md-10 column"></div>
                            <div class="col-md-2 column">
                                <button type="submit" class="btn btn-default">发表记录</button>
                            </div>
                        </form>


                    </div>
                </div>
                <div class="col-md-2 column">
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $('#datetimepicker').datetimepicker({
        format: "yyyy-mm-dd hh:ii",
        weekStart: 1,
        todayHighlight: 1,
        todayBtn: true,
        startView: 2, /*日期时间选择器打开之后首先显示的视图。 2 or 'month' for month view (the default)*/
        forceParse: 0,/*当选择器关闭的时候，是否强制解析输入框中的值。*/
        showMeridian: 1 ,/*选项里是否有天或小时*/
        hourStep: 1,
        minuteStep: 1,
        autoclose:true
    });

</script>

</body>
</html>
