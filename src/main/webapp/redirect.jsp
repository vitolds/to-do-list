<%--
  Created by IntelliJ IDEA.
  User: Vitolds
  Date: 12/23/2016
  Time: 10:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Redirecting...</title>
</head>
<body>
    <p>Redirecting... please wait!</p>
    <% response.sendRedirect(request.getAttribute("data").toString()); %>
</body>
</html>
