<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.eleonoralion.models.User" %>
<%@ page import="java.io.PrintWriter" %>

<%--
  Created by IntelliJ IDEA.
  User: eleonoralion
  Date: 11.02.2021
  Time: 19:44
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Подключение JSTL  -->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
    <head>
        <!-- Кодировка  -->
        <meta charset="UTF-8">
        <!-- Подключение СSS  -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/signUp.css">
        <!-- Заголовок в браузере  -->
        <title>TEST</title>
    </head>
    <body>
        <!-- ЗАГОЛОВОК -->
        <h1>TEST</h1>
        <!-- Форма входа -->
        <form class="login-box" method="post" action="${pageContext.request.contextPath}/Login">
            <!-- Введите имя -->
            <label for="name">Логин:
                <input type="text" name="name" id="name" required>
            </label>
            <!-- Введите пароль-->
            <label for="password">Пароль:
                <input type="password" name="password" id="password" required>
            </label>
            <!-- Кнопка отправить -->
            <input type="submit" value="Вход" id="sign">
        </form>

        <a class="refReg" href="${pageContext.request.contextPath}/SignUp">Регистрация</a>

    </body>
</html>
