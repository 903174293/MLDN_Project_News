<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<%	request.setCharacterEncoding("UTF-8") ;
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=basePath%>" /> 
<title>新闻管理</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<script type="text/javascript" src="jquery/jquery.min.js"></script>
<script type="text/javascript" src="js/back/admin/news/news_add.js"></script>
<script type="text/javascript" src="js/common/mldn_util.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
</head>
<%!
	public static final String NEWS_EDIT_URL = "pages/back/admin/news/NewsAction!edit.action" ;
	public static final String NEWS_LIST_URL = "pages/back/admin/news/NewsAction!list.action" ;
%>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<form action="<%=NEWS_EDIT_URL%>" method="post" class="form-horizontal" id="newsForm" enctype="multipart/form-data">
					<fieldset>
						<legend>
							<label>编辑新闻</label>
						</legend>
						<div class="form-group" id="titleDiv">
							<label class="col-md-2 control-label" for="title">新闻标题：</label>
							<div class="col-md-5">
								<input type="text" id="title" name="title" class="form-control" placeholder="请填写新闻标题" value="${news.title}">
							</div>
							<span class="col-md-5" id="titleSpan">*</span>
						</div>
						<div class="form-group" id="noteDiv">
							<label class="col-md-2 control-label" for="note">新闻内容：</label>
							<div class="col-md-5">
								<textarea id="note" name="note" class="form-control" placeholder="请编写新闻内容">${news.note}</textarea>
							</div>
							<span class="col-md-5" id="noteSpan">*</span>
						</div>
						<div class="form-group" id="picDiv">
							<label class="col-md-2 control-label" for="pic">新闻图片：</label>
							<div class="col-md-5">
								<img src="upload/news/${news.photo}">
								<input type="file" id="pic" name="pic" class="form-control" placeholder="请选择新闻图片">
							</div>
							<span class="col-md-5" id="picSpan">*</span>
						</div>
						<div class="form-group">
							<div class="col-md-3 col-md-offset-3">
								<input type="hidden" name="nid" id="nid" value="${news.nid}">
								<input type="hidden" name="oldphoto" id="oldphoto" value="${news.photo}">
								<input type="submit" value="提交" class="btn btn-primary">
								<input type="reset" value="重置" class="btn btn-warning">
								<a href="<%=NEWS_LIST_URL%>" class="btn btn-link">新闻列表</a> 
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</body>
</html>