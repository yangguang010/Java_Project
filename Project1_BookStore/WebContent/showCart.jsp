<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
	<br/>
	<c:if test="${empty sessionScope.cart.items}">
		<h2>对不起！您还没有购买任何商品</h2>
	</c:if>
	<c:if test="${!empty sessionScope.cart.items}">
		<table border="1" width="638" align="center">
		<tr>
			<th>选择</th>
			<th>商品名称</th>
			<th>单价</th>
			<th>数量</th>
			<th>小计</th>
			<th>操作</th>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		
		</table>
	</c:if>
</body>
</html>