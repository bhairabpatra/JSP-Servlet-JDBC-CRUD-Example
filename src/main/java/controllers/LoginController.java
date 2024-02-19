package controllers;

import dao.StudentDao;
import models.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Student student = new Student();
        student.setEmail(email);
        student.setPassword(password);
        StudentDao studentDao = new StudentDao();

        try {
            boolean result = studentDao.loginEmployee(student);
            if (result) {
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("session_name",email);

                System.out.println(httpSession.getAttribute("session_name"));
                System.out.println("Logged in done");
                resp.sendRedirect("welcome.jsp");
            } else {
                resp.setContentType("text/html");
                out.println("<h3> Email and password did not matched </h3>");
                RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
                dispatcher.include(req, resp);
            }

        } catch (Exception e) {

            resp.setContentType("text/html");
            out.println(e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.include(req, resp);

        }
    }
}
