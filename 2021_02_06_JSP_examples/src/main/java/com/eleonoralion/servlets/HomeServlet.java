package com.eleonoralion.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Cookie c = new Cookie("color", "");
        //c.setMaxAge(0);
        //response.addCookie(c);

        // Направляем на JSP
        request.getServletContext().getRequestDispatcher("/jsp/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем параметры
        String color = request.getParameter("color");

        // Создаём и отправляем клиенту куки
        Cookie colorCookie = new Cookie("color", color);
        response.addCookie(colorCookie);

        //System.out.println(request.getContextPath());

        response.sendRedirect(request.getContextPath() + "/Home");
    }
}
