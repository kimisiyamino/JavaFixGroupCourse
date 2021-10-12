package com.eleonoralion.database.servlets;

import com.eleonoralion.database.dao.UserDao;
import com.eleonoralion.database.dao.UsersDaoJdbcImpl;
import com.eleonoralion.database.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

@WebServlet("/add")
public class AddUserServlet extends HttpServlet {

    //private Connection connection;
    private UserDao usersDaoJdbc;

    // Инициализация сервлета
    @Override
    public void init() throws ServletException {

        // Класс для загрузки properties файлов
        Properties properties = new Properties();

        try {
            // Передаём правильный путь, т.к. приложение запускается внутри Tomcat
            properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));

            // Получаем настройки
            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            String dbClass = properties.getProperty("db.driverClassName");

            // Подключаем драйвер-класс (Предварительно подключаем в Maven)
            Class.forName(dbClass);

            // Настраиваем connection
            Connection connection;
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

            // DAO
            usersDaoJdbc = new UsersDaoJdbcImpl(connection);

        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Get method
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //List<User> users = usersDaoJdbc.findAll();

        // Отдаём JSP страницу
        request.getServletContext().getRequestDispatcher("/jsp/add.jsp").forward(request, response);
    }

    // Post method (даннные с формы)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");

//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO fix_user(firstName, lastName) VALUES (?, ?)");
//            // parameter из формы с его id в JSP
//            preparedStatement.setString(1, firstName);
//            preparedStatement.setString(2, lastName);
//
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        User user = new User(firstName, lastName);
        usersDaoJdbc.save(user);

        // Redirect на URL сервлета
        response.sendRedirect(request.getContextPath() + "/show");
    }
}
