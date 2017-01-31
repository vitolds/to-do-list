<%@ page import="lv.javaguru.java2.domain.User" %>
<%@ page import="lv.javaguru.java2.servlet.dto.UserDTO" %><%--
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
                UserDTO user = (UserDTO) request.getAttribute("data");
            %>
            <div style="float:top; display: inline-block; width:100%">
                <div class="profile-info">
                    <nobr>
                        <h4>Hello <%= user.getUsername()%>! <a class="glyphicon glyphicon-cog" href="/java2/profile/edit"></a></h4>
                    </nobr>
                    <h5><b>Coins <%= user.getCoins()%></b></h5>
                </div>
                <div class="main-task" align="right">
                    <% if (user.getTask()==null) { %>
                    <div>No main task</div>

                    <% } else { %>
                    <h4 style="margin: 0 auto; color:orangered">Main task</h4>
                    <% if (user.getTask().getName()!=null) { %><h3 style="margin: 0 auto"> <%= user.getTask().getName()%> <% if (user.getTask().getText()!=null) { %><small> <%= user.getTask().getText()%> </small><% } %> </h3><% } %>
                    <% if (user.getTask().getDeadline()!=null) { %><div>Deadline: <b><%= user.getTask().getDeadline()%> </b></div><% } %>
                    <% } %>
                </div>
            </div>
            <div style="float:top; display: block">
                <h5>Current task slots: <%= user.getTaskSlots()%></h5>
                <h5>Buy task slots:</h5>
                <form method="post">
                    <div class="input-group" style="max-width: 135px;">
                        <input type="text" class="form-control" placeholder="Task slots" name="slotsToBuy">
                        <span class="input-group-btn">
                        <button class="btn btn-primary" type="submit">Buy</button>
                    </span>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
