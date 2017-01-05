
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.44/css/bootstrap-datetimepicker.min.css">
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.17.1/moment-with-locales.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.44/js/bootstrap-datetimepicker.min.js"></script>
    <%--<link rel="stylesheet" href="css/style.css">--%>
</head>
<body style="background-color: #eee;">
    <div class="page-header">
        <h2 align="center"><b>To Do List</b></h2>
    </div>

    <form method="post" style="max-width: 530px;padding: 15px;margin: 0 auto;background-color: #fff;
        border-radius: 0.3em;">
        <h2 align="center"><b>Task creation</b></h2><br>
        <%= request.getAttribute("data")%>

        <div class="form-group">
            <label >Task name:</label>
            <input type="text" class="form-control" name="taskName">
        </div>

        <div class="form-group">
            <label>Task text:</label>
            <textarea class="form-control" name="taskText"></textarea>
        </div>

        <label>Deadline date:</label>
        <div class="input-group date" style="max-width: 200px;">
            <input class="form-control model-edit-info input-sm"
                   id="deadlineDate"
                   type="text"
                   name="deadlineDate"/>
            <span class="input-group-addon input-sm" style="cursor:pointer">
                <span class="glyphicon glyphicon-calendar"></span>
            </span>
        </div>
        <br>

        <label>Deadline time:</label>
        <div class="input-group date" style="max-width: 120px;">
            <input class="form-control model-edit-info input-sm"
                   id="deadlineTime"
                   type="text"
                   name="deadlineTime"/>
            <span class="input-group-addon input-sm" style="cursor:pointer">
                <span class="glyphicon glyphicon-time"></span>
            </span>
        </div>
        <br>

        <div class="form-group">
            <label>Task priority:</label>
            <select class="form-control" name="taskPriority" style="max-width: 110px;">
                <option value="1">1 priority</option>
                <option value="2">2 priority</option>
                <option value="3">3 priority</option>
            </select>
        </div>

        <div class="checkbox">
            <label><input type="checkbox" name="isMainTask" value="1"> Main task</label>
        </div>

        <button type="submit" class="btn btn-lg btn-primary btn-block">Submit</button>
    </form>

    <script type="text/javascript">
        $(document).ready(function () {
            $('#deadlineDate').datetimepicker({
                locale: 'en',
                format: 'DD.MM.YYYY',
            });

            $('#deadlineTime').datetimepicker({
                locale: 'en',
                format: 'HH:mm',
            });
        });
    </script>
</body>
</html>
