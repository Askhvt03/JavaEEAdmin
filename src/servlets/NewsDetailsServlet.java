package servlets;

import db.Comment;
import db.DBManager;
import db.News;
import db.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/news-details")
public class NewsDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("currentUser");
//        if (user.getRole()==1 && user.getRole()==2){
//        if(user!=null){
        Long id = Long.parseLong(request.getParameter("id"));
        News news = DBManager.getNewsById(id);
        request.setAttribute("news", news);

        if (news!=null){
            ArrayList<Comment> comments = DBManager.getComments(news.getId());
            request.setAttribute("comments", comments);
        }
        request.getRequestDispatcher("/newsdetails.jsp").forward(request, response);
//        }else {
////            request.getRequestDispatcher("/404.jsp").forward(request, response);
//            response.sendRedirect("/login");
//        }
    }
}
