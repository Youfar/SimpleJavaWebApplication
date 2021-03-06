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
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    private static final DBAccessor dbAccessor = DBAccessor.getInstance();

    private static final Logger logger = Logger.getLogger(DeleteServlet.class.getSimpleName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher rd = req.getRequestDispatcher("./html/delete.html");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id;
        resp.setContentType("text/html; charset=utf-8");

        try {
            Map<String, String[]> body = req.getParameterMap();
            //［0］表示取第一个输入值
            id = body.get("id")[0];
            dbAccessor.deleteUser(Integer.parseInt(id));

            RequestDispatcher rd = req.getRequestDispatcher("./html/delete.html");
            rd.forward(req, resp);
        } catch (SQLException | NumberFormatException e) {
            logger.warning(e.getMessage());
            resp.setStatus(400);

            RequestDispatcher rd = req.getRequestDispatcher("./html/add.html");
            rd.forward(req, resp);
        }
    }

}
