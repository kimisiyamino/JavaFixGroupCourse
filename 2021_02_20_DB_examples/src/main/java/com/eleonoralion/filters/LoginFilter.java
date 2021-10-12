package com.eleonoralion.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/login")
public class LoginFilter implements Filter {
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
        if(session == null || session.getAttribute("user") == null){
            //response.sendRedirect(request.getContextPath() + "/login");
            //request.getServletContext().getRequestDispatcher("/login").forward(request, response);
            chain.doFilter(request, response);
        }else{
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }

    @Override
    public void destroy() {

    }
}
