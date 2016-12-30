<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>阿里云开放存储服务(OSS) Spring MVC 插件示例</title>
</head>
<body>
	<div id="uploader">
		<form action="/lemon/image/add" method="post" enctype="multipart/form-data">
			<input type="file" name="file"/> <br/>
			<input type="submit" value="上传" />
		</form>
	</div>
	<br />
	<br />
	<div id="result"></div>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
</body>
</html>
