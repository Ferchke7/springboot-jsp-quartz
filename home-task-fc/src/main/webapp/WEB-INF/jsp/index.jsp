<%@ page import="com.example.hometaskfc.models.EmployeeModel" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<%@include file="navigatonbar.jsp"%>
<div class="container">
    <div class="row justify-content-center mt-5">
        <div class="col-md-6">
            <h1 class="text-right">Search</h1>
            <form action="getusersearch" method="get">
                <div class="row mb-4">
                    <div class="form-group col-md-9">
                        <div class="input-group">
                        <input name="keyword" type="text" placeholder="put a value" class="form-control form-control-underlined">
                    </div>
                    </div>
                    <div class="form-group col-md-3">
                        <button type="submit" class="btn btn-primary rounded-pill btn-block shadow-sm">Search</button>
                    </div>

                </div>
            </form>
            <% if (request.getAttribute("userList") != null) { %>
            <ul>
                <% List<EmployeeModel> userList = (List<EmployeeModel>) request.getAttribute("userList"); %>
                <% if (!userList.isEmpty()) { %>
                <% for (EmployeeModel user : userList) { %>
                <li><%= user.getEmployeeName() %></li>
                <% } %>
                <% } else { %>
                <li class="text-center">No employee found</li>
                <% } %>
            </ul>
            <% } %>
        </div>
    </div>
</div>
</body>
</html>
