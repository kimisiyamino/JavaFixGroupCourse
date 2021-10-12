<%--
eleonoralion
11.02.2021
19:44
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/signUp.css">
        <title>TEST REG</title>
    </head>
    <body>
        <!-- ЗАГОЛОВОК -->
        <h1>TEST</h1>
        <h2>Регистрация</h2>
        <p class="message">${pageContext.request.getAttribute("message")}</p>
        <!-- Форма входа -->
        <form class="login-box" method="post" action="${pageContext.request.contextPath}/registration">
            <!-- Введите имя -->
            <label for="login">Логин: <span class="required_field">*</span>
                <br/>
                <span class="login_description">Начинается только с буквы, может содержать только английские символы, цифры, нижний пробел и тире.</span>
                <input type="text" minlength="4" maxlength="8" pattern="[a-zA-Z]+[a-zA-Z0-9_-]+" name="login" id="login" required>
            </label>
            <!-- Введите день рождения -->
            <label for="birthday">День рождения <span class="noRequired">(Необязательно)</span>
                <input type="date" name="birthday" id="birthday" min="1900-01-01" max="2018-12-31">
            </label>
            <!-- Введите пароль-->
            <label for="password">Пароль: <span class="required_field">*</span>
                <input type="password" minlength="4" maxlength="8" name="password" id="password" required>
            </label>
            <!-- Кнопка отправить -->
            <input type="submit" value="Зарегистрироваться" id="sign">
            <!-- Поле для текста -->
            <label for="simpleText">
                <input type="text" id="simpleText" name="simpleText" placeholder="Место для комментария">
            </label>
            <!-- Кнопка сбросить -->
            <input type="reset" value="Сброс формы" id="reset">
        </form>

        <a class="refLogin" href="${pageContext.request.contextPath}/login">К странице входа</a>
    </body>
</html>
