<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/manager/header.jsp" %>
	<br/><br/>
	<table border="1" width="638" align="center">
	  <tr>
	    <th>选择</th>
	    <th>书名</th>
	    <th>作者</th>
	    <th>单价</th>
	    <th>描述</th>
	    <th>所属分类</th>
	    <th>图片</th>
	    <th>操作</th>
	  </tr>
	  <c:forEach  items="${page.records}" var="book" varStatus="vs">
		  <tr class="${vs.index%2==0?'odd':'even'}">
		    <td>
		    	<input type="checkbox" name="ids" value="${book.id}"/>
		    </td>
		    <td>${book.name}</td>
		    <td>${book.author}</td>
		    <td>${book.price}</td>
		    <td>${book.description}</td>
		    <td>${book.categoryId}</td>
		    <td>
		    	<img src="E:/学习测试程序/JavaTest/Project1_BookStore/WebContent/images${book.path}/${book.photoFileName}"/>
		    </td>
		    <td>
		    	<a href="javascript:alert('已修改')">修改</a>
		    	<a href="javascript:alert('已删除')">删除</a>
		    </td>
		  </tr>
		</c:forEach>
		<tr>
		
		</tr>
	</table>
	<br/>
	第${page.currentPageNum}页/共${page.totalPage}页&nbsp;&nbsp;
	<a href="${pageContext.request.contextPath}/servlet/ControlServlet?op=showAllBooks&num=${page.prePageNum}">上一页</a>&nbsp;
	<a href="${pageContext.request.contextPath}/servlet/ControlServlet?op=showAllBooks&num=${page.nextPageNum}">下一页</a>
	&nbsp;&nbsp;
</body>
</html>