package com.eleonoralion.servlets;

import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.*;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final String ERROR_LoginLength = "Длинна логина должна быть от 4 до 8 символов";
    private final String ERROR_LoginChars = "Логин должен начинаться с латинской буквы, далее может содержать только английские символы, цифры, нижний пробел и тире.";
    private final String ERROR_PassLength = "Длинна пароля должна быть от 4 до 8 символов";
    private final String ERROR_FailLogin = "Неверный логин!";
    private final String ERROR_FailPassword = "Неверный пароль!";

    private Connection connection;

    @Override
    public void init(){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));
            String dbUrl = properties.getProperty("db.url");
            String dbDataBase = properties.getProperty("db.database");
            String dbUserName = properties.getProperty("db.user");
            String dbPassword = properties.getProperty("db.password");
            String dbClass = properties.getProperty("db.className");

            Class.forName(dbClass);
            connection = DriverManager.getConnection(dbUrl + dbDataBase, dbUserName, dbPassword);

        } catch (ClassNotFoundException | IOException | SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        /*Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("message")){
                request.setAttribute("message", URLDecoder.decode(cookie.getValue(), "UTF-8"));
                cookie.setMaxAge(0);
                break;
            }
        }
        System.out.println(cookies[1].getName());
        System.out.println(cookies[1].getValue());*/

        request.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Устанавливаем кодировку для всех (русских) символов
        request.setCharacterEncoding("UTF-8");
        // Получаем данные с формы
        String login = request.getParameter("login");
        if(login == null || (login.length() < 4 || login.length() > 8)){ noValid(request, response, ERROR_LoginLength); return; }
        Matcher matcher = Pattern.compile("\\b[a-zA-Z]+[a-zA-Z0-9_-]+\\b").matcher(login);
        if(!matcher.find()){ noValid(request, response, ERROR_LoginChars); return;}

        String password = request.getParameter("password");
        if(password == null || (password.length() < 4 || password.length() > 8)){ noValid(request, response, ERROR_PassLength); return;}

        // Проверяем наличие данного пользователя в базе данных
        try {
            Statement statement = connection.createStatement();
            // Проверяем наличие login в БД
            ResultSet resultSet = statement.executeQuery("SELECT login FROM hysra_user WHERE login='" + login + "';");
            if(resultSet.next()) {
                resultSet = statement.executeQuery("SELECT password FROM hysra_user WHERE login='" + login + "';");
                if(resultSet.next()) {
                    if(BCrypt.checkpw(password, resultSet.getString("password"))){
                        HttpSession httpSession = request.getSession(); // Создали сессию (жетон, токен)
                        httpSession.setAttribute("user", login);
                        //request.getServletContext().getRequestDispatcher("/jsp/home.jsp").forward(request, response);
                        response.sendRedirect(request.getContextPath() + "/home");
                    }else{
                        request.setAttribute("message", ERROR_FailPassword);
                        request.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
                    }
                }
            }else{
                request.setAttribute("message", ERROR_FailLogin);
                request.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Перенаправление
        //request.setAttribute("SuccessReg", "Вы успешно зарегистрировались!");
        //request.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        //response.sendRedirect("/login");

    }

    private void noValid(HttpServletRequest request, HttpServletResponse response, String error) {
        request.setAttribute("message", error);
        try {
            request.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            //doGet(request, response);
            //response.sendRedirect(request.getContextPath() + "/registration");
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}

/*
Для конспекта

настройка проекта - tomcat, packaging, war, servlet, jsp
создание настроек и подключение к дб- Connection, properties (фалй настроек, ),

ПРоблемы соединения jdbc
1. Перемутать логин пароль
2. Не положили в tomcat в папку lib JAR драйвер JDBC
3. Class.forName();
4. url
(5. баг)

  <input type="text" pattern="\d\d\d\d-\d\d-\d\d" name="birthday" id="birthday" placeholder="1990-01-01">

 */
