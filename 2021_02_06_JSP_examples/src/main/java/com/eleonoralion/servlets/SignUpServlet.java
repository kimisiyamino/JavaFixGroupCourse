package com.eleonoralion.servlets;

import com.eleonoralion.models.User;
import com.eleonoralion.repositories.UserRepositotiesInMemory;
import com.eleonoralion.repositories.UsersRepositories;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

// Создали проект
// Добавли в pom: GOOGLE- maven servlets 3, <packaging> war
// Добавли в main->webapp->WEB-INF-> web.xml: schemas only (GOOGLE- web xml 3)
// Run-Edit Configuration-> add tomcat local
// main - new class servlet + annotation mapping

// Добавили папки models, repositories и написали соответствующие классы
// Добавили в pom поддержку java 1.8 (google: maven java 8), строчки build
// webapp -> jsp -> signUp.jsp
// init()  doGet() add forward & setAttributes

// doPost()
//
//
//
//

@WebServlet("/SignUp")
public class SignUpServlet extends HttpServlet {
    private UsersRepositories usersRepositories;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Пример получения PrintWriter
        //PrintWriter writer = response.getWriter();
        //writer.write("<html>Hello</html>");


        // Кодировка
        //response.setContentType("text/html; charset=UTF-8");  // Попытка- Не работает!
        request.setCharacterEncoding("UTF-8");                  // Попытка- Работает!

        // Пример использования аттрибутов
        List<User> users = usersRepositories.findAll();
        request.setAttribute("UsersFromRepositories", users);

        // Перенаправление Forward
        RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/jsp/signUp.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    public void init() {
        usersRepositories = new UserRepositotiesInMemory();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Кодировка
        request.setCharacterEncoding("UTF-8");

        // Получение данных с формы
        String name = request.getParameter("name");
        System.out.println(name);
        String password = request.getParameter("password");
        LocalDate birthday = null;
        if(!request.getParameter("birthday").equals("")) {
            try {
                birthday = LocalDate.parse(request.getParameter("birthday"));
            }catch (Exception e){
                birthday = LocalDate.parse("1970-01-01");
                e.printStackTrace();
            }
        }else {
            birthday = LocalDate.parse("1970-01-01");
        }

        // Сохраняем данные
        User user = new User(name, password, birthday);
        //usersRepositories.saveUser(user);
        usersRepositories.save(user);
        // Перенаправление
        response.sendRedirect("/Login");
        //doGet(request, response);
    }
}
