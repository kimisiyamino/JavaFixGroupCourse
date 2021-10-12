<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.eleonoralion.models.User" %>
<%@ page import="java.io.PrintWriter" %>

<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.02.2021
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>

<!-- < % @ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %> -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <!-- Подключение JSTL  -->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
    <head>
        <!-- Кодировка  -->
        <meta charset="UTF-8">
        <!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
        <!-- Подключение СSS  -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/signUp.css">
        <!-- Заголовок в браузере  -->
        <title>JSP</title>
    </head>
    <body>
        <!-- ЗАГОЛОВОК -->
        <h1>TEST</h1>
        <h2>Регистрация</h2>
        <!-- Форма входа -->
        <form class="login-box" method="post" action="${pageContext.request.contextPath}/SignUp">
            <!-- Введите имя -->
            <label for="name">Логин:
                <input type="text" name="name" id="name" required>
            </label>
            <!-- Введите день рождения -->
            <label for="birthday">День рождения <span class="noRequired">(Необязательно)</span>
                <input type="text" name="birthday" id="birthday">
            </label>
            <!-- Введите пароль-->
            <label for="password">Пароль:
                <input type="password" name="password" id="password" required>
            </label>
            <!-- Кнопка отправить -->
            <input type="submit" value="Зарегиться" id="sign">
            <!-- Поле для текста -->
            <label for="simpleText">
                <input type="text" id="simpleText" name="simpleText" placeholder="Место для комментария">
            </label>
            <!-- Кнопка сбросить -->
            <input type="reset" value="Сброс формы" id="reset">
        </form>

        <a class="refLogin" href="${pageContext.request.contextPath}/Login">К странице входа</a>

        <h3>Зарегистрированны пользователи</h3>
        <table>
            <tr>
                <th>Name</th>
                <th>Pass</th>
                <th>Birth</th>
            </tr>

            <c:forEach items="${UsersFromRepositories}" var="user">
                <tr>
                    <th>${user.name}</th>
                    <th>${user.password}</th>
                    <th>${user.birthday}</th>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
