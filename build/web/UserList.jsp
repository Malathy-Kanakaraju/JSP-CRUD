<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of Users</title>
        <link href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css" rel="stylesheet" />
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js" ></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js" ></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#user-table").DataTable();
            })  
        </script>
    </head>
    <body>
    <center>
        <h1>User Management</h1>
        <h2>
            <a href="new">Add User</a>&nbsp;&nbsp;&nbsp;
            <a href="newList">Add Multiple Users</a>&nbsp;&nbsp;&nbsp;
            <a href="list">Show all users</a>
        </h2>
    </center>

    <table id="user-table" border="1" width="50%" class="table table-striped">
        <thead>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Address</th>
        <th>Actions</th>
    </thead>
    <tbody>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td><c:out value="${user.firstName}"/></td>
                <td><c:out value="${user.lastName}"/></td>
                <td><c:out value="${user.address}"/></td>
                <td>
                    <a href="edit?id=<c:out value='${user.id}' />">Edit</a>&nbsp;&nbsp;&nbsp;
                    <a href="delete?id=<c:out value='${user.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
