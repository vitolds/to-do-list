<%@ page import="lv.javaguru.java2.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: Vitolds
  Date: 12/18/2016
  Time: 5:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
    <%
        User user = (User) request.getSession().getAttribute("user");
    %>
    <%= user.getUserName()%>
    <br>
    <a href="/java2/create_task">Create task</a>
</body>
</html>
