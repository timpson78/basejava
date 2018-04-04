package webapp.web;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
      //  response.setContentType("text/html; charset=UTF-8");
        response.setHeader("Content-type","text/html; charset=UTF-8");
        String name=request.getParameter("name");
        if (name == null || name=="") {
            response.getWriter().write("Hello!");
        } else {
            response.getWriter().write("Hello "+ name + "!");
        }
    }
}
