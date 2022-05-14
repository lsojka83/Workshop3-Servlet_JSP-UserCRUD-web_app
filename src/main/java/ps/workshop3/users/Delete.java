package ps.workshop3.users;

import ps.workshop3.entity.User;
import ps.workshop3.entity.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteUser", value = "/users/delete")
public class Delete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("userId");
        UserDao userDao = new UserDao();
        User read = userDao.read(Integer.parseInt(id));
        request.setAttribute("user", read);
        request.setAttribute("id", 0);
        getServletContext().getRequestDispatcher("/users/delete.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if ("Confirm".equals(request.getParameter("confirmed"))) {
            UserDao userDao = new UserDao();
            userDao.delete(Integer.valueOf(request.getParameter("id")));
            response.sendRedirect("/users/list");
        } else if ("no".equals(request.getParameter("confirmed"))) {
            response.sendRedirect("/users/list");
        }

    }
}
