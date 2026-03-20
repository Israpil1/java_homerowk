package org.example.servlet;

import org.example.config.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list": listUsers(response); break;
            case "edit": showEditForm(request, response); break;
            case "delete": deleteUser(request, response); break;
            default: listUsers(response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "register";

        switch (action) {
            case "register": registerUser(request, response); break;
            case "update": updateUser(request, response); break;
        }
    }

    private void listUsers(HttpServletResponse response) throws IOException {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {

            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            out.println("<h2>Список пользователей</h2>");
            out.println("<a href='register.html'>Добавить пользователя</a><br><br>");
            out.println("<table border='1'><tr><th>ID</th><th>Username</th><th>Email</th><th>Actions</th></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("username") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("<td><a href='users?action=edit&id=" + rs.getInt("id") + "'>Edit</a> | " +
                        "<a href='users?action=delete&id=" + rs.getInt("id") + "'>Delete</a></td>");
                out.println("</tr>");
            }
            out.println("</table>");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO users(username,email,password) VALUES(?,?,?)")) {

            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password); // В реальном приложении хэшируем!
            ps.executeUpdate();
            response.sendRedirect("users?action=list");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id=?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                PrintWriter out = response.getWriter();
                response.setContentType("text/html");
                out.println("<h2>Редактировать пользователя</h2>");
                out.println("<form method='post' action='users?action=update'>");
                out.println("<input type='hidden' name='id' value='" + rs.getInt("id") + "'>");
                out.println("Username: <input type='text' name='username' value='" + rs.getString("username") + "'><br>");
                out.println("Email: <input type='text' name='email' value='" + rs.getString("email") + "'><br>");
                out.println("Password: <input type='text' name='password' value='" + rs.getString("password") + "'><br>");
                out.println("<input type='submit' value='Update'>");
                out.println("</form>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE users SET username=?, email=?, password=? WHERE id=?")) {

            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setInt(4, id);
            ps.executeUpdate();
            response.sendRedirect("users?action=list");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE id=?")) {

            ps.setInt(1, id);
            ps.executeUpdate();
            response.sendRedirect("users?action=list");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}