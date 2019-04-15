<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>_________MONEY TRANSFER DETAIL________</h3>
	YOUR ACCOUNT NUMBER:${FROMACCOUNTNO}
	<br>TO ACCOUNT NUMBER:${TOACCOUNTNO}
	<br> AMOUNT TRANSFERRED:${AMOUNTTRANSFERRED}
	<br> BALANCE:${SENDERBALANCE}

	<%-- your previous account balance :${{PBALSENDER}} --%>
	<%-- <br> money sent :${{SENT}}
	<br> current account balance :${{UBALSENDER}} --%>
	<%-- <br> reciever previous account balance :${{PBALRECIEVER}}
	<br>
	final reciever balance                 :${{UBALRECIEVER}} <br> --%>

</body>
</html>