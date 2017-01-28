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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="css/profilePageStyle.css">
</head>
<body>
    <div class="main-container">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <span class="navbar-brand">TODO<span class="glyphicon glyphicon-book"></span>List</span>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a class="glyphicon glyphicon-user" href="/java2/profile"></a></li>
                    <li><a href="/java2/create_task">Create task</a></li>
                    <li><a href="/java2/tasks">Tasks</a></li>
                    <li><a href="#">?</a></li>
                    <li><a href="#">?</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/java2/logout">Logout</a></li>
                </ul>
            </div>
        </nav>
        <div class="info-container">
            <%
                User user = (User) request.getAttribute("data");
            %>
            <div class="profile-info">
                <nobr>
                    <h4>Hello <%= user.getUsername()%>! <a class="glyphicon glyphicon-cog" href="/java2/profile/edit"></a></h4>
                </nobr>
                <h5><span class="label label-info">Coins <%= user.getCoins()%></span></h5>
            </div>
        </div>
    </div>
</body>
</html>
