package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserBean;
import repositories.DBAccessor;

/**
 * @author yuki.wakisaka
 */
@WebServlet("/jsp")
public class JspServlet extends HttpServlet {

    private static final DBAccessor dbAccessor = DBAccessor.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<UserBean> users = new ArrayList<>();

        try {
            for (Map.Entry<Integer, String> u : dbAccessor.getUsers().entrySet()) {
                users.add(new UserBean(u.getKey(), u.getValue()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("users", users);

        RequestDispatcher rd = req.getRequestDispatcher("./jsp/users.jsp");
        rd.forward(req, resp);
    }
}

