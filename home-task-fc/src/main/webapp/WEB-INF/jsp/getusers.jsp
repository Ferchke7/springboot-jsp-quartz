<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</head>
<body>
<%@include file="navigatonbar.jsp"%>
<div style="margin-top: 20px">
<button><a href="${pageContext.request.contextPath}/export/csv">Download CSV</a></button>
<button><a href="${pageContext.request.contextPath}/export/xls">Download XLS</a></button>
</div>
    <div class="list-group">
    <c:if test="${empty userList}">
        <h1>No employee in the list or search</h1>
    </c:if>
    <c:if test="${not empty userList}">
        <c:forEach var="user" items="${userList}">

            <div class="card" style="margin-top: 20px;">
                <div class="card-header">
                    Employee id: ${user.employeeId}
                </div>

                <div class="card-body">
                    <h3 class="card-text">Username: ${user.employeeName}</h3>
                    <h3 class="card-text">Employee position: ${user.employeePosition}</h3>
                    <h3 class="card-text">Employee phone: ${user.employeePhone}</h3>
                    <h3 class="card-text">Employee email: ${user.employeeEmail}</h3>
                    <button type="button" id="formButton_${user.employeeId}" class="btn btn-primary" onclick="changeUser('${user.employeeId}')">Change information</button>

                    <form id="form1_${user.employeeId}" style="display: none;">
                        <br><br>
                        <div class="form-group row">
                            <label for="colFormLabelSm_${user.employeeId}_email" class="col-sm-2 col-form-label col-form-label-sm">Email</label>
                            <div class="col-sm-10">
                                <input type="email" class="form-control form-control-sm" id="colFormLabelSm_${user.employeeId}_email" placeholder="${user.employeeEmail}">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="colFormLabelSm_${user.employeeId}_position" class="col-sm-2 col-form-label col-form-label-sm">Position</label>
                            <div class="col-sm-10">
                                <input type="email" class="form-control form-control-sm" id="colFormLabelSm_${user.employeeId}_position" placeholder="${user.employeePosition}">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="colFormLabelSm_${user.employeeId}_name" class="col-sm-2 col-form-label col-form-label-sm">Name</label>
                            <div class="col-sm-10">
                                <input type="email" class="form-control form-control-sm" id="colFormLabelSm_${user.employeeId}_name" placeholder="${user.employeeName}">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="colFormLabelSm_${user.employeeId}_phone" class="col-sm-2 col-form-label col-form-label-sm">Phone</label>
                            <div class="col-sm-10">
                                <input type="email" class="form-control form-control-sm" id="colFormLabelSm_${user.employeeId}_phone" placeholder="${user.employeePhone}">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="colFormLabelSm_${user.employeeId}_newId" class="col-sm-2 col-form-label col-form-label-sm">New ID</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control form-control-sm" id="colFormLabelSm_${user.employeeId}_newId" placeholder="New ID">
                            </div>
                        </div>
                        <button class="btn btn-danger btn-sm" onclick="changeUserBy('${user.employeeId}')">Change information</button>
                        <br><br>
                    </form>
                    <a href="" class="btn btn-primary" onclick="deleteUser('${user.employeeId}')">Delete</a>
                </div>
            </div>
        </c:forEach>
    </c:if>
</div>

<script>
    function generateCSV() {
        $.ajax({
            url: "/getusers",
            type: "GET",
            success: function(data) {
                let csvContent = "data:text/csv;charset=utf-8,";
                csvContent += "Id,Position,Name,Phone,Email\n";

                $.each(data, function(index, user) {
                    csvContent += user.employeeId + "," + user.employeeName + "," + user.employeePosition + "," + user.employeePhone + "," + user.employeeEmail + "\n";
                });

                let encodedUri = encodeURI(csvContent);
                let link = document.createElement("a");
                link.setAttribute("href", encodedUri);
                link.setAttribute("download", "users.csv");
                document.body.appendChild(link);

                link.click();
            },
            error: function(xhr, status, error) {
                alert("Error generating CSV: " + error);
            }
        });
    }

    function deleteUser(employeeId) {
        if (confirm("deleting employee, are u sure?")) {
            $.ajax({
                type: "POST",
                url: "/deleteuser",
                data: { id: employeeId },
                success: function(response) {
                    alert("Successfully deleted :" + employeeId + "response :" +response)
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    alert("Error deleting user: " + error);
                }
            });
        }
    }

    function changeUserBy(userId) {
        let newId = $("#colFormLabelSm_" + userId + "_newId").val();
        let formData = {
            previousId: userId,
            employeeId: newId,
            employeePosition: $("#colFormLabelSm_" + userId + "_position").val(),
            employeeName: $("#colFormLabelSm_" + userId + "_name").val(),
            employeePhone: $("#colFormLabelSm_" + userId + "_phone").val(),
            employeeEmail: $("#colFormLabelSm_" + userId + "_email").val()
        };

        $.ajax({
            type: "POST",
            url: "/changeEmployee",
            data: formData,
            success: function(response) {
                alert("Employee information updated successfully!");
                window.location.reload();
            },
            error: function(xhr, status, error) {
                alert("Error updating employee information: " + error);
            }
        });
    }
    function manipulateUserId(userId) {
        if (userId < 10) {
            return "00" + userId;
        }
        if (userId < 100) {
            return "0" + userId;
        }
        return userId;
    }
    function changeUser(userId) {
        $("#form1_" + userId).toggle();
    }
</script>
</body>

</html>
