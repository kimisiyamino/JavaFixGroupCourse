<%@ page import="java.net.URLDecoder" %><%--
eleonoralion
11.02.2021
19:44
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/signUp.css">
        <title>TEST</title>
    </head>
    <body>
        <!-- ЗАГОЛОВОК -->
        <h1>TEST</h1>
        <%
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("message")){
                    request.setAttribute("message", URLDecoder.decode(cookie.getValue(), "UTF-8"));
                    cookie.setMaxAge(0);
                    break;
                }
            }
        %>
        <p class="message">${pageContext.request.getAttribute("message")}</p>
        <!-- <p class="message">${cookie.message.value}</p> -->
        <!-- <p class="message">${pageContext.session.getAttribute("message")}</p> -->

        <!-- Форма входа -->
        <form class="login-box" method="post" action="${pageContext.request.contextPath}/login">
            <!-- Введите имя -->
            <label for="login">Логин:
                <input type="text" minlength="4" maxlength="8" pattern="[a-zA-Z]+[a-zA-Z0-9_-]+" name="login" id="login" required>
            </label>
            <!-- Введите пароль-->
            <label for="password">Пароль:
                <input type="password" minlength="4" maxlength="8" name="password" id="password" required>
            </label>
            <!-- Кнопка отправить -->
            <input type="submit" value="Вход" id="sign">
        </form>
        <!-- Ссылка Регистрация -->
        <a class="refReg" href="${pageContext.request.contextPath}/registration">Регистрация</a>
    </body>
</html>
