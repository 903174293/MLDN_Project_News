<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<%
	String basePath = request.getScheme() + "://" + 
		request.getServerName() + ":" + request.getServerPort() + 
		request.getContextPath() + "/" ;
%>
<%!
	public static final String NEWS_ADD_URL = "pages/back/admin/news/NewsAction!addPre.action" ;
	public static final String NEWS_EDIT_URL = "pages/back/admin/news/NewsAction!editPre.action" ;
%>

<base href="<%=basePath%>"/>
<title>新闻管理</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<script type="text/javascript" src="js/common/mldn_util.js"></script>
<script type="text/javascript" src="js/back/admin/news/news_list.js"></script>
<script type="text/javascript" src="jquery/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="h1"><strong><span class="glyphicon glyphicon-th-list"></span>&nbsp;新闻信息列表</strong></div>
		</div>
		<div class="row">
			<jsp:include page="/pages/plugins/split_page_search_plugin.jsp"/>
			<table class="table table-striped table-bordered table-hover">
				<tr>
					<td style="width:5%"><input type="checkbox" id="selectall"/></td>
					<td>新闻图片</td>
					<td>新闻标题</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${allNews}" var="news">
				<tr>
					<td><input type="checkbox" id="nid" value="${news.nid}:${news.photo}"/></td><!-- 因为要删除图片，所以这样写格式 -->
					<td><img src="upload/news/${news.photo}"></td>
					<td>${news.title}</td>
					<td><a href="<%=NEWS_EDIT_URL%>?nid=${news.nid}" class="btn btn-warning btn-xs">
						<span class="glyphicon glyphicon-pencil"></span>&nbsp;编辑</a></td>
				</tr>
				</c:forEach> 
			</table>
			<jsp:include page="/pages/plugins/split_page_bar_plugin.jsp"/>
			<button id="deleteBtn" class="btn btn-danger btn-lg">
				<span class="glyphicon glyphicon-trash"></span>&nbsp;删除选中信息
			</button>
		</div>
	</div>
</body>
</html>