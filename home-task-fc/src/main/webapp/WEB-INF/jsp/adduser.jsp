<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Users</title>
    <link href="css/adduser.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
<%@include file="navigatonbar.jsp" %>
<form id="addUserForm" action="/users/add" method="post" style="margin-top: 50px">

    <div class="container register-form">
        <div class="form">
            <div class="note">
                <p>Add employee</p>
            </div>
            <div class="form-content">
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="employeeId">Employee's ID</label>
                            <input id="employeeId" name="employeeId" type="text" class="form-control" placeholder="Employee Id" value=""/>
                        </div>
                        <div class="form-group">
                            <label for="employeePosition">Employee's Position</label>
                            <input id="employeePosition" name="employeePosition" type="text" class="form-control" placeholder="Position" value=""/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="employeeName">Employee's Name</label>
                            <input id="employeeName" name="employeeName" type="text" class="form-control" placeholder="Name" value=""/>
                        </div>
                        <div class="form-group">
                            <label for="employeePhone">Employee's Phone</label>
                            <input id="employeePhone" name="employeePhone" type="text" class="form-control" placeholder="Phone" value=""/>
                        </div>
                    </div>
                    <div class="col-md-6">
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="employeeEmail">Employee's Email</label>
                            <input id="employeeEmail" name="employeeEmail" type="text" class="form-control" placeholder="Email" value=""/>
                        </div>
                    </div>
                </div>
                <button type="button" class="btnSubmit">Submit</button>
                <div id="notification" style="display: none; color: red;">Please fill in all fields and ensure Employee ID is exactly three digits.</div>
            </div>
        </div>
    </div>
</form>

<script>
    $(document).ready(function () {
        $(".btnSubmit").click(function () {
            let employeeId = $("#employeeId").val();
            let employeePosition = $("#employeePosition").val();
            let employeeName = $("#employeeName").val();
            let employeePhone = $("#employeePhone").val();
            let employeeEmail = $("#employeeEmail").val();
            let isValid = true;

            $("input").removeClass("is-invalid");

            if (employeeId.length !== 3 || !employeeId.match(/^\d+$/)) {
                $("#employeeId").addClass("is-invalid");
                isValid = false;
            }

            if (employeePosition === '') {
                $("#employeePosition").addClass("is-invalid");
                isValid = false;
            }

            if (employeeName === '') {
                $("#employeeName").addClass("is-invalid");
                isValid = false;
            }

            var phoneValid = isValidPhoneNumber(employeePhone), emailValid;
            if (!phoneValid) {
                $("#employeePhone").addClass("is-invalid");
            }

            emailValid = isValidEmail(employeeEmail);
            if (!emailValid) {
                $("#employeeEmail").addClass("is-invalid");
            }

            if (!isValid || !phoneValid || !emailValid) {
                var notificationText = "";
                if (!isValid) {
                    notificationText = "Please fill in all fields correctly and ensure Employee ID is exactly three digits.";
                }
                if (!phoneValid) {
                    notificationText += "\nNot valid phone number";
                }
                if (!emailValid) {
                    notificationText += "\nEmail is not valid.";
                }
                $("#notification").text(notificationText);
                $("#notification").show();
                return false;
            }

            $("#notification").hide();
            $("#addUserForm").submit();
        });
    });


    function isValidPhoneNumber(phone) {
        const phoneRegex = /^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\s\./0-9]*$/;
        return phone.match(phoneRegex);
    }

    function isValidEmail(email) {
        const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
        return email.match(emailRegex);
    }
</script>

</body>
</html>
