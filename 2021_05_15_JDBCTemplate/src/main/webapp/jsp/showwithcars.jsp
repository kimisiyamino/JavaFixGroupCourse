<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Title</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Already registered!
    </div>
    <table>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Cars</th>

        </tr>

            <tr>
                <td>${user.getFirstName()}</td>
                <td>${user.getLastName()}</td>
                <td>
                    <c:forEach items="${cars}" var="car">
                        <div>${car.getModel()}</div>
                    </c:forEach>
                </td>
            </tr>

    </table>

    <a href="${pageContext.request.contextPath}/show">Back</a>
</div>
</body>
</html>