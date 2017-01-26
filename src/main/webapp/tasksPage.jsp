<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Tasks</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.44/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="css/css.css">
    <link rel="stylesheet" href="css/tasksPage.css">
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.17.1/moment-with-locales.js"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.44/js/bootstrap-datetimepicker.min.js"></script>
</head>
<body>
<% ObjectMapper mapper = new ObjectMapper();
    String tasksJSON = mapper.writeValueAsString(request.getAttribute("data")); %>
<script type="text/javascript">
    var jsonObj = <%= tasksJSON%>;
    function editTask(taskId) {
        $.each(jsonObj.doneTasks, function (index, task) {
            if (task.taskId == taskId) {
                $("#alert").text("");
                $("#taskName").val(task.name);
                $("#taskText").val(task.text);
                $("#deadlineDate").val(task.deadlineDate);
                $("#deadlineTime").val(task.deadlineTime);
                if (task.isMainTask == 1) {
                    $("#isMainTask").prop("checked", true);
                }
                if (task.done == 1) {
                    $("#isDoneTask").prop("checked", true);
                }
                $("#taskPriority").val(task.priority);
                $("#taskId").val(taskId);
                $("#taskModal").modal('show');
                return;
            }
        });
        $.each(jsonObj.undoneTasks, function (index, task) {
                    if (task.taskId == taskId) {
                        $("#alert").text("");
                        $("#taskName").val(task.name);
                        $("#taskText").val(task.text);
                        $("#deadlineDate").val(task.deadlineDate);
                        $("#deadlineTime").val(task.deadlineTime);
                        if (task.isMainTask == 1) {
                            $("#isMainTask").prop("checked", true);
                        }
                        if (task.done == 1) {
                            $("#isDoneTask").prop("checked", true);
                        }
                        $("#taskPriority").val(task.priority);
                        $("#taskId").val(taskId);
                        $("#taskModal").modal('show');
                        return;
                    }
                });
    }

    function markDone(taskId) {
        $.ajax({
            type: 'POST',
            url: '/java2/tasks',
            data: {
                cmd: "markDone",
                taskId: taskId,
            },
            success: function (data) {
                location.reload();
            }
        });
    }

    function markUndone(taskId) {
        $.ajax({
            type: 'POST',
            url: '/java2/tasks',
            data: {
                cmd: "markUndone",
                taskId: taskId,
            },
            success: function (data) {
                location.reload();
            }
        });
    }

    function markMain(taskId) {
        $.ajax({
            type: 'POST',
            url: '/java2/tasks',
            data: {
                cmd: "markMain",
                taskId: taskId,
            },
            success: function (data) {
                location.reload();
            }
        });
    }

    function markNotMain(taskId) {
        $.ajax({
            type: 'POST',
            url: '/java2/tasks',
            data: {
                cmd: "markNotMain",
                taskId: taskId,
            },
            success: function (data) {
                location.reload();
            }
        });
    }


    $(document).ready(function () {
        $('#doneTasks').click(function () {
            $('#doneTasksTable').toggle(1);
        });
        $('#saveChanges').click(function () {
            if ($('#isDoneTask').checked) {
                var done = "1";
            } else {
                var done = "";
            }
            if ($('#isMainTask').checked) {
                var main = "1";
            } else {
                var main = "";
            }
            $.ajax({
                type: 'POST',
                url: '/java2/tasks',
                data: {
                    taskId: $('#taskId').val(),
                    taskName: $('#taskName').val(),
                    taskText: $('#taskText').val(),
                    deadlineDate: $('#deadlineDate').val(),
                    deadlineTime: $('#deadlineTime').val(),
                    taskPriority: $('#taskPriority').val(),
                    isMainTask: main,
                    isDoneTask: done,
                },
                success: function (data) {
                    $("#alert").html(data);
                }
            });
        });

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
<div class="main-container">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <span class="navbar-brand">TODO<span class="glyphicon glyphicon-book"></span>List</span>
            </div>
            <ul class="nav navbar-nav">
                <li><a class="glyphicon glyphicon-user" href="/java2/profile"></a></li>
                <li><a href="/java2/create_task">Create task</a></li>
                <li class="active"><a href="/java2/tasks">Tasks</a></li>
                <li><a href="#">?</a></li>
                <li><a href="#">?</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/java2/logout">Logout</a></li>
            </ul>
        </div>
    </nav>
    <c:choose>
        <c:when test="${data.mainTask != null}">
            <div class="panel panel-danger" style="width: 75%; margin:0 auto;">
                <div class="panel-heading">
                    <h3 class="panel-title" align="center">
                        <a data-toggle="collapse" href="#mainTaskBody">
                            <b><c:out value="${data.mainTask.name}"/></b>
                        </a>
                    </h3>
                </div>
                <div class="collapse" id="mainTaskBody">
                    <div class="panel-body">
                        <a href="javascript:markDone(<c:out value="${data.mainTask.taskId}"/>)">
                            <span class="glyphicon glyphicon-unchecked" aria-hidden="true" style="color:black"></span>
                        </a>
                        <c:out value="${data.mainTask.text}"/>
                    </div>
                </div>
            </div>
            <br>
        </c:when>
    </c:choose>

    <div class="panel panel-default" align="center" style="width: 75%; margin:0 auto;">
        <div class="panel-heading"><b>Tasks</b></div>

        <table class="table table-condensed table-hover table-active" style="border-collapse:collapse;">
            <c:forEach items="${data.undoneTasks}" var="task">
                <tr id="<c:out value="${task.taskId}"/>" data-toggle="collapse"
                    data-target="#task<c:out value="${task.taskId}"/>">
                    <td>
                        <a href="javascript:markDone(<c:out value="${task.taskId}"/>)"><span
                                class="glyphicon glyphicon-unchecked" aria-hidden="true" style="color:black"></span></a>
                        <c:out value="${task.name}"/>
                    </td>
                    <td align="right">
                        <c:choose>
                            <c:when test="${task.isMainTask.equals('1')}">
                                <a href="javascript:markNotMain(<c:out value="${task.taskId}"/>)">
                                    <span class="glyphicon glyphicon glyphicon-star" aria-hidden="true" style="color:black"></span>
                                </a>
                            </c:when>
                            <c:when test="${task.isMainTask.equals('0')}">
                                <a href="javascript:markMain(<c:out value="${task.taskId}"/>)">
                                    <span class="glyphicon glyphicon glyphicon-star-empty" aria-hidden="true" style="color:black"></span>
                                </a>
                            </c:when>
                        </c:choose>
                        <c:out value="${task.deadlineDate}"/> <c:out value="${task.deadlineTime}"/>
                        <a href="javascript:editTask(<c:out value="${task.taskId}"/>)">
                            <span class="glyphicon glyphicon-edit" aria-hidden="true" style="color:black"></span>
                        </a>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="hiddenRow" style="padding: 0 !important;">
                        <div style="padding: 5 !important;" id="task<c:out value="${task.taskId}"/>" class="collapse">
                            <c:out value="${task.text}"/></div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <br>
    <div class="panel panel-default" align="center" style="width: 75%; margin:0 auto;">
        <div class="panel-heading" id="doneTasks"><b>Done Tasks</b></div>
        <table id="doneTasksTable" class="table table-condensed table-hover table-active"
               style="border-collapse:collapse;display:none;">
            <c:forEach items="${data.doneTasks}" var="task">
                <tr id="<c:out value="${task.taskId}"/>" data-toggle="collapse"
                    data-target="#task<c:out value="${task.taskId}"/>">
                    <td>
                        <a href="javascript:markUndone(<c:out value="${task.taskId}"/>)"><span
                                class="glyphicon glyphicon-check" aria-hidden="true" style="color:black"></span></a>
                        <c:out value="${task.name}"/>
                    </td>
                    <td align="right">
                        <c:out value="${task.deadlineDate}"/> <c:out value="${task.deadlineTime}"/>
                        <a href="javascript:editTask(<c:out value="${task.taskId}"/>)">
                            <span class="glyphicon glyphicon-edit" aria-hidden="true" style="color:black"></span>
                        </a>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="hiddenRow" style="padding: 0 !important;">
                        <div style="padding: 5 !important;" id="task<c:out value="${task.taskId}"/>"
                             class="collapse"><c:out value="${task.text}"/></div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="taskModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" value="Refresh Page" onClick="window.location.reload()" class="close"
                            data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="taskModalLabel">Edit task</h4>
                </div>
                <div class="modal-body">
                    <form method="post" style="max-width: 530px;padding: 15px;margin: 0 auto;background-color: #fff;
        border-radius: 0.3em;">
                        <h2 align="center"><b>Task creation</b></h2><br>
                        <div id="alert"><%= request.getAttribute("data")%>
                        </div>

                        <div class="form-group">
                            <label>Task name:</label>
                            <input type="text" class="form-control" id="taskName" name="taskName" value="">
                        </div>

                        <div class="form-group">
                            <label>Task text:</label>
                            <textarea class="form-control" id="taskText" name="taskText"></textarea>
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
                            <select id="taskPriority" class="form-control" name="taskPriority"
                                    style="max-width: 110px;">
                                <option value="1">1 priority</option>
                                <option value="2">2 priority</option>
                                <option value="3">3 priority</option>
                            </select>
                        </div>

                        <div class="checkbox">
                            <label><input type="checkbox" id="isMainTask" name="isMainTask" value="1"> Main task</label>
                        </div>

                        <div class="checkbox">
                            <label><input type="checkbox" id="isDoneTask" name="isDoneTask" value="1"> Done</label>
                        </div>

                        <input type="hidden" id="taskId" value="">
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" value="Refresh Page" onClick="window.location.reload()"
                            class="btn btn-default" data-dismiss="modal">Close
                    </button>
                    <button type="button" id="saveChanges" class="btn btn-primary">Save changes</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $('.collapse').on('show.bs.collapse', function () {
        $('.collapse.in').collapse('hide');
    });
    $('.collapse2').on('show.bs.collapse', function () {
        $('.collapse2.in').collapse('hide');
    });
</script>
</body>
</html>
