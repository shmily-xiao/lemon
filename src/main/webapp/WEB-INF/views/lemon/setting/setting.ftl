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

    <!--上传图片插件样式-->
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

    <script src="/js/angular.min.js"></script>
    <!--上传图片插件Js-->
    <script type="text/javascript" src="/js/webuploader/webuploader.html5only.min.js"></script>

    <script src="/js/md5.js"></script>

    <style>
        /*为了不闪烁*/
        [ng\:cloak], [ng-cloak], [data-ng-cloak], [x-ng-cloak], .ng-cloak, .x-ng-cloak {
            display: none !important;
        }
        .list-group-item.active{
            background-color: #98bbde;
            border: none;
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
                        <li class="list-group-item" ng-class="{'active':!modifyDataStyle}" ng-cloak="" style="text-align:center;color: black;">
                            <!-- 利用data-target指定URL -->
                            <button class="menu-item-left"  ng-click="modifyData(event)">
                                <span>修改资料</span>
                            </button>
                        </li>
                        <li class="list-group-item" ng-class="{'active':!modifyPasswordStyle}" ng-cloak="" style="text-align:center;color: black;" >
                            <button class="menu-item-left" ng-click="modifyPassword(event)">
                                <span>修改密码</span>
                            </button>
                        </li>
                        <li class="list-group-item" ng-class="{'active':!modifyPrivacyStyle}" ng-cloak="" style="text-align:center;color: black;" >
                            <!-- 利用data-target指定URL -->
                            <button class="menu-item-left" ng-click="modifyPrivacy(event)" >
                                <span>隐私设置</span>
                            </button>
                        </li>
                        <li class="list-group-item" ng-class="{'active':!feedbackStyle}" ng-cloak="" style="text-align:center;color: black;" >
                            <button class="menu-item-left" style="width: 72px;" ng-click="feedback(event)">
                                <span>反馈</span>
                            </button>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        </div>
        <div class="col-md-9 column" style="{{modifyDataStyle}}" ng-cloak="">
            <div class="col-md-12 column">
                <div class="col-md-3 column">
                    <div style="float:left;margin-top: 10px;" class="img_box" ng-mouseenter="picShow = true" ng-mouseleave="picShow = false" ng-cloak="">
                        <div ng-hide="modifyDataPostData.avatar==''">
                            <img style="height: 80.5px;width: 100px;" ng-src="{{modifyDataPostData.avatar}}"/>
                        </div>
                        <div class="del_img" ng-click="delDetailImg($index)" ng-show="picShow"></div>
                    </div>
                    <div style="margin-bottom: 14px;position: relative;margin-top:10px;box-sizing:border-box;height:80.5px;width:100px;cursor:pointer;background:url(/img/img_add.png) no-repeat center;background-size:contain;">
                        <div id="upload"></div>
                    </div>
                    <div style="margin-left: 35px; margin-top: 5px;margin-bottom: 5px"><span>头像</span></div>
                </div>
                <div class="col-md-6 column">
                    <div id="account_error" class="line-alert text-danger" ng-cloak ng-class="{'hidden':(modifyDataPostData.nickname)}">
                        <span class="error line-alert text-danger" >昵称字数为2到10个</span>
                    </div>
                    <div class="input-group" style="margin-bottom: 9px">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                昵称
                            </button>
                        </span>
                        <input type="text" class="form-control"
                               name="nickname"
                               ng-model="modifyDataPostData.nickname"
                               ng-trim='false'
                               ng-minlength=2
                               ng-maxlength=10
                               placeholder="请输入昵称" />
                    </div><!-- /input-group -->
                    <div id="account_error" class="line-alert text-danger" ng-cloak ng-class="{'hidden':(modifyDataPostData.name)}">
                        <span class="error line-alert text-danger" >名字填写不正确</span>
                    </div>
                    <div class="input-group" style="margin-bottom: 9px">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                名字
                            </button>
                        </span>
                        <input type="text" class="form-control"
                               ng-model="modifyDataPostData.name"
                               ng-trim='false'
                               ng-minlength=2
                               ng-maxlength=10
                               placeholder="请输入您的姓名" >
                    </div><!-- /input-group -->
                    <div id="account_error" class="line-alert text-danger" ng-cloak ng-class="{'hidden':(modifyDataPostData.gender)}">
                        <span class="error line-alert text-danger" >请选择性别</span>
                    </div>
                    <div style="margin-bottom: 9px;">
                        <div class="input-group input-group-md" style="margin-top: 10px; float: left">
                            性别
                        </div>
                        <label class="checkbox-inline" style="margin-top: 10px;">
                            <input type="radio" name="sex" ng-model="modifyDataPostData.gender"  value="Male" > 男
                        </label>
                        <label class="checkbox-inline" style="margin-top: 10px;">
                            <input type="radio" name="sex" ng-model="modifyDataPostData.gender"  value="Female"> 女
                        </label>
                    </div>
                    <div id="account_error" class="line-alert text-danger" ng-cloak ng-class="{'hidden':(modifyDataPostData.birthday)}">
                        <span class="error line-alert text-danger" >请选择生日</span>
                    </div>
                    <div class="input-group input-group-md" style="margin-bottom: 9px;">
                        <span class="input-group-addon">生日</span>
                        <input class="form-control" style="text-align:center"
                               type="text" value="" readOnly="true" id="datetimepicker"
                               ng-model="modifyDataPostData.birthday" data-date-format="yyyy-mm-dd">
                        </input>
                    </div>
                    <div id="account_error" class="line-alert text-danger" ng-cloak ng-class="{'hidden':(modifyDataPostData.email)}">
                        <span class="error line-alert text-danger" >请输入正确的邮箱地址，这将用于找回您的密码</span>
                    </div>
                    <div class="input-group" style="margin-bottom: 9px">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                邮箱
                            </button>
                        </span>
                        <input type="text" class="form-control"
                               ng-model="modifyDataPostData.email"
                               placeholder="请输入您的邮箱"/>
                    </div><!-- /input-group -->
                    <div id="account_error" class="line-alert text-danger" ng-cloak="" ng-class="{'hidden':(modifyDataPostData.profile)}">
                        <span class="error line-alert text-danger" >不超过30个字符</span>
                    </div>
                    <div class="input-group" style="margin-bottom: 9px">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                简介
                            </button>
                        </span>
                        <input type="text" class="form-control" ng-model="modifyDataPostData.profile"
                               ng-maxlength=30
                               placeholder="请输入您的简介" required/>
                    </div><!-- /input-group -->
                </div>

            </div>
            <div class="col-md-12 column">
                <div class="col-md-10 column"></div>
                <div class="col-md-2 column" style="text-align: right;">
                    <button type="button" class="btn btn-default" ng-click="updateModifyData(event)">更&nbsp;&nbsp;新</button>
                </div>
            </div>
        </div>
        <div class="col-md-9 column" style="{{modifyPasswordStyle}}" ng-cloak="">
            <div class="col-md-12 column">
                <div class="col-md-6 column">
                    <div class="input-group" style="margin-bottom: 9px">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                &nbsp;&nbsp;&nbsp;&nbsp;原密码
                            </button>
                        </span>
                        <input class="form-control" ng-model="modifyPasswordPostData.oldPassword"
                               ng-minlength=8
                               ng-maxlength=100
                               type="password"/>
                    </div><!-- /input-group -->
                    <div class="input-group" style="margin-bottom: 9px">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                &nbsp;&nbsp;&nbsp;&nbsp;新密码
                            </button>
                        </span>
                        <input class="form-control" ng-model="modifyPasswordPostData.newPassword"
                               ng-minlength=8
                               ng-maxlength=100
                               type="password"/>
                    </div><!-- /input-group -->
                    <div id="account_error" class="line-alert text-danger"
                         ng-cloak ng-class="{'hidden':(modifyPasswordPostData.newPassword==modifyPasswordPostData.newPasswordConfirm)}">
                        <span class="error line-alert text-danger" >两次密码不同</span>
                    </div>
                    <div id="account_error" class="line-alert text-danger"
                         ng-cloak ng-class="{'hidden':!(modifyPasswordPostData.newPasswordConfirm.length<8||modifyPasswordPostData.newPasswordConfirm.length>100)}">
                        <span class="error line-alert text-danger" >密码至少8位数且不能超过100位</span>
                    </div>
                    <div class="input-group" style="margin-bottom: 9px">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                确认密码
                            </button>
                        </span>
                        <input class="form-control" ng-model="modifyPasswordPostData.newPasswordConfirm"
                               name="password"
                               type="password" />
                    </div><!-- /input-group -->

                </div>

            </div>
            <div class="col-md-12 column">
                <div class="col-md-10 column"></div>
                <div class="col-md-2 column" style="text-align: right;">
                    <button type="button" class="btn btn-default" ng-click="updateModifyPassword(event)">更&nbsp;&nbsp;新</button>
                </div>
            </div>
        </div>
        <div class="col-md-9 column" style="{{modifyPrivacyStyle}}" ng-cloak="">
            <div class="col-md-12 column">
                <div class="col-md-6 column">
                    <div class="input-group" style="margin-bottom: 9px">
                        <span class="input-group-addon">内容查看权限</span>
                        <select style="height: 34px;width: 151px;" ng-model="modifyPrivacyPostData.zoneStatus">
                            <option value="PUBLIC" >公开</option>
                            <option value="PRIVATE" >关闭</option>
                            <option value="FRIENDSHIP" >对好友可见</option>
                        </select>
                    </div><!-- /input-group -->
                </div>
            </div>
            <div class="col-md-12 column">
                <div class="col-md-10 column"></div>
                <div class="col-md-2 column" style="text-align: right;">
                    <button type="button" class="btn btn-default" ng-click="updateModifyPrivacyPostData(event)">更&nbsp;&nbsp;新</button>
                </div>
            </div>
        </div>
        <div class="col-md-9 column" style="{{feedbackStyle}}" ng-cloak="">
        <div class="col-md-12 column">
            <div class="col-md-8 column">
                <div class="panel-body">
                    <div class="form-group">
                        <textarea class="form-control"  ng-model="feedbackPostData.content" rows="10" style="resize:none;"
                                  placeholder="在这里写下您宝贵的意见并且留下联系方式，或者发邮件到wangzaijun1234@126.com。我将尽快与您联系。"></textarea>
                    </div>
                </div>

            </div>

        </div>
        <div class="col-md-12 column">
            <div class="col-md-10 column"></div>
            <div class="col-md-2 column" style="text-align: right;">
                <button type="button" class="btn btn-default" ng-click="sendFeedbackPostData(event)">提&nbsp;&nbsp;交</button>
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
        $scope.picShow=false;
        $scope.modifyDataPostData={
            avatar:'${user.avatar}'||'',
            gender:'${user.gender}'||'',
            name:'${user.name}'||'',
            nickname:'${user.nickName}'||'',
            birthday:'${user.birthday}'||'1992-09-24',
            email:'${user.email}'||'',
            profile:'${user.profile}'||''
        };

        $scope.modifyPasswordPostData={
            oldPassword:'',
            newPassword:'',
            newPasswordConfirm:''
        };


        $scope.modifyPrivacyPostData={
            zoneStatus:'PUBLIC'
        };

        $scope.feedbackPostData={
            content:''
        };

        $scope.modifyDataStyle = '';
        $scope.modifyPasswordStyle = 'display:none';
        $scope.modifyPrivacyStyle = 'display:none';
        $scope.feedbackStyle = 'display:none';

        $scope.modifyData = function(event){
            $scope.modifyDataStyle = '';
            $scope.modifyPasswordStyle = 'display:none';
            $scope.modifyPrivacyStyle = 'display:none';
            $scope.feedbackStyle = 'display:none';
        };
        $scope.modifyPassword = function(event){

            $scope.modifyDataStyle = 'display:none';
            $scope.modifyPasswordStyle = '';
            $scope.modifyPrivacyStyle = 'display:none';
            $scope.feedbackStyle = 'display:none';
        };
        $scope.modifyPrivacy = function(event){
            $scope.modifyDataStyle = 'display:none';
            $scope.modifyPasswordStyle = 'display:none';
            $scope.modifyPrivacyStyle = '';
            $scope.feedbackStyle = 'display:none';
        };
        $scope.feedback = function(event){
            $scope.modifyDataStyle = 'display:none';
            $scope.modifyPasswordStyle = 'display:none';
            $scope.modifyPrivacyStyle = 'display:none';
            $scope.feedbackStyle = '';
        };

        // 修改资料
        $scope.updateModifyData = function(event){

            if ($scope.modifyDataPostData.avatar==''||$scope.modifyDataPostData.birthday==''
                    ||$scope.modifyDataPostData.email==''||$scope.modifyDataPostData.gender==''
                    ||$scope.modifyDataPostData.name==''||$scope.modifyDataPostData.nickname==''
                    ||$scope.modifyDataPostData.profile.length>30){
                alert("请检查表单填写");
                return;
            }
            $http.post('/lemon/personal/center/modify/information',$scope.modifyDataPostData).success(function(data){
                alert(data.msg);
            }).error(function(){
                alert("网络异常，请稍后再试！");
            });
        };
        // 修改密码
        $scope.updateModifyPassword= function(event){
            if ($scope.modifyPasswordPostData.newPassword!=$scope.modifyPasswordPostData.newPasswordConfirm){
                alert("两次输入的密码不一致");
                return;
            }
            $scope.password={
                oldPassword:'',
                newPassword:''
            };
            $scope.password.oldPassword=hex_md5($scope.modifyPasswordPostData.oldPassword.trim());
            $scope.password.newPassword=hex_md5($scope.modifyPasswordPostData.newPassword.trim());

            $http.post('/lemon/personal/center/modify/password',$scope.password).success(function(data){
                alert(data.msg);
            }).error(function(){
                alert("网络异常，请稍后再试！");
            });
        };
        // 修改隐私设置
        $scope.updateModifyPrivacyPostData=function (event) {
            if ($scope.modifyPrivacyPostData.zoneStatus==''){
                alert("表单填写完整！");
                return;
            }
            $http.post('/lemon/personal/center/modify/privacy',$scope.modifyPrivacyPostData).success(function(data){
                alert(data.msg);
            }).error(function(){
                alert("网络异常，请稍后再试！");
            });
        };
        // 反馈
        $scope.sendFeedbackPostData = function (event) {
            if ($scope.feedbackPostData.content==''){
                alert("没有填写内容哦");
            }
            $http.post('/lemon/feedback',$scope.feedbackPostData).success(function(data){
                alert(data.msg);
            }).error(function(){
                alert("网络异常，请稍后再试！");
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
                $scope.modifyDataPostData.avatar = response.data;
                $scope.$apply();
            }else{
                alert(response.msg);
            }
        });
        uploader.on('error',function() {
            alert('不支持的图片!')
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
