<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Display Records</title>
</head>
<body>
    <h1>Records from Database</h1>
    <p>Hello, <%= request.getAttribute("session_name") %>! You have successfully logged in.</p>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Password</th>
            <th>Actions</th>
        </tr>

        <%
            List<Map<String, Object>> dataList = (List<Map<String, Object>>) request.getAttribute("dataList");
            for (Map<String, Object> data : dataList) {
        %>
            <tr>
                <td><%= data.get("id") %></td>
                <td><%= data.get("name") %></td>
                <td><%= data.get("email") %></td>
                <td><%= data.get("phone") %></td>
                <td><%= data.get("password") %></td>
                <td>
                        <form action="delete" method="get">
                                            <input type="hidden" name="id" value="<%= data.get("id") %>">
                                            <input type="submit" value="Delete">
                        </form>
               </td>
               <td>
                                       <form action="edit" method="get">
                                                           <input type="hidden" name="id" value="<%= data.get("id") %>">
                                                           <input type="submit" value="Edit">
                                       </form>
                              </td>
            </tr>
        <%
            }
        %>
    </table>
</body>
</html>
