package ps.workshop3.users;

import ps.workshop3.entity.User;
import ps.workshop3.entity.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditUser", value = "/users/edit")
public class Edit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("userId");
        System.out.println("edit - get - id: " + request.getParameter("id"));
        UserDao userDao = new UserDao();
        User read = userDao.read(Integer.parseInt(id));
        request.setAttribute("user", read);
        request.setAttribute("id", 0);
        getServletContext().getRequestDispatcher("/users/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        if ("Save".equals(request.getParameter("confirmed"))) {
            UserDao userDao = new UserDao();
            User user = userDao.read(Integer.valueOf(request.getParameter("id")));

            System.out.println("ID:    " + request.getParameter("id"));
            System.out.println(user.getId());
            System.out.println(request.getParameter("name"));
            System.out.println(request.getParameter("email"));
            System.out.println(request.getParameter("pass"));

            try {
                if (request.getParameter("name").isEmpty() || request.getParameter("email").isEmpty()
                        || request.getParameter("name") == null || request.getParameter("email") == null
                        || request.getParameter("pass") == null
                ) {
                    request.setAttribute("id", (request.getParameter("id")));
                    request.setAttribute("info", "Empty data. Fill all fields");
                    request.setAttribute("backLink", "/users/edit");
                    getServletContext().getRequestDispatcher("/infoPage.jsp").forward(request, response);

                } else {
                    user.setUserName(request.getParameter("name"));
                    user.setEmail(request.getParameter("email"));
                    if (request.getParameter("pass").isEmpty()) {
                        System.out.println("Pass is empty");

                    } else {
                        user.setPassword(request.getParameter("pass"));
                    }
                    userDao.update(user);
                    response.sendRedirect("/users/list");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("/problemPage.jsp");

            }
        } else if ("no".equals(request.getParameter("confirmed"))) {
            response.sendRedirect("/users/list");
        }
    }
}

