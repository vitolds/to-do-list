<%--
  Created by IntelliJ IDEA.
  User: Vitolds
  Date: 12/8/2016
  Time: 8:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="css/homePageStyle.css">
</head>
<body>
    <div class="text-center heading">
        <h1><b>TODO List</b></h1>
    </div>
    <div>
        <div class="row">
            <div class="col-md-6">
                <form class="text-right register" action="/java2/register" method="POST">
                    <div class="form-group">
                        <label>*Username</label> </br>
                        <input type="text" name="userName"/> </br>
                    </div>
                    <div class="form-group">
                        <label>*Email</label> </br>
                        <input type="email" name="email"/> </br>
                    </div>

                    <div class="form-group">
                        <label>*Password</label> </br>
                        <input type="password" name="password"/> </br>
                    </div>

                    <div class="form-group">
                        <label>FirstName</label> </br>
                        <input type="text" name="firstName"/> </br>
                    </div>

                    <div class="form-group">
                        <label>LastName</label> </br>
                        <input type="text" name ="lastName"/> </br>
                    </div>

                    <button class="btn btn-default" type="submit">Sign Up</button>
                </form>
            </div>
            <div class="col-md-6">
                <form class="text-right login" action="/java2/login" method="POST">
                    <div class="form-group">
                        <label>Username</label> </br>
                        <input type="text" name="userName"/> </br>
                    </div>
                    <div class="form-group">
                        <label>Password</label> </br>
                        <input type="password" name="password"/> </br>
                    </div>
                    <button class="btn btn-default" type="submit">Sign In</button>
                </form>
            </div>
        </div>
        <div class = "row">
            <div class="col-md-6">
                <div class="text-right mes"><p><b><%= request.getAttribute("data")%></b></p></div>
            </div>
            <div class="col-md-6">

            </div>
        </divclass>
    </div>
</body>
</html>
