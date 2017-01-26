<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
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
    <link rel="stylesheet" type="text/css" href="/js/webuploader/webuploader.css"/>
    <!-- 可选的Bootstrap主题文件（一般不使用） -->
    <link src="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></link>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap-datetimepicker.zh-CN.js"></script>
    <style>
        img{
            width: 100%;
            height: 200px;
        }
        .title{
            text-align: center;
            font-size: 36px;
            font-family: Algerian;
            margin-top: 89px;
            margin-bottom: 20px;
        }
       .introduce p {
           text-align: justify;
           text-indent:30px;
           line-height: 3;
           letter-spacing: 2px;
           border: 2px solid #d7d7d7;
           border-radius: 4px;
           padding: 10px;
       }

    </style>
</head>
<body ng-app="myApp" ng-controller="lemonAboutUs">

<!--header-->
<#assign currentPage = "add">
<#include "/lemon/common/head.ftl">
<body>
<div class="container">
    <!--<div class="col-md-12 column">-->
    <div class="row">
        <div class="col-md-2 column">
        </div>
        <div class="col-md-8 column">
            <div class="col-md-12 column">
                <div class="title">
                    <p>about us</p>
                </div>
            </div>
        </div>
        <div class="col-md-2 column">
        </div>
    </div>
    <div class="row">
        <div class="col-md-2 column">
        </div>
        <div class="col-md-8 column">
            <div class="col-md-12 column">
                <div class="introduce">
                    <p>我们是一个积极向上的团队，每一点的收获都离不开彼此的合作。我们是一个积极向上的团队，每一点的收获都离不开彼此的合作我们是一个积极向上的团队，每一点的收获都离不开彼此的合作。我们是一个积极向上的团队，每一点的收获都离不开彼此的合作。我们是一个积极向上的团队，每一点的收获都离不开彼此的合作我们是一个积极向上的团队，每一点的收获都离不开彼此的合作。</p>
                </div>
            </div>
        </div>
        <div class="col-md-2 column">
        </div>
    </div>

</div>
<script type="text/javascript" src="/js/webuploader/webuploader.html5only.min.js"></script>
</body>
</html>