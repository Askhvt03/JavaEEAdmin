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

@WebServlet(value = "/save-user")
public class ToUpdateUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("currentUser");
        if (user!=null){

                Long id = Long.valueOf(request.getParameter("id"));
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String fullName = request.getParameter("full_name");
                Integer role = Integer.parseInt(request.getParameter("role_id"));

                User users = DBManager.getUser(email);
                if (users != null) {
//                    users.setEmail(email);
                    users.setPassword(password);
                    users.setFullName(fullName);
                    users.setRole(role);

                    DBManager.updateUser(users);
                    response.sendRedirect("/profile?id");
                } else {
                    response.sendRedirect("/profile");
                }

        }else {
            response.sendRedirect("/login");
        }
    }

}
