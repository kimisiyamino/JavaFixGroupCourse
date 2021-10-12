package com.eleonoralion.servlets;

import com.eleonoralion.repositories.UserRepositotiesInMemory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

    private UserRepositotiesInMemory userRepositotiesInMemory;

    @Override
    public void init() throws ServletException {
        userRepositotiesInMemory = new UserRepositotiesInMemory();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Устанавливаем кодировку для всех (русских) символов
        request.setCharacterEncoding("UTF-8");
        // Получаем данные с формы
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        // Проверяем наличие данного пользователя в фейковой базе данных
        if(userRepositotiesInMemory.isExist(name, password)){
            HttpSession httpSession = request.getSession(); // Создали сессию (жетон, токен)
            httpSession.setAttribute("user", name);
            request.getServletContext().getRequestDispatcher("/Home").forward(request, response);
        }else{
            response.sendRedirect(request.getContextPath() + "/Login");
        }
    }
}
