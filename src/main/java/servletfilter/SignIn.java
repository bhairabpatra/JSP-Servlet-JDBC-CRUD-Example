package servletfilter;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/sign-in")
public class SignIn extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletConfig().getServletContext();
        ServletConfig servletConfig = getServletConfig();
        System.out.println(servletContext);
        System.out.println("servletConfig" + servletConfig);
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email.equals("test@gmail.com") && password.equals("12345")) {
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("key", email);
            resp.sendRedirect("filterService");
        } else {
            resp.sendRedirect("registration_form.jsp");
        }
    }
}
//https://www.javaguides.net/2019/10/build-todo-app-using-jsp-servlet-jdbc-and-mysql-part1.html