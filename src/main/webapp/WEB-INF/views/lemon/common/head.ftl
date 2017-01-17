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
        <#if headUserInfoView.avatar?has_content>
            <a class="avatar"><img src="${headUserInfoView.avatar}"></a>
        <#else>
            <a class="avatar"><img src="/img/user48.ico"></a>
        </#if>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li <#if currentPage=="friendsHomepage">class="active"</#if>><a href="/lemon/lemons/friends">首页</a></li>
                <li <#if currentPage=="myselfPage">class="active"</#if>><a href="/lemon/lemons/myself">我的发布</a></li>
                <li <#if currentPage=="friends">class="active"</#if>><a href="#">好友</a></li>
                <li <#if currentPage=="others">class="dropdown active"</#if> class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">更多 <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="/lemon/personal/center">个人中心</a></li>
                    <#--<li class="divider"></li>-->
                    <#--<li class="dropdown-header">友情链接</li>-->
                    <#--<li><a href="#">Separated link</a></li>-->
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li ><a href="#"><span>成就值：${headUserInfoView.score}</span></a></li>
                <li ><a href="#"><span>等级：${headUserInfoView.type}</span></a></li>
                <li <#if currentPage=="add">class="active"</#if>><a href="/lemon/lemons/add">记录<span class="glyphicon glyphicon-plus" style="color: rgb(152, 151, 151); font-size: 6px;"></span></a></li>
                <li ><a href="#">签到<span class="glyphicon glyphicon-map-marker" style="color: rgb(152, 151, 151); font-size: 6px;"></span></a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav><!--header end-->