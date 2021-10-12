<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11.02.2021
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="<c:url value="/jsp/home.css"/>">
    <link rel="stylesheet" href="<c:url value="/jsp/signUp.css"/>">
</head>
<body>
    <h2>Здарова ${pageContext.session.getAttribute("user")}</h2>
    <span style="color: ${cookie.color.value}"><b>Хоум</b></span>
    <form method="post" action="${pageContext.request.contextPath}/Home">
        <label for="color">
            <select name="color" id="color">
                <option value="red">Красный</option>
                <option value="black">Черный</option>
                <option value="purple">Фиолетовый</option>
                <option value="green">Зелёный</option>
            </select>
        </label>
        <input type="submit" value="отправить" name="">
    </form>
</body>
</html>
