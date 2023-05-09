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

@WebServlet(value = "/delete-news")
public class ToDeleteNewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("currentUser");
        if (user!=null){
            if (user.getRole()==1) {
                Long id = Long.parseLong(request.getParameter("id"));
                DBManager.deleteNews(id);
                response.sendRedirect("/news");
            }else {
                request.getRequestDispatcher("/404.jsp").forward(request, response);
            }
        }else {
            response.sendRedirect("/login");
        }
    }

}
