<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
	<br/>
	<table width="250" align="center">
		<tr>
			<td>
				<img src="E:/学习测试程序/JavaTest/Project1_BookStore/WebContent/images${book.path}/${book.photoFileName}">	
			</td>
			
			<td align="left">
				&nbsp;书名：${book.name}<br/>
				&nbsp;作者：${book.author}<br/>
				&nbsp;价格：￥${book.price}<br/>
				&nbsp;介绍：${book.description}<br/><br/>
				&nbsp;<a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=buyBooks&bookId=${book.id}">放入购物车</a>&nbsp;
				<a href="javascript:history.back()">继续购物</a>
			</td>
		</tr>
	</table>
</body>
</html>