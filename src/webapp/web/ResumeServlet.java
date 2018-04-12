package webapp.web;


import webapp.Config;
import webapp.model.Resume;
import webapp.storage.SqlStorage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8;");
        response.setHeader("Content-type", "text/html; charset=UTF-8");
        Config config = Config.getInstance();
        SqlStorage storage = new SqlStorage(config.getDbUrl(), config.getDbUser(), config.getDbPassword());
        String name = request.getParameter("name");
        PrintWriter writer = response.getWriter();
        ArrayList<Resume> resumes = (ArrayList) storage.getAllSorted();

        writer.write(
                "<html>\n" +
                        "<head>\n" +
                        "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                        "    <link rel=\"stylesheet\" href=\"/css/style.css\">\n" +
                        "    <title>Список всех резюме</title>\n" +
                        "</head>\n" +
                        "<header> header </header>" +
                        "<body>\n" +
                        "<section>\n" +
                        "<table border=\"1\" cellpadding=\"8\" cellspacing=\"0\">\n" +
                        "    <tr>\n" +
                        "        <td colspan=\"2\"> <h4 >Список резюме </h4> </td>\n" +
                        "    </tr>\n" +
                        "    <tr>\n" +
                        "        <td>Идентефикатор</td>\n" +
                        "        <td>ФИО </td>\n" +
                        "    </tr>\n");

        for (Resume r : resumes) {
            writer.write(" <tr>\n" +
                    "        <td>" + r.getUuid() + "</td>\n" +
                    "        <td>" + r.getFullName() + "</td>\n" +
                    "    </tr>\n");
        }

        writer.write("</table>\n" +
                "</section>\n" +
                "<footer> footer </footer>\n" +
                "</body>\n" +
                "</html>\n");

      /*  if (name == null || name=="") {
            writer.write("Hello!");
        } else {
            writer.write("Hello "+ name + "!");
        }*/
    }
}
