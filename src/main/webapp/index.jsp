



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h3>Login</h3>
	<form action="customer/checkuser" method="post">
		<table>

			<tr>
				<th>id</th>
				<td><input type="number" name="id" value="1"></td>
			</tr>
			<tr>
				<th>password</th>
				<td><input type="password" name="password" value="mehta"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="SUBMIT"></td>
			</tr>
		</table>
	</form>
	<a href="/manager.jsp">Manager Login</a>

</body>
</html>