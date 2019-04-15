<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>list of Transaction Details</h3>
	<table border="1">
		<thead>
			<tr>
				<th>Transaction Id</th>
				<th>Amount</th>
				<th>Type</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach var="transaction" items="${TRANSACTIONSLIST}">
				<tr>
					<td>${transaction.id}</td>
					<td>${transaction.amount}</td>
					<td>${transaction.type}</td>


				</tr>
			</c:forEach>
	</table>
</body>
</html>