package com.eleonoralion.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@WebFilter("/home")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain){
        // Преобразовываем
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // Получаем сессию
        HttpSession session = request.getSession(false); // false, чтобы если нет сессии, Java не создала новую

        // Проверяем есть ли такая уже сессия
        if(session == null || session.getAttribute("user") == null){
            Cookie cookie = null;
            try {
                cookie = new Cookie("message", URLEncoder.encode("Авторизуйтесь!", "utf-8"));
                cookie.setMaxAge(1);
                response.addCookie(cookie);
                response.sendRedirect(request.getContextPath() + "/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
            //request.setAttribute("message", "Авторизуйтесь!");
            //request.getServletContext().getRequestDispatcher("/login").forward(request, response);
        }
        // Цепь Фильтров,
        try {
            chain.doFilter(request, response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
