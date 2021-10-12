package com.eleonoralion.database.servlets;

import com.eleonoralion.database.dao.UserDao;
import com.eleonoralion.database.dao.UsersDaoJdbcImpl;
import com.eleonoralion.database.dao.UsersDaoJdbcTemplateImpl;
import com.eleonoralion.database.models.User;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@WebServlet("/show")
public class UsersShowServlet extends HttpServlet {

    private UserDao usersDao;

    @Override
    public void init() throws ServletException {

        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));

            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            String dbClass = properties.getProperty("db.driverClassName");

            DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

            driverManagerDataSource.setDriverClassName(dbClass);
            driverManagerDataSource.setUrl(dbUrl);
            driverManagerDataSource.setUsername(dbUsername);
            driverManagerDataSource.setPassword(dbPassword);

            // DAO
            usersDao = new UsersDaoJdbcTemplateImpl(driverManagerDataSource);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;

        if(request.getParameter("cars") != null){
            page = "/jsp/showwithcars.jsp";
            Optional<User> user = usersDao.findWithCars(Integer.parseInt(request.getParameter("cars")));
            request.setAttribute("user", user.get());
            request.setAttribute("cars", user.get().getCars());
        }else{
            page = "/jsp/show.jsp";
            List<User> users = usersDao.findAll();
            request.setAttribute("users", users);
        }

        getServletContext().getRequestDispatcher(page).forward(request, response);
    }
}
