<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/headerfooter/header.jsp" %>
<%@ include file="/resources/sub01/sub_image.html" %> 
<%@ include file="/resources/sub01/sub_menu.html" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<article>
<h2>My Page(주문 상세정보)</h2>
<form name="formm" method="post">
<h3>주문자 정보</h3>
<table id="cartList">
	<tr><th>주문일자</th><th>주문번호</th><th>주문자</th><th>주문총액</th></tr>
	<tr><td><fmt:formatDate value="${orderDerail.indate}" type="date"/></td>
</table>



</form>

</article>
</body>
</html>