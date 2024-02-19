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
import java.sql.SQLException;

@WebServlet("/")
public class GetUsers extends HttpServlet {
    StudentDao studentDao = new StudentDao();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getServletPath();
        switch (action) {
            case "/delete":
                deleteStudent(req, resp);
                break;
            case "/edit":
                try {
                    editStudent(req, resp);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

            default:
                getUsers(req, resp);
                break;
        }

    }


    private void getUsers(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setAttribute("dataList", studentDao.users());
            req.getRequestDispatcher("users.jsp").forward(req, resp);
        } catch (ClassNotFoundException | ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int userId = Integer.parseInt(req.getParameter("id"));
        if (studentDao.deleteStudent(userId) > 0) {
            System.out.println("Item Deleted");
        } else {
            System.out.println("Delete failed");
        }

    }

    private void editStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException,
            ClassNotFoundException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.editStudent(id);
        // Set the student object as a request attribute
        req.setAttribute("editInfo", student);

        // Forward the request to the JSP page
        req.getRequestDispatcher("edit.jsp").forward(req, resp);

    }
}

