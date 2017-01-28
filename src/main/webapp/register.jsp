<%--
  Created by IntelliJ IDEA.
  User: vitol
  Date: 22/01/2017
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="/java2/css/login_register.css">
</head>
<body>
    <div class="main-container">
        <h3>TODO<span class="glyphicon glyphicon-book"></span>List</h3>
        <form action="/java2/register" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Username" name="username">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="Password" name="password">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="First name" name="firstName">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Last name" name="lastName">
            </div>
            <div class="form-group">
                <input type="email" class="form-control" placeholder="Email" name="email">
            </div>
            <button type="submit" class="btn btn-default">Sign Up</button>
            <a href="/java2/login"><h4 align="center">Already have an account?</h4></a>
        </form>
    </div>
</body>
</html>
