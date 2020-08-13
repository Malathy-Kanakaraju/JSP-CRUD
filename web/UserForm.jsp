<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>User Form</title>
        <link rel="stylesheet" href="css/bootstrap.min.css" />         
        <script type="text/javascript" src="js/jquery-3.5.1.min.js" ></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>     
    </head>
    <body>
    <center>
        <h1>User Form</h1>
        <h2>
            <a href="new">Add User</a>&nbsp;&nbsp;&nbsp;
            <a href="list">Show all users</a>
        </h2>
    </center>

    <c:if test="${user == null}">
        <form class="form-horizontal" action="insert" method="post">
        </c:if>
        <c:if test="${user != null}">
            <form class="form-horizontal" action="update" method="post">
                <input type="hidden" name="id" value="<c:out value='${user.id}' />"/>
            </c:if>
            <div class="form-group row">
                <label class="col-sm-3" for="f-name">First Name</label>
                <div class="col-sm-9 input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-user"></i></span>
                    </div>
                    <input type="text" class="form-control" name="firstName" id="f-name" value="<c:out value='${user.firstName}'/>">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3" for="l-name">Last Name</label>
                <div class="col-sm-9 input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-user"></i></span>
                    </div>
                    <input type="text" class="form-control" name="lastName" id="l-name" value="<c:out value='${user.lastName}'/>">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3" for="address">Address</label>
                <div class="col-sm-9 input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-home"></i></span>
                    </div>
                    <input type="text" class="form-control" name="address" id="address" value="<c:out value='${user.address}'/>">
                </div>
            </div>

            <button type="submit" class="btn btn-primary">Save User</button>
        </form>
    </body>
</html>
