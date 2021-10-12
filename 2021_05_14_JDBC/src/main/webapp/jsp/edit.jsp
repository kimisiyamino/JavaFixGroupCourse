<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Edit</title>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        EDIT USER
    </div>
    <table>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
        </tr>
            <tr>
                <td>${user.getFirstName()}</td>
                <td>${user.getLastName()}</td>
            </tr>
    </table>

    <form method="post" action="${pageContext.request.contextPath}/updateUser?id=${user.id}">

        <div class="input-field">ID: ${user.id}"</div>

        <label for="first-name">First Name
            <input class="input-field" type="text" id="first-name" name="first-name" value="${user.getFirstName()}">
        </label>
        <label for="last-name">Last Name
            <input class="input-field" type="text" id="last-name" name="last-name" value="${user.getLastName()}">
        </label>
        <input type="submit" value="Edit">
    </form>

    <a href="${pageContext.request.contextPath}/show">Back</a>
</div>
</body>
</html>
