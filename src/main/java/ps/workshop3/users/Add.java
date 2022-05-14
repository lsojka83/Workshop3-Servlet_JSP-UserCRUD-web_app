package ps.workshop3.users;

import ps.workshop3.entity.User;
import ps.workshop3.entity.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddUser", value = "/users/add")
public class Add extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/users/add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if ("yes".equals(request.getParameter("addBack"))) {
            response.sendRedirect("/users/list");
        } else {
            try {
                if (request.getParameter("name").isEmpty() || request.getParameter("email").isEmpty()
                        || request.getParameter("pass").isEmpty()
                        || request.getParameter("name") == null || request.getParameter("email") == null
                        || request.getParameter("pass") == null
                ) {
                    request.setAttribute("id", (request.getParameter("id")));
                    request.setAttribute("info", "Empty data. Fill all fields");
                    request.setAttribute("backLink", "/users/add");
                    getServletContext().getRequestDispatcher("/infoPage.jsp").forward(request, response);

                } else {
                    User user = new User();
                    user.setEmail(request.getParameter("email"));
                    user.setUserName(request.getParameter("name"));
                    user.setPassword(request.getParameter("pass"));
                    UserDao userDao = new UserDao();
                    userDao.create(user);
                    response.sendRedirect("/users/list");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("/problemPage.jsp");

            }
        }
    }
}
