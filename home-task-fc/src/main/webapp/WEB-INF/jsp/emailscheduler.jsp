<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <title>Schedule Email</title>
</head>
<body>
<%@include file="navigatonbar.jsp"%>

<div class="container mt-5">
    <div class="card mx-auto">
        <div class="card-header">
            <h1>Schedule Email</h1>
        </div>
        <div class="card-body">
            <form id="" action="${pageContext.request.contextPath}/scheduler" method="post">
                <div class="mb-3">
                    <label for="email" class="form-label">Email:</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <div class="mb-3">
                    <label for="dateTime" class="form-label">Date and Time:</label>
                    <input type="datetime-local" class="form-control" id="dateTime" name="dateTime" required>
                </div>
                <div class="mb-3">
                    <label for="timeZone" class="form-label">Time Zone:</label>
                    <select class="form-select" id="timeZone" name="timeZone">
                        <option value="Asia/Seoul">Seoul Time</option>
                        <option value="UTC">UTC</option>

                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Schedule Email</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>