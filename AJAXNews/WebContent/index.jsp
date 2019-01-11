<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="jq/jquery-3.1.1.js"></script>
<title>新闻首页</title>
<style>
a {
	text-decoration: none;
}

a:link {
	color: red;
}
td:first-of-type{
	width: 80%;
}
table{
	margin: 0 auto;
	width: 100%;
	border-collapse:collapse;
	}
</style>
</head>
<body>
	<h1 style="text-align: center">欢迎来到新闻首页</h1>
	<div style="margin: 0 auto;width: 20%">
	<a href="addNews.jsp">添加新闻</a>
	<table border="1" style="width: 100%; border-collapse: collapse">
		<tr>
			<th>新闻标题</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${pageList.ls}" var="news">
			<tr>
				<td>${news.title}</td>
				<td><a href="NewsControler?method=doUpdate&id=${news.id}">修改</a>
					<a href="#" onclick="confirmDelete(${news.id})">删除</a></td>
			</tr>
		</c:forEach>
	</table>
	<div id="pd" style="text-align: right">
		共${pageList.totalPages }页 当前第${pageList.pageNum }页
		<c:if test="${pageList.pageNum<pageList.totalPages}">
			<button onclick="nextPage()">下一页</button>
		</c:if>
		<c:if test="${pageList.pageNum!=pageList.totalPages}">
			<button onclick="endPage()">末页</button>
		</c:if>
	</div>
</div>
<script>
var pageNum=1;
var totalPages=${pageList.totalPages};
/* 下一页 */
	function nextPage(){
		$.ajax({
		  	url : "NewsControler",
			type : "GET",  //发送请求的方式
			data : {"pageNum":pageNum+1,"method":"doPaging"}, //要发送到服务器的数据
			dataType : "json",//指定传输的数据格式
			success : function(result) {
			var tContent;
			var ls=result.data.ls;
			pageNum=result.data.pageNum;
			$.each(ls,function(index,news){
				tContent+="<tr><td>"+news.title+"</td><td><a href='NewsControler?method=doUpdate&id="+news.id+"'"+">修改</a><a href='#' onclick='confirmDelete("+news.id+")'"+">删除</a></td></tr>";
				});
			$("table").html(tContent);
			var dContent="共"+totalPages+"页 当前第"+pageNum+"页";
			dContent+="<button onclick='firstPage()'>首页</button><button onclick='prePage()'>上一页</button>";
			if(pageNum<totalPages){
				dContent+="<button onclick='nextPage()'>下一页</button>";
			}
			if(pageNum!=totalPages){
				dContent+="<button onclick='endPage()'>末页</button>";
			}
			$("#pd").html(dContent);
			},
			error : function() {
				//请求失败后要执行的代码
			}
		});
};
	/* 上一页 */
	function prePage(){
		$.ajax({
		  	url : "NewsControler",
			type : "GET",  //发送请求的方式
			data : {"pageNum":pageNum-1,"method":"doPaging"}, //要发送到服务器的数据
			dataType : "json",//指定传输的数据格式
			success : function(result) {
			var tContent;
			var ls=result.data.ls;
			pageNum=result.data.pageNum;
			$.each(ls,function(index,news){
				tContent+="<tr><td>"+news.title+"</td><td><a href='NewsControler?method=doUpdate&id="+news.id+"'"+">修改</a><a href='#' onclick='confirmDelete("+news.id+")'"+">删除</a></td></tr>";
				});
			$("table").html(tContent);
			var dContent="共"+totalPages+"页 当前第"+pageNum+"页";
			if(pageNum!=1){
				dContent+="<button onclick='firstPage()'>首页</button><button onclick='prePage()'>上一页</button>";
			}
			dContent+="<button onclick='nextPage()'>下一页</button>";
			dContent+="<button onclick='endPage()'>末页</button>";
			$("#pd").html(dContent);
			},
			error : function() {
				//请求失败后要执行的代码
			}
		});
	};
	
	/* 首页 */
	function firstPage(){
		$.ajax({
		  	url : "NewsControler",
			type : "GET",  //发送请求的方式
			data : {"pageNum":1,"method":"doPaging"}, //要发送到服务器的数据
			dataType : "json",//指定传输的数据格式
			success : function(result) {
			var tContent;
			var ls=result.data.ls;
			pageNum=result.data.pageNum;
			$.each(ls,function(index,news){
				tContent+="<tr><td>"+news.title+"</td><td><a href='NewsControler?method=doUpdate&id="+news.id+"'"+">修改</a><a href='#' onclick='confirmDelete("+news.id+")'"+">删除</a></td></tr>";
				});
			$("table").html(tContent);
			var dContent="共"+totalPages+"页 当前第"+pageNum+"页";
			dContent+="<button onclick='nextPage()'>下一页</button>";
			dContent+="<button onclick='endPage()'>末页</button>";
			$("#pd").html(dContent);
			},
			error : function() {
				//请求失败后要执行的代码
			}
		});
	};
	
	/* 末页 */
	function endPage(){
		$.ajax({
		  	url : "NewsControler",
			type : "GET",  //发送请求的方式
			data : {"pageNum":totalPages,"method":"doPaging"}, //要发送到服务器的数据
			dataType : "json",//指定传输的数据格式
			success : function(result) {
			var tContent;
			var ls=result.data.ls;
			pageNum=result.data.pageNum;
			$.each(ls,function(index,news){
				tContent+="<tr><td>"+news.title+"</td><td><a href='NewsControler?method=doUpdate&id="+news.id+"'"+">修改</a><a href='#' onclick='confirmDelete("+news.id+")'"+">删除</a></td></tr>";
				});
			$("table").html(tContent);
			var dContent="共"+totalPages+"页 当前第"+pageNum+"页";
			dContent+="<button onclick='firstPage()'>首页</button><button onclick='prePage()'>上一页</button>";
			$("#pd").html(dContent);
			},
			error : function() {
				//请求失败后要执行的代码
			}
		});
	};
	function confirmDelete(id) {
		var isSure=confirm("确认要删除吗");
		if(isSure){
			$.ajax({
			  	url : "NewsControler",
				type : "GET",  //发送请求的方式
				data : {"id":id,"method":"doDelete"}, //要发送到服务器的数据
				dataType : "json",//指定传输的数据格式
				success : function(result) {
					if(result>0){
						alert("删除成功");
						/* window.location.reload();//重新加载当前url，也就是重新加载当前页 */
						window.location.href="NewsControler";//进入指定的地址
					}
					else{
						alert("删除失败");
					}
				},
				error : function() { 
					//请求失败后要执行的代码
				}
			});
		}
	}
</script>
</body>
</html>