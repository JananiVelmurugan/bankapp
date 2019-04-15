<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>list Product</title>
</head>
<body>

	<h3>CUSTOMER Details</h3>
	<table border="1">
		<thead>
			<tr>
				<th>Id</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>User Name</th>
				<th>Password</th>
				<th>Balance</th>
			
			</tr>
		</thead>
		<tbody>
			<c:forEach var="a" items="${CUSTLIST}">
				<tr>
				<td>${a.id}</td>
					<td>${a.firstName}</td>
					<td>${a.lastName}</td>
					<td>${a.userName}</td>
				    <td>${a.password}</td>
				    <td>${a.accountBalance}</td>
				    
					
					
				</tr>
			</c:forEach>
		</tbody>
	</table>


</body>
</html>