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

<body ng-app="myApp" ng-controller="lemonAdd">

<!--header-->
<#assign currentPage = "add">
<#include "/lemon/common/head.ftl">
<!--header end-->


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
                                    <input type="text" class="form-control" ng-model="postData.title" placeholder="标题">
                                </div>
                                <div class="panel-body">
                                    <div class="form-group">
                                        <textarea class="form-control" ng-model="postData.content" rows="10" style="resize:none;"></textarea>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12 column" style="height: 105px;">
                                <div style="float:left;margin-top: 10px;" class="img_box" ng-mouseenter="picShow = true" ng-mouseleave="picShow = false" ng-cloak="" ng-repeat="pic in postData.images">
                                    <div class="img_box_hidden">
                                        <img style="height: 80.5px;width: 100px;" ng-src="{{pic}}"/>
                                    </div>
                                    <div class="del_img" ng-click="delDetailImg($index)" ng-show="picShow"></div>
                                </div>
                                <div style="position: relative;margin-top:10px;box-sizing:border-box;height:80.5px;width:100px;cursor:pointer;background:url(/img/img_add.png) no-repeat center;background-size:contain;">
                                    <#--<label for="upload" style="display:block;width:100%;height:100%"></label>-->
                                    <#--<input type="file" id="upload" name="image" ng-model="image" class="webuploader-element-invisible" multiple="multiple" accept="image/gif,image/jpg,image/jpeg,image/bmp,image/png">-->
                                        <div ng-show="postData.images.length &lt; 2" id="upload"></div>
                                </div>
                                <#--<div ng-show="postData.images.length &lt; 9" class="img_add"></div>-->
                            </div>

                            <div class="btn-group">
                                <div class="col-md-6 column" style="margin-top: 4px;margin-bottom: 4px;">
                                <div class="input-group input-group-md">
                                    <span class="input-group-addon">选择类型</span>
                                    <select style="height: 34px;width: 151px;" ng-model="postData.contentsType">
                                        <#list lemonAddView.contentsType as contentsType>
                                            <option value="${contentsType.value}" <#if contentsType.selected>selected="selected"</#if>>${contentsType.name}</option>
                                        </#list>
                                    </select>

                                </div>
                                </div>
                                <div class="col-md-6 column" style="margin-top: 4px; margin-bottom: 4px;" >
                                <div class="input-group input-group-md">
                                    <span class="input-group-addon">完成时间</span>
                                    <input style="height: 34px;width: 151px;" type="text" value="" readOnly="true" id="datetimepicker" ng-model="postData.finishedTime" data-date-format="yyyy-mm-dd hh:ii">
                                    </input>
                                </div>
                                </div>
                                <div class="col-md-6 column">
                                    <div class="input-group input-group-md" style="margin-top: 10px; float: left">
                                        短息提醒
                                    </div>
                                    <label class="checkbox-inline" style="margin-top: 10px;">
                                        <input type="radio" name="needMessage" ng-model="postData.needMessage"  value="true" > 需要
                                    </label>
                                    <label class="checkbox-inline" style="margin-top: 10px;">
                                        <input type="radio" name="needMessage" ng-model="postData.needMessage"  value="false" checked> 不需要
                                    </label>
                                </div>
                                <div class="col-md-4 column" style="margin-top: 4px; margin-bottom: 4px;">
                                <div class="input-group input-group-md">
                                    <span class="input-group-addon">可见范围</span>
                                    <select style="height: 34px;width: 151px;" ng-model="postData.strategyType">
                                        <#list lemonAddView.strategyTypes as strategyType>
                                            <option value="${strategyType.value}" <#if strategyType.selected>selected="selected"</#if>>${strategyType.name}</option>
                                        </#list>
                                    </select>
                                </div>
                                </div>
                            </div>
                            <div class="col-md-10 column"></div>
                            <div class="col-md-2 column" style="text-align: right;">
                                <button type="button" class="btn btn-default" ng-click="addLemon($event)">发表记录</button>
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
<script src="/js/angular.min.js"></script>
<script type="text/javascript" src="/js/webuploader/webuploader.html5only.min.js"></script>
<script>
    function generateMixed(n) {
        var jschars = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
        var res = "";
        for (var i = 0; i < n; i++) {
            var id = Math.ceil(Math.random() * 35);
            res += jschars[id];
        }
        return res;
    }
    var app = angular.module('myApp', []);
    app.controller('lemonAdd',['$scope','$http',function($scope,$http) {
        $scope.postData={
            title:'',
            content:'',
            finishedTime:'',
            contentsType:'DREAM',
            needMessage:'false',
            strategyType:'PUBLIC',
            images:[]
        };
        $scope.addLemon = function (event) {
            if ($scope.postData.images.length>2){
                alert("图片上传超过上限");
            }
            $http.post('/lemon/lemons/add', $scope.postData).success(function (data) {
                if (data.code == 0) {
                    top.location.href = data.url;
                } else if (data.code == 2) {
                    $scope.empty = true;
                }else {
                    alert("网络异常");
                }
            }).error(function () {
                alert("网络异常");
            });
        };



        //--------------------------------------------------------//
        //-------------------- 图片上传的插件 ----------------------//
        //--------------------------start-------------------------//
        var uploader = WebUploader.create({
            auto: true,
            // 文件接收服务端。
            server: '/lemon/image/add',
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: angular.element('#upload'),
            formData: {"randomCode": generateMixed(16)},
            duplicate : true,
            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: 'png,jpg,jpeg',//'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/png,image/jpg,image/jpeg'//'image/gif,image/jpg,image/jpeg,image/bmp,image/png'
            },
            //限制文件的大小
            fileSingleSizeLimit: 5 * 1024 * 1024
        });
        uploader.on('uploadStart',function(){
            $scope.disabled = true;
            $scope.$apply();
        });
        uploader.on( 'uploadSuccess', function( file , response) {
            if(response.code == 0){
                $scope.postData.images=[];//只能添加一张照片
                $scope.postData.images.push(response.data);
                $scope.$apply();
            }else{
                alert(response.msg);
            }
        });
        uploader.on('error',function() {
            alert('error!')
        });
        //删除图片
        $scope.delDetailImg = function(index){
            $scope.postData.images.splice(index,1);
        };
        //-------------------------end----------------------------//
        //-------------------- 图片上传的插件 ----------------------//
        //--------------------------------------------------------//
    }]);
</script>

</body>
</html>
