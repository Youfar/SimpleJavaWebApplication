package servlets;

import repositories.DBAccessor;
import util.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author yuki.wakisaka
 */
@WebServlet("/edit")
public class EditNameServlet extends HttpServlet {

    private static final DBAccessor dbAccessor = DBAccessor.getInstance();

    private static final Logger logger = Logger.getLogger(EditNameServlet.class.getSimpleName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher rd = req.getRequestDispatcher("./html/edit.html");
        rd.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id;
        String name;
        resp.setContentType("text/html; charset=utf-8");

        try {
            Map<String, String[]> body = req.getParameterMap();
            //［0］表示取第一个输入值
            id = body.get("id")[0];
            name = body.get("name")[0];
            dbAccessor.editUser(Integer.parseInt(id), name);

        } catch (SQLException | NumberFormatException e) {
            RequestDispatcher rd = req.getRequestDispatcher("./html/edit.html");
            rd.forward(req, resp);
            logger.warning(e.getMessage());
            resp.setStatus(400);
        }
    }

}
