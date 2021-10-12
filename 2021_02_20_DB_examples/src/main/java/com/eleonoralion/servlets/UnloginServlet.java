package com.eleonoralion.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@WebServlet("/unlogin")
public class UnloginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        HttpSession session = request.getSession();
        session.removeAttribute("user");

        //session.setAttribute("message", "Вы вышли.");
        Cookie cookie = new Cookie("message", URLEncoder.encode("Вы вышли!", "utf-8"));
        cookie.setMaxAge(1);
        response.addCookie(cookie);
        try {
            response.sendRedirect(request.getContextPath() + "/login");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
