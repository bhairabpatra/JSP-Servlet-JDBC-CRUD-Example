

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends  HttpServlet{

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Simulate user registration process (e.g., saving to database)
        // Here, we'll just print the values for demonstration
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        // Forward to a success page or perform other actions as needed
        response.sendRedirect("registration_success.jsp");
    }
}
