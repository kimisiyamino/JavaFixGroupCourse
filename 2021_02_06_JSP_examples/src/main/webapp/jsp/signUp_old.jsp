<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.eleonoralion.models.User" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.02.2021
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>JSP</title>
    </head>
    <body>
        <table>
            <tr>
                <th>Name</th>
                <th>Pass</th>
                <th>Birth</th>
            </tr>


            <%  List<User> users = (ArrayList<User>)request.getAttribute("UsersFromRepositories");
                for (User u : users){
            %>
            <tr>
                <th><%=u.getName()%></th>
                <th><%=u.getPassword()%></th>
                <th><%=u.getBirthday()%></th>
            </tr>

            <%
                }
            %>

        </table>
    </body>
</html>
