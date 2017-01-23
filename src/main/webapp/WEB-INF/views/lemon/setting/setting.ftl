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
    <!--日历插件css-->
    <link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet">

    <!--上传插件样式-->
    <link rel="stylesheet" type="text/css" href="/js/webuploader/webuploader.css"/>

    <!-- 可选的Bootstrap主题文件（一般不使用） -->
    <link src="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></link>

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <!--日历插件js-->
    <script type="text/javascript" src="/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap-datetimepicker.zh-CN.js"></script>
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
                        <li class="list-group-item" style="text-align:center;color: black;">
                            <!-- 利用data-target指定URL -->
                            <button class="menu-item-left" >
                                <span>修改资料</span>
                            </button>
                        </li>
                        <li class="list-group-item" style="text-align:center;color: black;">
                            <button class="menu-item-left" >
                                <span>修改密码</span>
                            </button>
                        </li>
                        <li class="list-group-item" style="text-align:center;color: black;">
                            <!-- 利用data-target指定URL -->
                            <button class="menu-item-left" >
                                <span>隐私设置</span>
                            </button>
                        </li>
                        <li class="list-group-item" style="text-align:center;color: black;">
                            <button class="menu-item-left" style="width: 72px;">
                                <span>反馈</span>
                            </button>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        </div>
        <div class="col-md-9 column" style="display: none">
            <div class="col-md-12 column">
                <div class="col-md-3 column">
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
                    <div style="margin-left: 35px; margin-top: 5px;margin-bottom: 5px"><span>头像</span></div>
                </div>
                <div class="col-md-6 column">
                    <div class="input-group" style="margin-bottom: 9px">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                昵称
                            </button>
                        </span>
                        <input type="text" class="form-control" style="width: 250px;">
                    </div><!-- /input-group -->
                    <div class="input-group" style="margin-bottom: 9px">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                名字
                            </button>
                        </span>
                        <input type="text" class="form-control" style="width: 250px;">
                    </div><!-- /input-group -->
                    <div style="margin-bottom: 9px;">
                        <div class="input-group input-group-md" style="margin-top: 10px; float: left">
                            性别
                        </div>
                        <label class="checkbox-inline" style="margin-top: 10px;">
                            <input type="radio" name="sex" ng-model="postData.sex"  value="true" > 男
                        </label>
                        <label class="checkbox-inline" style="margin-top: 10px;">
                            <input type="radio" name="sex" ng-model="postData.sex"  value="false" checked> 女
                        </label>
                    </div>
                    <div class="input-group input-group-md" style="margin-bottom: 9px;">
                        <span class="input-group-addon">生日</span>
                        <input style="height: 34px;width: 250px; text-align:center" type="text" value="" readOnly="true" id="datetimepicker" ng-model="postData.finishedTime" data-date-format="yyyy-mm-dd">
                        </input>
                    </div>
                    <div class="input-group" style="margin-bottom: 9px">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                邮箱
                            </button>
                        </span>
                        <input type="text" class="form-control" style="width: 250px;">
                    </div><!-- /input-group -->

                    <div class="input-group" style="margin-bottom: 9px">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                简介
                            </button>
                        </span>
                        <input type="text" class="form-control" style="width: 250px;">
                    </div><!-- /input-group -->
                </div>

            </div>
            <div class="col-md-12 column">
                <div class="col-md-10 column"></div>
                <div class="col-md-2 column" style="text-align: right;">
                    <button type="button" class="btn btn-default" >更&nbsp;&nbsp;新</button>
                </div>
            </div>
        </div>
        <div class="col-md-9 column" style="display: none">
            <div class="col-md-12 column">
                <div class="col-md-6 column">
                    <div class="input-group" style="margin-bottom: 9px">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;原密码
                            </button>
                        </span>
                        <input type="text" class="form-control" style="width: 250px;">
                    </div><!-- /input-group -->
                    <div class="input-group" style="margin-bottom: 9px">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;新密码
                            </button>
                        </span>
                        <input type="text" class="form-control" style="width: 250px;">
                    </div><!-- /input-group -->
                    <div class="input-group" style="margin-bottom: 9px">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                确认密码
                            </button>
                        </span>
                        <input type="text" class="form-control" style="width: 250px;">
                    </div><!-- /input-group -->

                </div>

            </div>
            <div class="col-md-12 column">
                <div class="col-md-10 column"></div>
                <div class="col-md-2 column" style="text-align: right;">
                    <button type="button" class="btn btn-default" >更&nbsp;&nbsp;新</button>
                </div>
            </div>
        </div>
        <div class="col-md-9 column" style="display: none">
            <div class="col-md-12 column">
                <div class="col-md-6 column">
                    <div class="input-group" style="margin-bottom: 9px">
                        <span class="input-group-addon">内容查看权限</span>
                        <select style="height: 34px;width: 151px;">
                            <option value="public" >公开</option>
                            <option value="private" >关闭</option>
                            <option value="friend" >对好友可见</option>
                        </select>
                    </div><!-- /input-group -->

                </div>

            </div>
            <div class="col-md-12 column">
                <div class="col-md-10 column"></div>
                <div class="col-md-2 column" style="text-align: right;">
                    <button type="button" class="btn btn-default" >更&nbsp;&nbsp;新</button>
                </div>
            </div>
        </div>
        <div class="col-md-9 column">
            <div class="col-md-12 column">
                <div class="col-md-6 column">
                    <div class="panel-body">
                        <div class="form-group">
                            <textarea class="form-control"  rows="10" style="resize:none;"
                                      placeholder="在这里写下您宝贵的意见并且留下联系方式，或者发邮件到wangzaijun1234@126.com。我将尽快与您联系。"></textarea>
                        </div>
                    </div>

                </div>

            </div>
            <div class="col-md-12 column">
                <div class="col-md-10 column"></div>
                <div class="col-md-2 column" style="text-align: right;">
                    <button type="button" class="btn btn-default" >提&nbsp;&nbsp;交</button>
                </div>
            </div>
        </div>

    </div>
</div>
<script>
    $.fn.datetimepicker.dates['zh'] = {
        days:       ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六","星期日"],
        daysShort:  ["日", "一", "二", "三", "四", "五", "六","日"],
        daysMin:    ["日", "一", "二", "三", "四", "五", "六","日"],
        months:     ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月","十二月"],
        monthsShort:  ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"],
        meridiem:    ["上午", "下午"],
        //suffix:      ["st", "nd", "rd", "th"],
        today:       "今天"
    };
    $('#datetimepicker').datetimepicker({
        language:  'zh',
        format: "yyyy-mm-dd",
        minView: "month",//设置只显示到月份
        weekStart: 1,
        todayHighlight: 1,
        todayBtn: false,//今日按钮
        startView: 'year', /*日期时间选择器打开之后首先显示的视图。 2 or 'month' for month view (the default)*/
        forceParse: 0,/*当选择器关闭的时候，是否强制解析输入框中的值。*/
        showMeridian: 0 ,/*选项里是否有天或小时*/
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
    app.controller('personalCenter',['$scope','$http',function($scope,$http) {



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
