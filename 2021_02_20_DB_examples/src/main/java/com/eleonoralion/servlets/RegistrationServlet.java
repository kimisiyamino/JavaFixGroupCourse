package com.eleonoralion.servlets;

import org.mindrot.jbcrypt.BCrypt;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private Connection connection;
    private final String ERROR_LoginLength = "Длинна логина должна быть от 4 до 8 символов";
    private final String ERROR_LoginChars = "Логин должен начинаться с латинской буквы, далее может содержать только английские символы, цифры, нижний пробел и тире.";
    private final String ERROR_PassLength = "Длинна пароля должна быть от 4 до 8 символов";
    private final String ERROR_LoginExists = "Пользователь с таким именем уже зарегистрирован!";
    private final String ERROR_BirthdayError = "Неверный формат даты рождения!";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        // Кодировка
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Перенаправление Forward
        RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/jsp/registration.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
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

        } catch (FileNotFoundException | ClassNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Кодировка
        request.setCharacterEncoding("UTF-8");

        // Получаем Логин
        String login = request.getParameter("login");
        // Проверка логина
        if(login == null || (login.length() < 4 || login.length() > 8)){ noValid(request, response, ERROR_LoginLength); return; }
        Matcher matcher = Pattern.compile("\\b[a-zA-Z]+[a-zA-Z0-9_-]+\\b").matcher(login);
        if(!matcher.find()){ noValid(request, response, ERROR_LoginChars); return;}

        // Получаем пароль
        String pass = request.getParameter("password");
        // Проверка пароля
        if(pass == null || (pass.length() < 4 || pass.length() > 8)){ noValid(request, response, ERROR_PassLength); return;}

        // Получаем дату рождения
        LocalDate localBirthday = null;
        String birthday = request.getParameter("birthday");

        // Проверяем дату рождения
        if(birthday == null){
            noValid(request, response, ERROR_BirthdayError); return;
        }else{
            if(!birthday.equals("")) {
                try {
                    localBirthday = LocalDate.parse(birthday);
                    if (localBirthday.getYear() < 1900 || localBirthday.getYear() > 2018){
                        noValid(request, response, ERROR_BirthdayError); return;
                    }
                }catch (Exception e){
                    noValid(request, response, ERROR_BirthdayError); return;
                }
            }
        }

        // Хэшируем пароль
        String hashPassword = BCrypt.hashpw(pass, BCrypt.gensalt(12));

        try {
            // Statement 1 вариант

            /*Statement statement = connection.createStatement();
            // Проверяем наличие login в БД
            ResultSet resultSet = statement.executeQuery("SELECT login FROM hysra_user WHERE login='" + login + "';");
            if(resultSet.next()) {
                // Если уже есть
                noValid(request, response, ERROR_LoginExists);
            }else{
                // Если свободно - добавляем в БД
                if (localBirthday == null)
                    statement.execute("INSERT INTO hysra_user(login, password) VALUES ('" + login + "', '" + hashPassword + "');");
                else
                    statement.execute("INSERT INTO hysra_user(login, password, birthday_year, birthday_month, birthday_day) " + "VALUES ('" + login + "', '" + hashPassword + "', " + localBirthday.getYear() + ", " + localBirthday.getMonthValue() + ", " + localBirthday.getDayOfMonth() + ");");
            }*/


            // PreparedStatement 2 вариант

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT ? FROM hysra_user WHERE login=?;");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                // Если уже есть
                noValid(request, response, ERROR_LoginExists);
            }else {
                // Если свободно - добавляем в БД
                if (localBirthday == null) {
                    preparedStatement = connection.prepareStatement("INSERT INTO hysra_user(login, password) VALUES(?, ?);");
                    preparedStatement.setString(1, login);
                    preparedStatement.setString(2, hashPassword);
                    preparedStatement.execute();
                }else{

                    preparedStatement = connection.prepareStatement("INSERT INTO hysra_user(login, password, birthday_year, birthday_month, birthday_day) VALUES(?, ?, ?, ?, ?);");
                    preparedStatement.setString(1, login);
                    preparedStatement.setString(2, hashPassword);
                    preparedStatement.setInt(3, localBirthday.getYear());
                    preparedStatement.setInt(4, localBirthday.getMonthValue());
                    preparedStatement.setInt(5, localBirthday.getDayOfMonth());
                    preparedStatement.execute();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Перенаправление
        //request.setAttribute("message", "Вы успешно зарегистрировались!");
        Cookie cookie = new Cookie("message", URLEncoder.encode("Вы успешно зарегистрировались!", "utf-8"));
        cookie.setMaxAge(1);
        response.addCookie(cookie);
        //Cookie cookie = new Cookie("message", "Вы успешно зарегистрировались!");
        //cookie.setMaxAge(1);
        //response.addCookie(cookie);
        //request.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        response.sendRedirect(request.getContextPath() + "/login");
    }

    private void noValid(HttpServletRequest request, HttpServletResponse response, String error) {
        request.setAttribute("message", error);
        try {
            request.getServletContext().getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
            //doGet(request, response);
            //response.sendRedirect(request.getContextPath() + "/registration");
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}