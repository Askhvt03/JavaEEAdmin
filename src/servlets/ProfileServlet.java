package servlets;

import db.DBManager;
import db.Items;
import db.News;
import db.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        User user = DBManager.getUser(currentUser.getEmail());
        request.setAttribute("user", user);
        if (currentUser!=null) {
            request.getRequestDispatcher("/profile.jsp").forward(request, response);
        }else {
            response.sendRedirect("/login");
        }

    }
}
