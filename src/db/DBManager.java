package db;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {

    private static Connection connection;
    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/javaeeadmin", "postgres", "postgres");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static User getUser(String email){
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFullName(resultSet.getString("full_name"));
                user.setRole(resultSet.getInt("role_id"));
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
    public static void addUser(User user){
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO users (email, password, full_name, role_id) " +
                    "VALUES (?, ?, ?, ?)");

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setInt(4, user.getRole());

            statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateUser(User user){
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE public.users SET email = ?, password = ?, full_name = ?, role_id = ? " +
                    "WHERE id = ?");

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setInt(4, user.getRole());
            statement.setLong(5, user.getId());

            statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void addNews(News news){
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO news (title, content, post_date, author_id) " +
                    "VALUES (?, ?, NOW(), ?)");

            statement.setString(1, news.getTitle());
            statement.setString(2, news.getContent());
            statement.setLong(3, news.getUser().getId());

            statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<News> getNews(){
        ArrayList<News> news = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT n.id, n.title, n.content, n.author_id, u.full_name, n.post_date " +
                    "FROM news n " +
                    "INNER JOIN users u ON u.id = n.author_id " +
                    "ORDER BY n.post_date DESC ");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                News n = new News();
                n.setId(resultSet.getLong("id"));
                n.setTitle(resultSet.getString("title"));
                n.setContent(resultSet.getString("content"));
                n.setPostDate(resultSet.getTimestamp("post_date"));

                User user = new User();
                user.setId(resultSet.getLong("author_id"));
                user.setFullName(resultSet.getString("full_name"));
                n.setUser(user);

                news.add(n);
            }
            statement.close();


        }catch (Exception e){
            e.printStackTrace();
        }
        return news;
    }

    public static News getNewsById(Long id){
        News n = null;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT n.id, n.title, n.content, n.author_id, u.full_name, n.post_date " +
                    "FROM news n " +
                    "INNER JOIN users u ON u.id = n.author_id " +
                    "WHERE n.id = ? ");
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                n = new News();
                n.setId(resultSet.getLong("id"));
                n.setTitle(resultSet.getString("title"));
                n.setContent(resultSet.getString("content"));
                n.setPostDate(resultSet.getTimestamp("post_date"));

                User user = new User();
                user.setId(resultSet.getLong("author_id"));
                user.setFullName(resultSet.getString("full_name"));
                n.setUser(user);

            }
            statement.close();


        }catch (Exception e){
            e.printStackTrace();
        }
        return n;
    }
    public static void updateNews(News news){
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE news SET title = ?, content = ? " +
                    "WHERE id = ?");

            statement.setString(1, news.getTitle());
            statement.setString(2, news.getContent());
            statement.setLong(3, news.getId());

            statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteNews(Long id){
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "DELETE FROM news WHERE id = ?");

            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();


            statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void addComment(Comment comment){
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO comments (comment, user_id, news_id, post_date) " +
                    "VALUES (?, ?, ?, NOW())");

            statement.setString(1, comment.getComment());
            statement.setLong(2, comment.getUser().getId());
            statement.setLong(3, comment.getNews().getId());

            statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Comment> getComments(Long newsId){
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT co.id, co.comment, co.post_date, co.news_id, co.user_id, u.full_name " +
                    "FROM comments co " +
                    "INNER JOIN users u ON u.id = co.user_id " +
                    "WHERE co.news_id = ? " +
                    "ORDER BY co.post_date DESC");

            statement.setLong(1, newsId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setComment(resultSet.getString("comment"));
                comment.setPostDate(resultSet.getTimestamp("post_date"));
                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                comment.setUser(user);

                News news = new News();
                news.setId(resultSet.getLong("news_id"));
                comment.setNews(news);

                comments.add(comment);
            }

            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return comments;
    }
}
