package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import repositories.DBAccessor;
import util.Logger;

/**
 * @author yuki.wakisaka
 */
@WebServlet("/users")
public class GetServlet extends HttpServlet {

    private static final DBAccessor dbAccessor = DBAccessor.getInstance();

    private static final Logger logger = Logger.getLogger(GetServlet.class.getSimpleName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Map<Integer, String> users = new HashMap<>();
        Exception exception = null;

        resp.setContentType("text/html");


        try {
            users = dbAccessor.getUsers();
        } catch (SQLException e) {
            resp.setStatus(500);
            exception = e;
            for (StackTraceElement st : e.getStackTrace()) {
                logger.warning(st.toString());
            }
        }

        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"ja\">");
            out.println("<body>");
            if (exception != null) {
                out.println("<p>");
                out.println(exception);
                out.println("</p>");
            }
            out.println("<h2>");
            out.println("Users");
            out.println("</h2>");
            out.println("<p><a href=\"/add\">Add New User</a></p>");
            for (Map.Entry u : users.entrySet()) {
                out.println("id: ");
                out.println(u.getKey());
                out.println("<br>");
                out.println("name: ");
                out.println(u.getValue());
                out.println("<br><br>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }
}
