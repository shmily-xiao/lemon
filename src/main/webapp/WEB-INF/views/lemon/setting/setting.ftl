<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>个人中心</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- 可选的Bootstrap主题文件（一般不使用） -->
    <link src="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></link>

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>

        /*为了不闪烁*/
        [ng\:cloak], [ng-cloak], [data-ng-cloak], [x-ng-cloak], .ng-cloak, .x-ng-cloak {
            display: none !important;
        }

    </style>
</head>

<body ng-app="myApp" ng-controller="personalCenter">

<!-- header -->
<#assign currentPage = "others">
<#include "/lemon/common/head.ftl">
<!-- header end -->

<div class="container">
    <div class="row" style="margin-top: 100px;padding: 5px;margin-bottom: 16px;">
        <div class="col-md-3 column">
        <div class="col left-column">
            <div class="design" id="leftcolumn">
                <div class="sidebar-box gallery-list">
                    <ul class="list-group">
                        <li class="list-group-item" style="text-align:center">
                            <!-- 利用data-target指定URL -->
                            <button class="menu-item-left" >
                                <span>修改资料</span>
                            </button>
                        </li>
                        <li class="list-group-item" style="text-align:center">
                            <button class="menu-item-left" >
                                <span>修改密码</span>
                            </button>
                        </li>
                        <li class="list-group-item" style="text-align:center">
                            <!-- 利用data-target指定URL -->
                            <button class="menu-item-left" >
                                <span>隐私设置</span>
                            </button>
                        </li>
                        <li class="list-group-item" style="text-align:center">
                            <button class="menu-item-left" style="width: 72px;">
                                <span>反馈</span>
                            </button>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        </div>
        <div class="col-md-9 column">
        <div class="col middle-column">
            第三方十多个
        </div>
        </div>
    </div>
</div>
</body>
</html>
