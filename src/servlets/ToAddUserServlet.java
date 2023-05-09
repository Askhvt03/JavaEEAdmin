package servlets;

import db.DBManager;
import db.News;
import db.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/add-user")
public class ToAddUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String fullName = request.getParameter("full_name");
                String role = request.getParameter("role_id");

                User user = new User();
                user.setEmail(email);
                user.setPassword(password);
                user.setFullName(fullName);
                user.setRole(Integer.parseInt(role));

                DBManager.addUser(user);
                response.sendRedirect("/login");

    }

}
