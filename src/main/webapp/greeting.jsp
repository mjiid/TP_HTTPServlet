<!-- greetings.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Greetings Servlet</title>
</head>
<body>
<h1>Greetings ${name}!</h1>
<p>Vous avez gagné: <%= Math.random() * 10 %> millions de dollars!</p>
</body>
</html>