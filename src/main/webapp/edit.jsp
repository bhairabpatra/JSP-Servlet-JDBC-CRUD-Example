
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
</head>
<body>
    <h2>User Registration Form</h2>
    <%=  request.getAttribute("editInfo") %>!
        <form action="register" method="post">
        <label for="username">Name:</label>
        <input type="text" id="name" name="name"  value="${editInfo}" required><br><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email"  value="${editInfo.email}" required><br><br>

        <label for="Phone">phone:</label>
        <input type="text" id="phone" name="phone"  value="${editInfo.phone}" required><br><br>
           <input type="submit" value="update">
    </form>
</body>
</html>
