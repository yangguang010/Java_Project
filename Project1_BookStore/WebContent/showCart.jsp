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
		<c:forEach items="${sessionScope.cart.items}" var="me" varStatus="vs">
			<tr class="${vs.index%2 == 0?'odd':'even'}">
				<td>
					<input type="checkbox" value="${me.key}">
				</td>
				<td>${me.value.book.name}</td>
				<td>${me.value.book.price}</td>
				<td>
					<input type="text" size="3" id="quantity" value="${me.value.quantity}" onchange="changeNum(this,'${me.key}',${me.value.quantity})"/>
				</td>
				<td>${me.value.totalPrice}</td>
				<td>
					<a href="javascript:delOneItem('${me.key}')">删除</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="6" align="right">
				共${sessionScope.cart.totalQuantity}件商品，付款金额：${sessionScope.cart.amount}
				&nbsp;<a href="">去收银台</a>
			</td>
		</tr>
		</table>
		<script type="text/javascript">
		function changeNum(inputObj,bookId,oldNum){
			//用户输入验证
			var num = inputObj.value;
			if(!/^[1-9][0-9]*$/.test(num)){
				alert("请输入正确数量");
				return;
			}
			
			var sure = window.confirm("确定要修改吗？");
			if(sure){
				window.location.href="${pageContext.request.contextPath}/servlet/ClientServlet?op=changeNum&bookId="+bookId+"&num="+inputObj.value;
			}else {
				inputObj.value = oldNum;
			}
		}
		
		function delOneItem(bookId){
			var sure = window.confirm("确定要删除该项吗？");
			if (sure) {
				window.location.href="${pageContext.request.contextPath}/servlet/ClientServlet?op=delOneItem&bookId="+bookId;
			}
		}
		
	</script>
	</c:if>
	
</body>
</html>