package servlets;

import repositories.DBAccessor;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

import util.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yuki.wakisaka
 */
@WebServlet("/add")
public class PostServlet extends HttpServlet {

    private static final DBAccessor dbAccessor = DBAccessor.getInstance();

    private static final Logger logger = Logger.getLogger(PostServlet.class.getSimpleName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html; charset=utf-8");

        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"ja\">");
        out.println("<body>");
        out.println("<h2>");
        out.println("Add User");
        out.println("</h2>");
        out.println("<form method=\"post\" action=\"/add\">");
        out.println("<p><label for=\"id\">ID: </label>");
        out.println("<input type=\"text\" name=\"id\"></p>");
        out.println("<p><label for=\"name\">NAME: </label>");
        out.println("<input type=\"text\" name=\"name\"></p>");
        out.println("<input type=\"submit\" value=\"送信\">");
        out.println("</form>");
        out.println("<p><a href=\"/users\">See All Users</a></p>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id;
        String name;
        resp.setContentType("text/html; charset=utf-8");

        try {
            Map<String, String[]> body = req.getParameterMap();
            id = body.get("id")[0];
            name = body.get("name")[0];
            dbAccessor.setUser(Integer.parseInt(id), name);

            resp.sendRedirect("/users");
        } catch (SQLException | NumberFormatException e) {
            logger.warning(e.getMessage());
            resp.setStatus(400);

            RequestDispatcher rd = req.getRequestDispatcher("./html/add.html");
            rd.forward(req, resp);
        }
    }
}