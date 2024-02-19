<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
    <h2>Welcome</h2>
    <p>Hello, <%= session.getAttribute("session_name") %>! You have successfully logged in.</p>

</body>
</html>
