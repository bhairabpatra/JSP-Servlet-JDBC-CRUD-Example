package controllers;

import dao.StudentDao;
import database.Database;
import models.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class StudentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("registration_form.jsp");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");

        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setPhone(phone);
        student.setPassword(password);
        try {
            StudentDao studentDao = new StudentDao();
            int result = studentDao.registerEmployee(student);
            if (result > 1) {
                resp.sendRedirect("registration_success.jsp");
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("registration_form.jsp");
                dispatcher.include(req, resp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
