<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">

<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>身份认证</title>

<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="/css/system.css" rel="stylesheet">
</head>
<script type="text/javascript">


function show(){
	alert("若无法登录系统请于管理员联系！");
}

function check(){
	alert(request.getAttribute("msg"));
}
</script>
<body class="cas zh " >

<div class="container">
			<div class="login">
				<div class="heading" >
					<h2 style="color:#000000">欢迎来到您的安全邮箱系统</h2>
					<h4>让我们先确认您的个人身份和账户安全</h4>
				</div>
				<div class="square-gradient-bar">
					<div class="bar left one"></div>
					<div class="bar two"></div>
					<div class="bar three"></div>
					<div class="bar four"></div>
					<div class="bar five"></div>
					<div class="bar right six"></div>
				</div>
				<div class="well-big square-top">
					<form method="post" action="/user/front/login" th:action="@{/user/front/login}">
						
						<div class="field">
							<div class="input-group ">
								<span class="input-group-addon">
								<img src="/img/user.jpg">
								</span>
								<input type="text" class="form-control input-lg" name="username" tabindex="1" placeholder="用户ID" 
								value="" data-required="true" />
							</div>
						</div>
						
						<div class="field">
							<div class="input-group ">
								<span class="input-group-addon">
									<img src="/img/password.jpg">
								</span>
								<input type="password" class="form-control input-lg" name="password" tabindex="2" placeholder="密码" 
								value="" data-required="true"/>
							</div>
						</div>
						<div class="field">
							<div class="input-group ">
								
								<input type="text" class="form-control input-lg" style="width:150px" name="rd" tabindex="1" placeholder="验证码" 
								value="" data-required="true" />
								<img title="看不清？换一张" src="code" style="cursor:pointer" onclick="this.src='code?date='+new Date();"/>
							</div>
							
						</div>

						<div class="row">
							<div class="col-6 col-lg-6 col-sm-12">
								<input type="submit" class="button" value="登录" onclick="check()"/>
									
							</div>
							<div class="col-6 col-lg-6 col-sm-12">
								<div class="note">
									<div class="title">是否注册？</div>
									<a href="register.ftl">注册账号</a>
									
								</div>
							</div>
						</div>
						
					</form>

				</div>
			</div>
  </div> 

</body>
</html>