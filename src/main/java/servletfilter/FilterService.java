package servletfilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter("/filterService")
public class FilterService implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init execute");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        boolean loggedIn = session.getAttribute("key") != null;
        String loginURI = request.getContextPath() + "/registration_form.jsp";
        String welcomeURI = request.getContextPath() + "/welcome.jsp";

        if (loggedIn) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(loginURI);
            System.out.println("Login failed");
        }
    }

    @Override
    public void destroy() {
    }
}
