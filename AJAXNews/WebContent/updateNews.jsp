<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新news</title>
</head>
<body>
<form action="NewsControler" method="post">
<input type="hidden" name="id" value="${news.id}" />
    标题：<input type="text" name="title" value="${news.title}"><br>
    <input type="submit" value="更新">
</form>
</body>
</html>