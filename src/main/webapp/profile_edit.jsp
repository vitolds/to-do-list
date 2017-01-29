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
    <title>Edit Profile</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="/java2/css/profilePageStyle.css">
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
            <%
                UserDTO user = (UserDTO) request.getAttribute("data");
            %>
            <div class="profile-info" style="width: 30%; margin: 0 auto">
                <form action="/java2/profile/edit" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="form-group">
                        <label>Username: <%= user.getUsername()%></label>
                        <input type="text" class="form-control" placeholder="New Username" name="username">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="New Password" name="password">
                    </div>
                    <div>
                        <label>Email: <%= user.getEmail()%></label>
                        <input type="email" class="form-control" placeholder="New Email" name="email">
                    </div>
                    <div>
                        <label>First Name: <%= user.getFirstName()%></label>
                        <input type="text" class="form-control" placeholder="First Name" name="firstname">
                    </div>
                    <div>
                        <label>Last Name: <%= user.getLastName()%></label>
                        <input type="text" class="form-control" placeholder="Last Name" name="lastname">
                    </div>
                    <div>
                        <label>Visible: <%= user.isVisible()%></label>
                        <input type="checkbox" class="form-control" name="visible" value="true">
                    </div>
                    <button type="submit" class="btn btn-default">Update</button>
                </form>
            </div>
    </div>
</body>
</html>
