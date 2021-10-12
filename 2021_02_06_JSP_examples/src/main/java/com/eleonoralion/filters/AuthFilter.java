package com.eleonoralion.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/Home")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        // Преобразовываем
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // Получаем сессию
        HttpSession session = request.getSession(false); // false, чтобы если нет сессии, Java не создала новую

        // Проверяем есть ли такая уже сессия
        if(session == null || session.getAttribute("user") == null){
            //
            //response.sendRedirect(request.getContextPath() + "/Login");

            request.getServletContext().getRequestDispatcher("/Login").forward(request, response);
        }

        // Цепь Фильтров,
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
