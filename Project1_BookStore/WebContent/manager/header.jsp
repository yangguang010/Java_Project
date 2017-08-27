<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
<title>后台业务管理</title>
</head>
<body>
<h1 class="top">后台管理</h1>
<br/>
<a href="${pageContext.request.contextPath}/manager/addCategory.jsp">添加分类</a>
<a href="${pageContext.request.contextPath}/servlet/ControlServlet?op=showAllCategories">查询分类</a>
<a href="${pageContext.request.contextPath}/servlet/ControlServlet?op=findAllCategory">添加图书</a>
<a href="">查询图书</a>
<a href="">待处理订单</a>
<a href="">已处理订单</a>
<br/>
<hr/>
