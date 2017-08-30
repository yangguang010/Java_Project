<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
	<br/>
	所有分类:</a>
	<c:forEach items="${list}" var="c">
		<a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=showCategoryBooks&categoryId=${c.id}">${c.name}</a>
	</c:forEach>
	<br/>
	<table border="1" align="center">
	  <tr>
	  	<c:forEach items="${page.records}" var="b">
	    	<td>
	    		<img src="E:/学习测试程序/JavaTest/Project1_BookStore/WebContent/images${b.path}/${b.photoFileName}"/><br/>
	    		书名：${b.name}<br/>
	    		作者：${b.author}<br/>
	    		售价：${b.price}<br/>
	    		<a href="">去看看</a>
	    	</td>
	    </c:forEach>
	  </tr>
	</table>
	<br/>
	第${page.currentPageNum}页/共${page.totalPage}页&nbsp;&nbsp;
	<a href="${pageContext.request.contextPath}/${page.url}&num=${page.prePageNum}">上一页</a>
	<a href="${pageContext.request.contextPath}/${page.url}&num=${page.nextPageNum}">下一页</a>
</body>
</html>