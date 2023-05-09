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

@WebServlet(value = "/save-news")
public class ToUpdateNewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("currentUser");
        if (user!=null){
            if (user.getRole()==1) {
                String title = request.getParameter("title");
                String content = request.getParameter("content");
                Long id = Long.parseLong(request.getParameter("id"));

                News news = DBManager.getNewsById(id);
                if (news != null) {


                    news.setTitle(title);
                    news.setContent(content);
                    news.setUser(user);

                    DBManager.updateNews(news);
                    response.sendRedirect("/news-details?id=" + id);
                } else {
                    response.sendRedirect("/");
                }
            }else {
                request.getRequestDispatcher("/404.jsp").forward(request, response);
            }
        }else {
            response.sendRedirect("/login");
        }
    }

}
